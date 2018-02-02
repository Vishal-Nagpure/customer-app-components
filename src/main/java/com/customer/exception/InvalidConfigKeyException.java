package com.customer.exception;

/**
 * @author Vishal Nagpure
 */
public class InvalidConfigKeyException extends Exception {

    private String message;

    public InvalidConfigKeyException(String message) {
        super(message);
        this.message = message;
    }

    public InvalidConfigKeyException(String message, Throwable cause, String message1) {
        super(message, cause);
        this.message = message1;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
