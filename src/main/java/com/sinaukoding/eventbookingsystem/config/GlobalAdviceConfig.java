package com.sinaukoding.eventbookingsystem.config;

import com.sinaukoding.eventbookingsystem.model.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalAdviceConfig {

    @ExceptionHandler({ RuntimeException.class, Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse<?> handleExceptionInternalServerError(Exception e) {
        return BaseResponse.error(
                "Something bad in app server, please re-run your app or contact support!",
                e.getMessage()
        );
    }

    @ExceptionHandler({ HttpMessageNotReadableException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<?> handleExceptionBadRequest(Exception e) {
        return BaseResponse.badRequest(e.getMessage());
    }

    @ExceptionHandler({ AccessDeniedException.class })
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public BaseResponse<?> handleExceptionForbidden(AccessDeniedException e) {
        return BaseResponse.forbiddenAccess(
                "You don't have a permission to access this resource."
        );
    }

}
