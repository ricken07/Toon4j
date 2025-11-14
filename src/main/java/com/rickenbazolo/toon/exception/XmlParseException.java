package com.rickenbazolo.toon.exception;

public class XmlParseException extends XmlException {
    private final String context;

    public XmlParseException(String message) {
        this(message, null, null);
    }

    public XmlParseException(String message, Throwable cause) {
        this(message, cause, null);
    }

    public XmlParseException(String message, Throwable cause, String context) {
        super(buildMessage(message, context), cause);
        this.context = context;
    }

    public String getContext() {
        return context;
    }

    private static String buildMessage(String message, String context) {
        if (context != null && !context.isEmpty()) {
            return message + " [context: " + context + "]";
        }
        return message;
    }
}
