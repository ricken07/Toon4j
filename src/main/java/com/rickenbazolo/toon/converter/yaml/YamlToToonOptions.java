package com.rickenbazolo.toon.converter.yaml;

import com.rickenbazolo.toon.config.ToonOptions;

/**
 * Configuration options for YAML to TOON conversion.
 *
 * @param toonOptions TOON encoding options for the output
 * @param allowDuplicateKeys whether duplicate YAML keys are permitted
 * @param allowMultiDocument whether to parse multi-document YAML streams
 */
public record YamlToToonOptions(
    ToonOptions toonOptions,
    boolean allowDuplicateKeys,
    boolean allowMultiDocument
) {

    /**
     * Default options for YAML to TOON conversion.
     */
    public static final YamlToToonOptions DEFAULT = new YamlToToonOptions(
        ToonOptions.DEFAULT,
        false,
        false
    );

    /**
     * Validates the options.
     */
    public YamlToToonOptions {
        if (toonOptions == null) {
            throw new IllegalArgumentException("ToonOptions cannot be null");
        }
    }

    /**
     * Builder for creating YamlToToonOptions instances.
     */
    public static class Builder {
        private ToonOptions toonOptions = DEFAULT.toonOptions;
        private boolean allowDuplicateKeys = DEFAULT.allowDuplicateKeys;
        private boolean allowMultiDocument = DEFAULT.allowMultiDocument;

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
         * Controls whether duplicate YAML keys are allowed.
         *
         * @param allowDuplicateKeys true to allow duplicates, false to reject
         * @return this builder
         */
        public Builder allowDuplicateKeys(boolean allowDuplicateKeys) {
            this.allowDuplicateKeys = allowDuplicateKeys;
            return this;
        }

        /**
         * Controls whether multi-document YAML streams are accepted.
         *
         * @param allowMultiDocument true to parse multi-doc streams
         * @return this builder
         */
        public Builder allowMultiDocument(boolean allowMultiDocument) {
            this.allowMultiDocument = allowMultiDocument;
            return this;
        }

        /**
         * Builds the YamlToToonOptions instance.
         *
         * @return the configured options
         */
        public YamlToToonOptions build() {
            return new YamlToToonOptions(toonOptions, allowDuplicateKeys, allowMultiDocument);
        }
    }

    /**
     * Creates a new builder for YamlToToonOptions.
     *
     * @return a new builder instance
     */
    public static Builder builder() {
        return new Builder();
    }
}
