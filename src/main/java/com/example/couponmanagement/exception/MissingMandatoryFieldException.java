package com.example.couponmanagement.exception;

public class MissingMandatoryFieldException extends RuntimeException {
    private static final long serialVersionUID = -7695082146058822324L;

    public MissingMandatoryFieldException(String message) {
        super(message);
    }

    public MissingMandatoryFieldException(String message, Throwable cause) {
        super(message, cause);
    }
}
