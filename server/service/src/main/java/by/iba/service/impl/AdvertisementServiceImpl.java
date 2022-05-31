package by.iba.service.impl;

import by.iba.common.dto.PageWrapper;
import by.iba.dto.AdvertisementReqParams;
import by.iba.dto.req.advertisement.AdvertisementReq;
import by.iba.dto.req.advertisement.AdvertisementUpdateReq;
import by.iba.dto.resp.advertisement.AdvertisementResp;
import by.iba.entity.user.Advertisement;
import by.iba.entity.user.AdvertisementStatus;
import by.iba.entity.user.User;
import by.iba.exception.ResourceNotFoundException;
import by.iba.mapper.AdvertisementMapper;
import by.iba.repository.AdvertisementRepository;
import by.iba.repository.UserRepository;
import by.iba.service.AdvertisementService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

import static by.iba.specification.AdvertisementSpecification.findAllByAdvertisementType;

@Service
@AllArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementMapper advertisementMapper;
    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;

    @Override
    public AdvertisementResp udpate(AdvertisementUpdateReq req) {
        Advertisement advertisement = advertisementRepository.findById(req.getAdvertisementId())
                .orElseThrow(ResourceNotFoundException::new);

        advertisement.setStatus(req.getStatus());

        Advertisement updated = advertisementRepository.save(advertisement);

        return advertisementMapper.toDto(updated);
    }

    @Override
    @Transactional
    public AdvertisementResp save(AdvertisementReq advertisementReq) {

        Advertisement advertisement = advertisementMapper.toEntityFromReq(advertisementReq);

        User user = userRepository.findById(advertisementReq.getUserId()).orElseThrow(ResourceNotFoundException::new);
        advertisement.setUser(user);

        Advertisement saved = advertisementRepository.save(advertisement);

        return advertisementMapper.toDto(saved);
    }

    @Override
    public AdvertisementResp findById(Long id) {
        Advertisement advertisement = advertisementRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return advertisementMapper.toDto(advertisement);
    }

    @Override
    public PageWrapper<AdvertisementResp> findAll(Integer page, Integer size, AdvertisementReqParams advertisementReqParams) {
        Sort sort = null;
        if (advertisementReqParams.getSortByRating()) {
            sort = Sort.by("ratingSum").descending();
        }
        Pageable pageable;
        if (sort != null) {
            pageable = PageRequest.of(page, size, sort);
        } else {
            pageable = PageRequest.of(page, size);
        }

        Page<Advertisement>
                requests;
        if (!advertisementReqParams.getAdvertisementTypes().isEmpty()) {
            Specification<Advertisement> specification =
                    getAdvertisementSpecification(advertisementReqParams.getAdvertisementTypes());

            requests = advertisementRepository.findAll(specification, pageable);
        } else {
            requests = advertisementRepository.findAll(pageable);
        }

        return
                new PageWrapper<>(advertisementMapper
                        .toDtoList(requests.toList()),
                        requests.getTotalPages(),
                        requests.getTotalElements());
    }

    @Override
    public PageWrapper<AdvertisementResp> findAllForAdmin(Integer page, Integer size, AdvertisementReqParams advertisementReqParams) {
        Sort sort = null;
        if (advertisementReqParams.getSortByRating()) {
            sort = Sort.by("ratingSum").descending();
        }
        Pageable pageable;
        if (sort != null) {
            pageable = PageRequest.of(page, size, sort);
        } else {
            pageable = PageRequest.of(page, size);
        }

        Page<Advertisement>
                requests;
        if (!advertisementReqParams.getAdvertisementTypes().isEmpty()) {
            Specification<Advertisement> specification =
                    getAdvertisementSpecification(advertisementReqParams.getAdvertisementTypes());

            requests = advertisementRepository.findAllByStatusEquals(specification, pageable, AdvertisementStatus.PENDING);
        } else {
            requests = advertisementRepository.findAllByStatusEquals(pageable, AdvertisementStatus.PENDING);
        }

        return
                new PageWrapper<>(advertisementMapper
                        .toDtoList(requests.toList()),
                        requests.getTotalPages(),
                        requests.getTotalElements());
    }

    private Specification<Advertisement> getAdvertisementSpecification(Set<String> advertisementTypes) {

        Specification<Advertisement> specification =
                Specification
                        .where(findAllByAdvertisementType(advertisementTypes));

        return specification;
    }
}
