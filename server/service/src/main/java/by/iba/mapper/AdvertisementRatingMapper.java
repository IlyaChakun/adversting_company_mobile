package by.iba.mapper;

import by.iba.common.mapper.core.FullAbstractMapper;
import by.iba.dto.req.advertisement.AdvertisementRatingReq;
import by.iba.dto.resp.advertisement.AdvertisementRatingResp;
import by.iba.entity.user.AdvertisementRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdvertisementRatingMapper extends FullAbstractMapper<AdvertisementRating, AdvertisementRatingResp, AdvertisementRatingReq> {

    @Autowired
    public AdvertisementRatingMapper() {
        super(AdvertisementRating.class, AdvertisementRatingResp.class);
    }

}
