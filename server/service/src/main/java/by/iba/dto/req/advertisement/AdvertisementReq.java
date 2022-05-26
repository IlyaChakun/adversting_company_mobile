package by.iba.dto.req.advertisement;

import by.iba.common.dto.core.BaseAbstractReq;
import by.iba.entity.user.AdvertisementStatus;
import by.iba.entity.user.AdvertisementType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AdvertisementReq extends BaseAbstractReq {

    private String title;

    private String body;

    private String picture;

    private AdvertisementType type;

    private AdvertisementStatus status;

    private Long userId;

}
