package by.iba.dto.req.advertisement;

import by.iba.common.dto.core.BaseAbstractReq;
import by.iba.entity.user.AdvertisementStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AdvertisementUpdateReq extends BaseAbstractReq {

    private Long advertisementId;

    private AdvertisementStatus status;

}
