package by.iba.dto;

import by.iba.entity.user.AdvertisementType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
public class AdvertisementReqParams {

    private Boolean sortByRating = false;
    private Set<String> advertisementTypes = new HashSet<>();

}
