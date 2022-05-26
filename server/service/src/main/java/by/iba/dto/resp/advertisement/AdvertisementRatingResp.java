package by.iba.dto.resp.advertisement;

import by.iba.common.dto.core.FullAbstractResp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AdvertisementRatingResp extends FullAbstractResp {

    private Integer rating;

    private String reviewMessage;

}