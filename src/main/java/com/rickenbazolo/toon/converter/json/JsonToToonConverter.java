package com.rickenbazolo.toon.converter.json;

import tools.jackson.databind.json.JsonMapper;
import com.rickenbazolo.toon.core.ToonEncoder;

import java.io.IOException;

/**
 * Converts JSON documents to TOON format.
 *
 * <p>This converter transforms JSON documents into TOON (Token-Oriented Object Notation)
 * format, providing a more compact and LLM-friendly representation of JSON data.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * // Basic conversion with default options
 * JsonToToonConverter converter = new JsonToToonConverter();
 * String toon = converter.convert("{\"name\":\"Alice\",\"age\":30}");
 *
 * // Custom conversion options
 * JsonToToonOptions options = JsonToToonOptions.builder()
 *     .toonOptions(ToonOptions.builder().indent(4).build())
 *     .build();
 * JsonToToonConverter customConverter = new JsonToToonConverter(options);
 * String customToon = customConverter.convert(jsonString);
 * }</pre>
 *
 * @since 0.2.0
 * @author Ricken Bazolo
 * @see JsonToToonOptions
 * @see ToonToJsonConverter
 */
public class JsonToToonConverter {

    private final JsonToToonOptions options;
    private final JsonMapper jsonMapper;
    private final ToonEncoder encoder;

    /**
     * Creates a new converter with default options.
     */
    public JsonToToonConverter() {
        this(JsonToToonOptions.DEFAULT);
    }

    /**
     * Creates a new converter with custom options.
     *
     * @param options the conversion options to use
     */
    public JsonToToonConverter(JsonToToonOptions options) {
        this.options = options;
        this.jsonMapper = new JsonMapper();
        this.encoder = new ToonEncoder(options.toonOptions());
    }

    /**
     * Converts a JSON string to TOON format.
     *
     * @param jsonString the JSON document as a string (must be valid JSON)
     * @return the TOON representation of the JSON data
     * @throws IOException if JSON parsing fails
     * @throws IllegalArgumentException if jsonString is null
     */
    public String convert(String jsonString) throws IOException {
        if (jsonString == null) {
            throw new IllegalArgumentException("JSON string cannot be null");
        }

        var node = jsonMapper.readTree(jsonString);
        return encoder.encode(node);
    }

    /**
     * Returns the options used by this converter.
     *
     * @return the conversion options
     */
    public JsonToToonOptions getOptions() {
        return options;
    }
}
