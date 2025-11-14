package com.rickenbazolo.toon.converter.xml;

import com.rickenbazolo.toon.config.ToonOptions;

public record XmlToToonOptions(
    ToonOptions toonOptions,
    boolean includeAttributes,
    String attributePrefix,
    String textNodeKey,
    ArrayDetection arrayDetection
) {
    public enum ArrayDetection {
        AUTO, NEVER, ALWAYS
    }

    public static final XmlToToonOptions DEFAULT = new XmlToToonOptions(
        ToonOptions.DEFAULT, true, "@", "#text", ArrayDetection.AUTO
    );

    public XmlToToonOptions {
        if (toonOptions == null) throw new IllegalArgumentException("ToonOptions cannot be null");
        if (attributePrefix == null) throw new IllegalArgumentException("Attribute prefix cannot be null");
        if (textNodeKey == null || textNodeKey.isEmpty()) throw new IllegalArgumentException("Text node key cannot be null or empty");
        if (arrayDetection == null) throw new IllegalArgumentException("Array detection cannot be null");
    }

    public static class Builder {
        private ToonOptions toonOptions = DEFAULT.toonOptions;
        private boolean includeAttributes = DEFAULT.includeAttributes;
        private String attributePrefix = DEFAULT.attributePrefix;
        private String textNodeKey = DEFAULT.textNodeKey;
        private ArrayDetection arrayDetection = DEFAULT.arrayDetection;

        public Builder toonOptions(ToonOptions opt) { this.toonOptions = opt; return this; }
        public Builder includeAttributes(boolean inc) { this.includeAttributes = inc; return this; }
        public Builder attributePrefix(String prefix) { this.attributePrefix = prefix; return this; }
        public Builder textNodeKey(String key) { this.textNodeKey = key; return this; }
        public Builder arrayDetection(ArrayDetection det) { this.arrayDetection = det; return this; }

        public XmlToToonOptions build() {
            return new XmlToToonOptions(toonOptions, includeAttributes, attributePrefix, textNodeKey, arrayDetection);
        }
    }

    public static Builder builder() { return new Builder(); }
}
