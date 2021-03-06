package by.iba.common.validation.validator;

import by.iba.common.validation.annotation.PositiveLong;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.nonNull;

public class PositiveLongValidator implements ConstraintValidator<PositiveLong, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (nonNull(value)) {
            try {
                return Long.parseLong(value.toString()) > 0;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

}