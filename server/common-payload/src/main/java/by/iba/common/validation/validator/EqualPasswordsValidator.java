package by.iba.common.validation.validator;

import by.iba.common.validation.annotation.EqualPasswordsValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class EqualPasswordsValidator implements
        ConstraintValidator<EqualPasswordsValidation, Object> {

    @Override
    public void initialize(EqualPasswordsValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Method getPassMethod = value.getClass().getMethod("getNewPassword");
            Method getConfirmedPassMethod = value.getClass().getMethod("getConfirmedPassword");

            if (Objects.isNull(getConfirmedPassMethod.invoke(value)) ||
                    Objects.isNull(getPassMethod.invoke(value))) {
                return false;

            } else return getConfirmedPassMethod.invoke(value).equals(getPassMethod.invoke(value));

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
}
