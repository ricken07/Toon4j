package com.rickenbazolo.toon.converter.xml;

import com.rickenbazolo.toon.config.ToonOptions;

public record ToonToXmlOptions(
    ToonOptions toonOptions,
    String rootElementName,
    String attributePrefix,
    String textNodeKey,
    boolean prettyPrint,
    int indent,
    boolean xmlDeclaration
) {
    public static final ToonToXmlOptions DEFAULT = new ToonToXmlOptions(
        ToonOptions.DEFAULT, "root", "@", "#text", true, 2, true
    );

    public ToonToXmlOptions {
        if (toonOptions == null) throw new IllegalArgumentException("ToonOptions cannot be null");
        if (rootElementName == null || rootElementName.isEmpty()) throw new IllegalArgumentException("Root element name cannot be null or empty");
        if (attributePrefix == null) throw new IllegalArgumentException("Attribute prefix cannot be null");
        if (textNodeKey == null || textNodeKey.isEmpty()) throw new IllegalArgumentException("Text node key cannot be null or empty");
        if (indent < 0) throw new IllegalArgumentException("Indent must be >= 0");
    }

    public static class Builder {
        private ToonOptions toonOptions = DEFAULT.toonOptions;
        private String rootElementName = DEFAULT.rootElementName;
        private String attributePrefix = DEFAULT.attributePrefix;
        private String textNodeKey = DEFAULT.textNodeKey;
        private boolean prettyPrint = DEFAULT.prettyPrint;
        private int indent = DEFAULT.indent;
        private boolean xmlDeclaration = DEFAULT.xmlDeclaration;

        public Builder toonOptions(ToonOptions opt) { this.toonOptions = opt; return this; }
        public Builder rootElementName(String name) { this.rootElementName = name; return this; }
        public Builder attributePrefix(String prefix) { this.attributePrefix = prefix; return this; }
        public Builder textNodeKey(String key) { this.textNodeKey = key; return this; }
        public Builder prettyPrint(boolean pretty) { this.prettyPrint = pretty; return this; }
        public Builder indent(int ind) { this.indent = ind; return this; }
        public Builder xmlDeclaration(boolean decl) { this.xmlDeclaration = decl; return this; }

        public ToonToXmlOptions build() {
            return new ToonToXmlOptions(toonOptions, rootElementName, attributePrefix, textNodeKey, prettyPrint, indent, xmlDeclaration);
        }
    }

    public static Builder builder() { return new Builder(); }
}
