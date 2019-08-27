package ua.com.hedgehog.adspublisher.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.com.hedgehog.adspublisher.rest.exception.CampaignNotFoundException;
import ua.com.hedgehog.adspublisher.rest.exception.ErrorResponse;

@RestControllerAdvice(assignableTypes = CampaignsController.class)
public class CampaignExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = CampaignNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse campaignNotFound(CampaignNotFoundException exception) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
}
