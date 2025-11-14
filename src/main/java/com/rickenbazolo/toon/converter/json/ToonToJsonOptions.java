package com.rickenbazolo.toon.converter.json;

import com.rickenbazolo.toon.config.ToonOptions;

/**
 * Configuration options for TOON to JSON conversion.
 *
 * <p>This record class encapsulates the configuration parameters for converting
 * TOON documents to JSON format.</p>
 *
 * @param toonOptions the TOON decoding options to use for parsing
 * @param prettyPrint whether to format the output JSON with indentation
 *
 * @since 0.2.0
 * @author Ricken Bazolo
 * @see ToonOptions
 * @see ToonToJsonConverter
 */
public record ToonToJsonOptions(
    ToonOptions toonOptions,
    boolean prettyPrint
) {

    /**
     * Default options for TOON to JSON conversion (with pretty-printing enabled).
     */
    public static final ToonToJsonOptions DEFAULT = new ToonToJsonOptions(ToonOptions.DEFAULT, true);

    /**
     * Validates the options.
     */
    public ToonToJsonOptions {
        if (toonOptions == null) {
            throw new IllegalArgumentException("ToonOptions cannot be null");
        }
    }

    /**
     * Builder for creating ToonToJsonOptions instances.
     */
    public static class Builder {
        private ToonOptions toonOptions = DEFAULT.toonOptions;
        private boolean prettyPrint = DEFAULT.prettyPrint;

        /**
         * Sets the TOON decoding options.
         *
         * @param toonOptions the TOON options to use
         * @return this builder
         */
        public Builder toonOptions(ToonOptions toonOptions) {
            this.toonOptions = toonOptions;
            return this;
        }

        /**
         * Sets whether to format the output JSON with indentation.
         *
         * @param prettyPrint true to enable pretty printing, false otherwise
         * @return this builder
         */
        public Builder prettyPrint(boolean prettyPrint) {
            this.prettyPrint = prettyPrint;
            return this;
        }

        /**
         * Builds the ToonToJsonOptions instance.
         *
         * @return the configured options
         */
        public ToonToJsonOptions build() {
            return new ToonToJsonOptions(toonOptions, prettyPrint);
        }
    }

    /**
     * Creates a new builder for ToonToJsonOptions.
     *
     * @return a new builder instance
     */
    public static Builder builder() {
        return new Builder();
    }
}
