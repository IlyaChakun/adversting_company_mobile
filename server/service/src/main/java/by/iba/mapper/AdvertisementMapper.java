package by.iba.mapper;

import by.iba.common.mapper.core.FullAbstractMapper;
import by.iba.dto.req.advertisement.AdvertisementReq;
import by.iba.dto.resp.advertisement.AdvertisementResp;
import by.iba.entity.user.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdvertisementMapper extends FullAbstractMapper<Advertisement, AdvertisementResp, AdvertisementReq> {

    @Autowired
    public AdvertisementMapper() {
        super(Advertisement.class, AdvertisementResp.class);
    }

}

