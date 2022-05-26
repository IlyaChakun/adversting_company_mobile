package by.iba.service.impl;

import by.iba.common.dto.PageWrapper;
import by.iba.dto.req.advertisement.AdvertisementRatingReq;
import by.iba.dto.resp.advertisement.AdvertisementRatingResp;
import by.iba.entity.user.Advertisement;
import by.iba.entity.user.AdvertisementRating;
import by.iba.exception.ResourceNotFoundException;
import by.iba.mapper.AdvertisementRatingMapper;
import by.iba.repository.AdvertisementRatingRepository;
import by.iba.repository.AdvertisementRepository;
import by.iba.repository.UserRepository;
import by.iba.service.AdvertisementRatingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdvertisementRatingServiceImpl implements AdvertisementRatingService {

    private final AdvertisementRatingMapper advertisementRatingMapper;
    private final AdvertisementRatingRepository advertisementRatingRepository;
    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;

    @Override
    public AdvertisementRatingResp save(AdvertisementRatingReq advertisementReq) {
        AdvertisementRating advertisementRating = advertisementRatingMapper.toEntityFromReq(advertisementReq);
        AdvertisementRating saved = advertisementRatingRepository.save(advertisementRating);

        Advertisement advertisement = advertisementRepository.getOne(advertisementReq.getAdvertisementId());
        advertisement.getRatingSet().add(saved);
        advertisement.setRatingSum(
                (double) (advertisement.getRatingSet().stream().mapToInt(AdvertisementRating::getRating).sum() / advertisement.getRatingSet().size())
        );
        advertisementRepository.save(advertisement);

        return advertisementRatingMapper.toDto(saved);
    }

    @Override
    public AdvertisementRatingResp findById(Long id) {
        AdvertisementRating advertisement = advertisementRatingRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return advertisementRatingMapper.toDto(advertisement);
    }

    @Override
    public PageWrapper<AdvertisementRatingResp> findAll(Integer page, Integer size) {
        final Pageable pageable =
                PageRequest.of(page, size);

        final Page<AdvertisementRating> requests =
                advertisementRatingRepository.findAll(pageable);

        return
                new PageWrapper<>(advertisementRatingMapper
                        .toDtoList(requests.toList()),
                        requests.getTotalPages(),
                        requests.getTotalElements());
    }
}
