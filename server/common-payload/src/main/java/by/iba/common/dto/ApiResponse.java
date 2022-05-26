package by.iba.common.dto;

import by.iba.common.dto.core.BaseApiResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse extends BaseApiResponse {

    private final String message;

    public ApiResponse(boolean success, String message) {
        super(success);
        this.message = message;
    }

}
