package com.sina.controller;


import com.sina.dto.ErrorDto;
import com.sina.exception.BaseException;
import com.sina.exception.DataNotFoundException;
import com.sina.exception.InternalServerException;
import com.sina.exception.enumeration.FailureReason;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

/**
 * @author Sina Askarnejad
 */
@RestControllerAdvice(basePackages = "com.sina.controller")
@RequiredArgsConstructor
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = {InternalServerException.class, BaseException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto convertInternalException(BaseException exception){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setReason(exception.getReason().name());
        errorDto.setCode(500);
        return errorDto;
    }

    @ExceptionHandler(value = {DataNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto convertException(BaseException exception){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setReason(exception.getReason().name());
        errorDto.setCode(404);
        return errorDto;
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto convertCommonException(Exception exception){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setReason(FailureReason.UNKNOWN_ERROR.name());
        errorDto.setCode(500);
        return errorDto;
    }
}
