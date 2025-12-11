package com.rickenbazolo.toon.exception;

/**
 * Exception thrown when YAML parsing fails.
 *
 * <p>Provides line and column context when available.</p>
 */
public class YamlParseException extends YamlException {

    private final long lineNumber;
    private final long columnNumber;

    /**
     * Constructs a new YAML parse exception with line and column information.
     *
     * @param message the detail message explaining the error
     * @param lineNumber the line number where the error occurred (1-based)
     * @param columnNumber the column number where the error occurred (1-based)
     */
    public YamlParseException(String message, long lineNumber, long columnNumber) {
        super(formatMessage(message, lineNumber, columnNumber));
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }

    /**
     * Constructs a new YAML parse exception with line and column information and a cause.
     *
     * @param message the detail message explaining the error
     * @param lineNumber the line number where the error occurred (1-based)
     * @param columnNumber the column number where the error occurred (1-based)
     * @param cause the cause of this exception
     */
    public YamlParseException(String message, long lineNumber, long columnNumber, Throwable cause) {
        super(formatMessage(message, lineNumber, columnNumber), cause);
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }

    /**
     * Gets the line number where the parsing error occurred.
     *
     * @return the line number (1-based), or -1 if not available
     */
    public long getLineNumber() {
        return lineNumber;
    }

    /**
     * Gets the column number where the parsing error occurred.
     *
     * @return the column number (1-based), or -1 if not available
     */
    public long getColumnNumber() {
        return columnNumber;
    }

    private static String formatMessage(String message, long lineNumber, long columnNumber) {
        if (lineNumber > 0 && columnNumber > 0) {
            return String.format("%s at line %d, column %d", message, lineNumber, columnNumber);
        }
        return message;
    }
}
