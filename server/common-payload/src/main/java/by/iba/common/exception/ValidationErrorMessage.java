package by.iba.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public final class ValidationErrorMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String errorField;

    private String errorDescription;

}
