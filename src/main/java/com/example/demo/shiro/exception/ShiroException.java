package com.example.demo.shiro.exception;

public class ShiroException extends RuntimeException {

    /**
     * Creates a new ShiroException.
     */
    public ShiroException() {
        super();
    }

    /**
     * Constructs a new ShiroException.
     *
     * @param message the reason for the exception
     */
    public ShiroException(String message) {
        super(message);
    }

    /**
     * Constructs a new ShiroException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public ShiroException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new ShiroException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public ShiroException(String message, Throwable cause) {
        super(message, cause);
    }

}