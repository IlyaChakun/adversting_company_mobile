package by.iba.service;

import by.iba.common.dto.PageWrapper;
import by.iba.dto.req.advertisement.AdvertisementRatingReq;
import by.iba.dto.resp.advertisement.AdvertisementRatingResp;

public interface AdvertisementRatingService {

    AdvertisementRatingResp save(AdvertisementRatingReq advertisementReq);

    AdvertisementRatingResp findById(Long id);

    PageWrapper<AdvertisementRatingResp> findAll(final Integer page, final Integer size);

}
