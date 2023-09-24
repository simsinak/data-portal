package com.sina.exception;

import com.sina.exception.enumeration.FailureReason;

/**
 * @author Sina Askarnejad
 */
public class DataNotFoundException extends BaseException {

    public DataNotFoundException(String message) {
        super(message);
    }

    @Override
    public FailureReason getReason() {
        return FailureReason.DATA_NOT_FOUND;
    }
}
