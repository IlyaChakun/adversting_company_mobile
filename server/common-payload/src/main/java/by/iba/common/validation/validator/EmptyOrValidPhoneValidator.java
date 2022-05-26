package by.iba.common.validation.validator;




import by.iba.common.validation.annotation.EmptyOrValidPhone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.iba.common.validation.validator.CommonHolder.PHONE_PATTERN;

public class EmptyOrValidPhoneValidator implements ConstraintValidator<EmptyOrValidPhone, String> {


    private static final Pattern PATTERN = Pattern.compile(PHONE_PATTERN);

    @Override
    public boolean isValid(final String phoneNumber, final ConstraintValidatorContext context) {
        if (Objects.isNull(phoneNumber) || phoneNumber.isEmpty()) {
            return true;
        }
        return (validatePhone(phoneNumber));
    }

    private boolean validatePhone(final String phoneNumber) {
        Matcher matcher = PATTERN.matcher(phoneNumber);
        return matcher.matches();
    }

}

