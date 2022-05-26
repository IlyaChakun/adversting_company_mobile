package by.iba.common.validation.validator;

import by.iba.common.validation.annotation.ValidPhones;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.iba.common.validation.validator.CommonHolder.PHONE_PATTERN;

public class PhonesValidator implements ConstraintValidator<ValidPhones, List<String>> {

    private static final Pattern PATTERN = Pattern.compile(PHONE_PATTERN);

    @Override
    public boolean isValid(final List<String> phoneNumbers, final ConstraintValidatorContext context) {
        return phoneNumbers.stream()
                .allMatch(phoneNumber -> Objects.nonNull(phoneNumber) && validatePhone(phoneNumber));
    }

    private boolean validatePhone(final String phoneNumber) {
        final Matcher matcher = PATTERN.matcher(phoneNumber);
        return matcher.matches();
    }

}
