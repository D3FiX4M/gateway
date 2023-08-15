package ru.microsservices.gateway.config.exception;

import lombok.Getter;

@Getter
public class ExtendException extends RuntimeException {

    private final ExtendError error;

    private ExtendException(ExtendError error, String message) {
        super(message);
        this.error = error;
    }

    public static ExtendException of(ExtendError error, String message) {
        throw new ExtendException(error, message);
    }

    public static ExtendException of(Exception exception) {
        if (exception.getClass()
                .isAssignableFrom(ExtendException.class)) {
            throw (ExtendException) exception;
        }
        throw new ExtendException(ExtendError.UNKNOWN_ERROR, exception.getMessage());
    }
}
