package com.rickenbazolo.toon.converter.json;

import com.rickenbazolo.toon.config.ToonOptions;

/**
 * Configuration options for JSON to TOON conversion.
 *
 * <p>This record class encapsulates the configuration parameters for converting
 * JSON documents to TOON format. Currently, it only wraps ToonOptions since
 * JSON parsing is handled by Jackson with standard settings.</p>
 *
 * @param toonOptions the TOON encoding options to use for output
 *
 * @since 0.2.0
 * @author Ricken Bazolo
 * @see ToonOptions
 * @see JsonToToonConverter
 */
public record JsonToToonOptions(ToonOptions toonOptions) {

    /**
     * Default options for JSON to TOON conversion.
     */
    public static final JsonToToonOptions DEFAULT = new JsonToToonOptions(ToonOptions.DEFAULT);

    /**
     * Validates the options.
     */
    public JsonToToonOptions {
        if (toonOptions == null) {
            throw new IllegalArgumentException("ToonOptions cannot be null");
        }
    }

    /**
     * Builder for creating JsonToToonOptions instances.
     */
    public static class Builder {
        private ToonOptions toonOptions = DEFAULT.toonOptions;

        /**
         * Sets the TOON encoding options.
         *
         * @param toonOptions the TOON options to use
         * @return this builder
         */
        public Builder toonOptions(ToonOptions toonOptions) {
            this.toonOptions = toonOptions;
            return this;
        }

        /**
         * Builds the JsonToToonOptions instance.
         *
         * @return the configured options
         */
        public JsonToToonOptions build() {
            return new JsonToToonOptions(toonOptions);
        }
    }

    /**
     * Creates a new builder for JsonToToonOptions.
     *
     * @return a new builder instance
     */
    public static Builder builder() {
        return new Builder();
    }
}
