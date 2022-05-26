package by.iba.common.validation.validator;


import by.iba.common.validation.annotation.ValidPhone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.iba.common.validation.validator.CommonHolder.PHONE_PATTERN;

public class PhoneValidator implements ConstraintValidator<ValidPhone, String> {

    private static final Pattern PATTERN = Pattern.compile(PHONE_PATTERN);

    @Override
    public boolean isValid(final String phoneNumber, final ConstraintValidatorContext context) {
        if (Objects.nonNull(phoneNumber)) {
            return validatePhone(phoneNumber);
        } else {
            return false;
        }
    }

    private boolean validatePhone(final String phoneNumber) {
        final Matcher matcher = PATTERN.matcher(phoneNumber);
        return matcher.matches();
    }

}

