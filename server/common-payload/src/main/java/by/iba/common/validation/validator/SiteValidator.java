package by.iba.common.validation.validator;

import by.iba.common.validation.annotation.ValidSite;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.iba.common.validation.validator.CommonHolder.SITE_PATTERN;

public class SiteValidator implements ConstraintValidator<ValidSite, String> {

    private static final Pattern PATTERN = Pattern.compile(SITE_PATTERN);

    @Override
    public boolean isValid(final String site, final ConstraintValidatorContext context) {
        if (Objects.nonNull(site)) {
            return validateSite(site);
        } else {
            return false;
        }
    }

    private boolean validateSite(final String site) {
        final Matcher matcher = PATTERN.matcher(site);
        return matcher.matches();
    }

}