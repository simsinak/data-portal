package com.sina.exception;

import com.sina.exception.enumeration.FailureReason;

/**
 * @author Sina Askarnejad
 */
public class InternalServerException extends BaseException {

    public InternalServerException(String message, Exception exception) {
        super(message, exception);
    }

    @Override
    public FailureReason getReason() {
        return FailureReason.INTERNAL_SERVER_ERROR;
    }
}