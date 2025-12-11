package com.rickenbazolo.toon.converter.yaml;

import com.rickenbazolo.toon.core.ToonDecoder;
import com.rickenbazolo.toon.exception.YamlException;
import org.snakeyaml.engine.v2.api.Dump;
import org.snakeyaml.engine.v2.api.DumpSettings;
import org.snakeyaml.engine.v2.common.FlowStyle;
import tools.jackson.databind.json.JsonMapper;

/**
 * Converts TOON documents to YAML format.
 */
public class ToonToYamlConverter {

    private final ToonToYamlOptions options;
    private final JsonMapper jsonMapper;
    private final ToonDecoder decoder;

    /**
     * Creates a new converter with default options.
     */
    public ToonToYamlConverter() {
        this(ToonToYamlOptions.DEFAULT);
    }

    /**
     * Creates a new converter with custom options.
     *
     * @param options the conversion options to use
     */
    public ToonToYamlConverter(ToonToYamlOptions options) {
        if (options == null) {
            throw new IllegalArgumentException("ToonToYamlOptions cannot be null");
        }
        this.options = options;
        this.jsonMapper = new JsonMapper();
        this.decoder = new ToonDecoder(options.toonOptions());
    }

    /**
     * Converts a TOON string to YAML format.
     *
     * @param toonString the TOON document
     * @return the YAML representation
     */
    public String convert(String toonString) {
        if (toonString == null) {
            throw new IllegalArgumentException("TOON string cannot be null");
        }

        try {
            var node = decoder.decode(toonString);
            var value = jsonMapper.treeToValue(node, Object.class);

            var settings = DumpSettings.builder()
                .setDefaultFlowStyle(options.flowStyle())
                .setIndent(options.indent())
                .setWidth(options.width())
                .setExplicitStart(options.explicitStart())
                .setExplicitEnd(options.explicitEnd())
                .build();

            var dumper = new Dump(settings);
            return dumper.dumpToString(value);
        } catch (Exception e) {
            throw new YamlException("Failed to convert TOON to YAML: " + e.getMessage(), e);
        }
    }

    /**
     * Returns the options used by this converter.
     *
     * @return the conversion options
     */
    public ToonToYamlOptions getOptions() {
        return options;
    }
}
