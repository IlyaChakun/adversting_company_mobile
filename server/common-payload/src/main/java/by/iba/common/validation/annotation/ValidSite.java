package by.iba.common.validation.annotation;

import by.iba.common.validation.validator.SiteValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = SiteValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ValidSite {

    String message() default "Bad site";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
