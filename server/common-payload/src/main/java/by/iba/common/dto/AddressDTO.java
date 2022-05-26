package by.iba.common.dto;

import by.iba.common.dto.core.MappableObjectDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AddressDTO extends MappableObjectDTO {

    @NotNull(message = "validation.company.country.not_presented")//TODO VALIDATION
    private Long countryId;

    @NotNull(message = "validation.company.city.not_presented")//TODO VALIDATION
    private Long cityId;

    @NotBlank(message = "validation.company.address.not_presented")//TODO VALIDATION
    private String address;

    @Min(value = 1, message = "validation.company.apartment.number.less_then_min")
    @Max(value = 5000, message = "validation.company.apartment.number.more_then_max")
    private Integer apartment;

}
