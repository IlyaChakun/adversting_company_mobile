package by.iba.service.impl;

import by.iba.common.dto.PageWrapper;
import by.iba.dto.AdvertisementReqParams;
import by.iba.dto.req.advertisement.AdvertisementReq;
import by.iba.dto.resp.advertisement.AdvertisementResp;
import by.iba.entity.user.Advertisement;
import by.iba.entity.user.AdvertisementRating;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

import static by.iba.specification.AdvertisementSpecification.findByAdvertisementTypeLike;
import static by.iba.specification.AdvertisementSpecification.sortByAdvertisementRating;

@Service
@AllArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementMapper advertisementMapper;
    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public AdvertisementResp save(AdvertisementReq advertisementReq) {

        Advertisement advertisement = advertisementMapper.toEntityFromReq(advertisementReq);

        User user = userRepository.getOne(advertisementReq.getUserId());
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

        final Pageable pageable =
                PageRequest.of(page, size);

        Specification<Advertisement> specification =
                getAdvertisementSpecification(advertisementReqParams.getAdvertisementTypes(), advertisementReqParams.getSortByRating());

        Page<Advertisement>
                requests = advertisementRepository.findAll(pageable);


        return
                new PageWrapper<>(advertisementMapper
                        .toDtoList(requests.toList()),
                        requests.getTotalPages(),
                        requests.getTotalElements());
    }

    private Specification<Advertisement> getAdvertisementSpecification(Set advertisementType,
                                                                       Boolean ratings) {

        Specification<Advertisement> specification =
                Specification
                        .where(findByAdvertisementTypeLike(advertisementType));

        if (ratings) {
            specification = specification.and(sortByAdvertisementRating(ratings));
        }
        return specification;
    }
}
