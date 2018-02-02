package com.customer.exception;

/**
 * @author Vishal Nagpure
 */
public class ConfigurationException extends Exception {

    private String message;

    public ConfigurationException(String message, String message1) {
        super(message);
        this.message = message1;
    }

    public ConfigurationException(String message, Throwable cause, String message1) {
        super(message, cause);
        this.message = message1;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
