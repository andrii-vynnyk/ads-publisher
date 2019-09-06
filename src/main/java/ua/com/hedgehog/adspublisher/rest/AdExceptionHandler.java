package ua.com.hedgehog.adspublisher.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.com.hedgehog.adspublisher.rest.exception.AdNotFoundException;
import ua.com.hedgehog.adspublisher.rest.exception.ErrorResponse;

@RestControllerAdvice(assignableTypes = AdsController.class)
public class AdExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = AdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse campaignNotFound(AdNotFoundException exception) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
}
