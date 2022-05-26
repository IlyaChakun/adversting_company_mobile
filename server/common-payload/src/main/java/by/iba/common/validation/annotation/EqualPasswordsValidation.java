package by.iba.common.validation.annotation;


import by.iba.common.validation.validator.EqualPasswordsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EqualPasswordsValidator.class)
public @interface EqualPasswordsValidation {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
