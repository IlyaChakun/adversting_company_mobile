package by.iba.common.dto;

import by.iba.common.dto.core.BaseApiResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IdApiResponse extends BaseApiResponse {

    private final Long id;

    public IdApiResponse(boolean success, Long id) {
        super(success);
        this.id = id;
    }

}