package com.sina.exception;

import com.sina.exception.enumeration.FailureReason;

/**
 * @author Sina Askarnejad
 */
public abstract class BaseException extends Exception {

    public BaseException(String message, Exception exception) {
        super(message, exception);
    }

    public BaseException(String message) {
        super(message);
    }

    public abstract FailureReason getReason();
}
