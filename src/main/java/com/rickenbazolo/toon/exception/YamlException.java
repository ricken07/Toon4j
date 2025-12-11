package com.rickenbazolo.toon.exception;

/**
 * Base exception for YAML-related errors.
 *
 * <p>This exception is thrown when YAML parsing, generation, or conversion
 * operations encounter unrecoverable errors.</p>
 */
public class YamlException extends RuntimeException {

    /**
     * Constructs a new YAML exception with the specified detail message.
     *
     * @param message the detail message explaining the error
     */
    public YamlException(String message) {
        super(message);
    }

    /**
     * Constructs a new YAML exception with the specified detail message and cause.
     *
     * @param message the detail message explaining the error
     * @param cause the cause of this exception
     */
    public YamlException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new YAML exception with the specified cause.
     *
     * @param cause the cause of this exception
     */
    public YamlException(Throwable cause) {
        super(cause);
    }
}
