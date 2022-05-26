package by.iba.common.validation.annotation;


import by.iba.common.validation.validator.PhonesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PhonesValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ValidPhones {
    String message() default "Bad phone numbers";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
