package by.iba.dto.req.advertisement;

import by.iba.common.dto.core.BaseAbstractReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AdvertisementRatingReq extends BaseAbstractReq {

    private Integer rating;

    private String reviewMessage;

    private Long advertisementId;

}