package by.iba.service;

import by.iba.common.dto.PageWrapper;
import by.iba.dto.AdvertisementReqParams;
import by.iba.dto.req.advertisement.AdvertisementReq;
import by.iba.dto.req.advertisement.AdvertisementUpdateReq;
import by.iba.dto.resp.advertisement.AdvertisementResp;

public interface AdvertisementService {

    AdvertisementResp save(AdvertisementReq advertisementReq);

    AdvertisementResp findById(Long id);

    PageWrapper<AdvertisementResp> findAll(final Integer page, final Integer size, AdvertisementReqParams advertisementReqParams, Long id);

    PageWrapper<AdvertisementResp> findAllPublish(final Integer page, final Integer size, AdvertisementReqParams advertisementReqParams);

    PageWrapper<AdvertisementResp> findAllForAdmin(final Integer page, final Integer size, AdvertisementReqParams advertisementReqParams);

    AdvertisementResp udpate(AdvertisementUpdateReq req);
}
