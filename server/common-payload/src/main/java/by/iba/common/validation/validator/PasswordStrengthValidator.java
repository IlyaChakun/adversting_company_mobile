package by.iba.common.validation.validator;

import by.iba.common.validation.annotation.PasswordStrengthValidation;
import lombok.extern.slf4j.Slf4j;
import org.passay.MessageResolver;
import org.passay.PropertiesMessageResolver;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Slf4j
public class PasswordStrengthValidator implements ConstraintValidator<PasswordStrengthValidation, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Properties props = new Properties();

        InputStream inputStream = getClass()

                .getClassLoader().getResourceAsStream("passay.properties");

        try {
            props.load(inputStream);
        } catch (IOException e) {
            log.error("Bad password validation", e);
        }

        MessageResolver resolver = new PropertiesMessageResolver(props);


//        PasswordValidator validator = new PasswordValidator(resolver, Arrays.asList(
//
//                new LengthRule(8, 16),
//                new CharacterRule(EnglishCharacterData.UpperCase, 1),
//
//                new CharacterRule(EnglishCharacterData.LowerCase, 1),
//
//
//                new CharacterRule(EnglishCharacterData.Digit, 1),
//
//                new CharacterRule(EnglishCharacterData.Special, 1),
//
//                new WhitespaceRule(),
//
//                new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),
//
//                new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false)
//
//        ));

        // RuleResult result = validator.validate(new PasswordData(value.toString()));


//        if (result.isValid()) {
//            return true;
//        }

//        List<String> messages = validator.getMessages(result);
//        String messageTemplate = String.join(",", messages);
//        context.buildConstraintViolationWithTemplate(messageTemplate)
//                .addConstraintViolation()
//                .disableDefaultConstraintViolation();

        String password = value.toString();
        if (Objects.nonNull(password) && password.length() > 5) {
            return true;
        }

        List<String> messages = new ArrayList<>();
        messages.add("Password should not be null and bigger than 5");

        String messageTemplate = String.join(",", messages);
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }

}
