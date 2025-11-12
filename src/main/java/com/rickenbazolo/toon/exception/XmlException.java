package com.rickenbazolo.toon.exception;

public class XmlException extends RuntimeException {
    public XmlException(String message) {
        super(message);
    }

    public XmlException(String message, Throwable cause) {
        super(message, cause);
    }
}
