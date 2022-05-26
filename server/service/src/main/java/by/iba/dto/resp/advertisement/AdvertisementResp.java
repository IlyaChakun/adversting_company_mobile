package by.iba.dto.resp.advertisement;

import by.iba.common.dto.core.FullAbstractResp;
import by.iba.dto.resp.user.UserResp;
import by.iba.entity.user.AdvertisementStatus;
import by.iba.entity.user.AdvertisementType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class AdvertisementResp extends FullAbstractResp {

    private String title;

    private String body;

    private String picture;

    private AdvertisementType type;

    private AdvertisementStatus status;

    private Set<AdvertisementRatingResp> ratingSet = new HashSet<>();

    private Double ratingSum;

    private UserResp user;
}
