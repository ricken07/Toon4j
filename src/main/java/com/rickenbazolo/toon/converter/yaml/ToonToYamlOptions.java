package com.rickenbazolo.toon.converter.yaml;

import com.rickenbazolo.toon.config.ToonOptions;
import org.snakeyaml.engine.v2.common.FlowStyle;

/**
 * Configuration options for TOON to YAML conversion.
 *
 * @param toonOptions TOON decoding options for parsing
 * @param indent number of spaces to use for indentation
 * @param width preferred line width before folding
 * @param flowStyle whether to emit block or flow YAML
 * @param explicitStart whether to emit an explicit document start marker (---)
 * @param explicitEnd whether to emit an explicit document end marker (...)
 */
public record ToonToYamlOptions(
    ToonOptions toonOptions,
    int indent,
    int width,
    FlowStyle flowStyle,
    boolean explicitStart,
    boolean explicitEnd
) {

    /**
     * Default options for TOON to YAML conversion.
     */
    public static final ToonToYamlOptions DEFAULT = new ToonToYamlOptions(
        ToonOptions.DEFAULT,
        2,
        80,
        FlowStyle.BLOCK,
        false,
        false
    );

    /**
     * Validates the options.
     */
    public ToonToYamlOptions {
        if (toonOptions == null) {
            throw new IllegalArgumentException("ToonOptions cannot be null");
        }
        if (indent < 1) {
            throw new IllegalArgumentException("Indent must be at least 1");
        }
        if (width < indent) {
            throw new IllegalArgumentException("Width must be greater than indent");
        }
        if (flowStyle == null) {
            throw new IllegalArgumentException("FlowStyle cannot be null");
        }
    }

    /**
     * Builder for creating ToonToYamlOptions instances.
     */
    public static class Builder {
        private ToonOptions toonOptions = DEFAULT.toonOptions;
        private int indent = DEFAULT.indent;
        private int width = DEFAULT.width;
        private FlowStyle flowStyle = DEFAULT.flowStyle;
        private boolean explicitStart = DEFAULT.explicitStart;
        private boolean explicitEnd = DEFAULT.explicitEnd;

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
         * Sets the indentation size.
         *
         * @param indent number of spaces
         * @return this builder
         */
        public Builder indent(int indent) {
            this.indent = indent;
            return this;
        }

        /**
         * Sets the preferred line width before folding.
         *
         * @param width the preferred width
         * @return this builder
         */
        public Builder width(int width) {
            this.width = width;
            return this;
        }

        /**
         * Sets the YAML flow style.
         *
         * @param flowStyle block or flow
         * @return this builder
         */
        public Builder flowStyle(FlowStyle flowStyle) {
            this.flowStyle = flowStyle;
            return this;
        }

        /**
         * Sets whether to emit an explicit document start marker (---).
         *
         * @param explicitStart true to emit the start marker
         * @return this builder
         */
        public Builder explicitStart(boolean explicitStart) {
            this.explicitStart = explicitStart;
            return this;
        }

        /**
         * Sets whether to emit an explicit document end marker (...).
         *
         * @param explicitEnd true to emit the end marker
         * @return this builder
         */
        public Builder explicitEnd(boolean explicitEnd) {
            this.explicitEnd = explicitEnd;
            return this;
        }

        /**
         * Builds the ToonToYamlOptions instance.
         *
         * @return the configured options
         */
        public ToonToYamlOptions build() {
            return new ToonToYamlOptions(toonOptions, indent, width, flowStyle, explicitStart, explicitEnd);
        }
    }

    /**
     * Creates a new builder for ToonToYamlOptions.
     *
     * @return a new builder instance
     */
    public static Builder builder() {
        return new Builder();
    }
}
