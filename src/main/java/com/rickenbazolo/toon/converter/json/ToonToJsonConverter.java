package com.rickenbazolo.toon.converter.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rickenbazolo.toon.core.ToonDecoder;
import com.rickenbazolo.toon.core.ToonDecoder.ToonParseException;

import java.io.IOException;

/**
 * Converts TOON documents to JSON format.
 *
 * <p>This converter transforms TOON (Token-Oriented Object Notation) documents
 * into JSON format, providing interoperability with JSON-based systems and tools.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * // Basic conversion with default options (pretty-printed)
 * ToonToJsonConverter converter = new ToonToJsonConverter();
 * String json = converter.convert("user:\n  name: Alice\n  age: 30");
 *
 * // Custom conversion options (compact JSON)
 * ToonToJsonOptions options = ToonToJsonOptions.builder()
 *     .prettyPrint(false)
 *     .build();
 * ToonToJsonConverter customConverter = new ToonToJsonConverter(options);
 * String compactJson = customConverter.convert(toonString);
 * }</pre>
 *
 * @since 0.2.0
 * @author Ricken Bazolo
 * @see ToonToJsonOptions
 * @see JsonToToonConverter
 */
public class ToonToJsonConverter {

    private final ToonToJsonOptions options;
    private final ObjectMapper objectMapper;
    private final ToonDecoder decoder;

    /**
     * Creates a new converter with default options (pretty-printing enabled).
     */
    public ToonToJsonConverter() {
        this(ToonToJsonOptions.DEFAULT);
    }

    /**
     * Creates a new converter with custom options.
     *
     * @param options the conversion options to use
     */
    public ToonToJsonConverter(ToonToJsonOptions options) {
        this.options = options;
        this.objectMapper = new ObjectMapper();
        this.decoder = new ToonDecoder(options.toonOptions());
    }

    /**
     * Converts a TOON string to JSON format.
     *
     * @param toonString the TOON document as a string
     * @return the JSON representation of the TOON data
     * @throws ToonParseException if TOON decoding fails
     * @throws IOException if JSON serialization fails
     * @throws IllegalArgumentException if toonString is null
     */
    public String convert(String toonString) throws IOException {
        if (toonString == null) {
            throw new IllegalArgumentException("TOON string cannot be null");
        }

        var node = decoder.decode(toonString);

        if (options.prettyPrint()) {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
        } else {
            return objectMapper.writeValueAsString(node);
        }
    }

    /**
     * Returns the options used by this converter.
     *
     * @return the conversion options
     */
    public ToonToJsonOptions getOptions() {
        return options;
    }
}
