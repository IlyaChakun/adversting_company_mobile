package by.iba.common.domain;

import by.iba.common.domain.core.MappableObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Address extends MappableObject {

    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "address")
    private String address;

    @Column(name = "apartment")
    private Integer apartment;

}
