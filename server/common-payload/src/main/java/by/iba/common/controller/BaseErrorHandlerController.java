package by.iba.common.controller;

import by.iba.common.dto.ErrorWrapper;
import by.iba.common.exception.AbstractException;
import by.iba.common.exception.ErrorMessage;
import by.iba.common.exception.IllegalRequestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.*;

@RestControllerAdvice
@Slf4j
@AllArgsConstructor
public class BaseErrorHandlerController {


    private final MessageSource messageSource;


    @ExceptionHandler({IllegalRequestException.class})
    public ResponseEntity<ErrorWrapper<ErrorMessage>> handleValidation(IllegalRequestException e, Locale locale) {
        /*
         * Validation exceptions handling. Status code 400.
         */
        List<ErrorMessage> errors = new ArrayList<>();

        e.getErrors().forEach(er -> errors.add(
                new ErrorMessage(400, "exception." + er.getField() + ".not_valid",
                        getLocalizedMessage(er.getDefaultMessage(), locale)))
        );
//        if (Objects.nonNull(e.getMessage())) {
//            errors.add(0, e.getMessage());
//        }
        return new ResponseEntity<>(
                new ErrorWrapper<>(errors),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorWrapper<ErrorMessage>> handleValidationExceptions(MethodArgumentNotValidException ex, Locale locale) {

        Map<String, ErrorMessage> errorsMap = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {

                    if (errorsMap.containsKey(error.getField())) {
                        errorsMap.put(error.getField(), new ErrorMessage(400, error.getField(),
                                error.getCode()));
                    } else {
                        errorsMap.put(error.getField(), new ErrorMessage(400, error.getField(),
                                error.getCode()));
                    }
                }

        );
        List<ErrorMessage> errors = new ArrayList<>(errorsMap.values());
        return new ResponseEntity<>(
                new ErrorWrapper<>(errors),
                HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({HttpMessageNotReadableException.class, BindException.class,
            UnsatisfiedServletRequestParameterException.class, IllegalArgumentException.class,
            MethodArgumentTypeMismatchException.class, HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ErrorMessage> handleJsonMappingException(Exception ex, Locale locale) {
        /*
         * Exception occurs when passed id is null. Status 400.
         */


        ex.getStackTrace();
        log.error(ex.getMessage());

        final String localizedMessage = getLocalizedMessage("exception.param_not_valid", locale);

        return new ResponseEntity<>(
                new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "bad_request", localizedMessage),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AbstractException.class})
    public ResponseEntity<ErrorMessage> handleAbstractException(AbstractException ex, Locale locale) {
        /*
        Handles AbstractException exceptions.
        */


        final String localizedMessage = getLocalizedMessage(ex.getError(), locale);


        return
                new ResponseEntity<>(
                        new ErrorMessage(
                                ex.getHttpCode(),
                                ex.getError(),
                                localizedMessage
                        ),
                        HttpStatus.valueOf(ex.getHttpCode())
                );
    }

    private String getLocalizedMessage(final String error, final Locale locale) {

        return messageSource.getMessage(error, (Object[]) null, locale);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> processError(Exception e, Locale locale) {
        /*
            500
         */
        e.printStackTrace();
        log.error(e.getMessage());

        final String localizedMessage = getLocalizedMessage("exception.server_error", locale);

        return
                new ResponseEntity<>(
                        new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                "server_error",
                                localizedMessage),
                        HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
