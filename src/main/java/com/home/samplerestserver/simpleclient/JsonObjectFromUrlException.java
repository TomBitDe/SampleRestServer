package com.home.samplerestserver.simpleclient;

/**
 * Signals that a JSON object could not be read from an URL.
 */
public class JsonObjectFromUrlException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final int statusCode;

    /**
     * Creates an exception for an unexpected HTTP status.
     *
     * @param message the detail message
     * @param statusCode the received HTTP status code
     */
    public JsonObjectFromUrlException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    /**
     * Creates an exception for an I/O problem.
     *
     * @param message the detail message
     * @param cause the underlying cause
     */
    public JsonObjectFromUrlException(String message, Throwable cause) {
        super(message, cause);
        this.statusCode = -1;
    }

    /**
     * Gets the HTTP status code that caused this exception.
     *
     * @return the HTTP status code or -1 if the exception was not caused by a status code
     */
    public int getStatusCode() {
        return statusCode;
    }
}
