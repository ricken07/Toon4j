package com.rickenbazolo.toon.converter.yaml;

import com.rickenbazolo.toon.core.ToonEncoder;
import com.rickenbazolo.toon.exception.YamlException;
import com.rickenbazolo.toon.exception.YamlParseException;
import org.snakeyaml.engine.v2.api.Load;
import org.snakeyaml.engine.v2.api.LoadSettings;
import org.snakeyaml.engine.v2.exceptions.YamlEngineException;
import tools.jackson.databind.json.JsonMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Converts YAML documents to TOON format.
 */
public class YamlToToonConverter {

    private final YamlToToonOptions options;
    private final JsonMapper jsonMapper;
    private final ToonEncoder encoder;

    /**
     * Creates a new converter with default options.
     */
    public YamlToToonConverter() {
        this(YamlToToonOptions.DEFAULT);
    }

    /**
     * Creates a new converter with custom options.
     *
     * @param options the conversion options to use
     */
    public YamlToToonConverter(YamlToToonOptions options) {
        if (options == null) {
            throw new IllegalArgumentException("YamlToToonOptions cannot be null");
        }
        this.options = options;
        this.jsonMapper = new JsonMapper();
        this.encoder = new ToonEncoder(options.toonOptions());
    }

    /**
     * Converts a YAML string to TOON format.
     *
     * @param yamlString the YAML content as a string
     * @return the TOON representation
     */
    public String convert(String yamlString) {
        if (yamlString == null) {
            throw new IllegalArgumentException("YAML string cannot be null");
        }

        try {
            var yamlData = parseYamlString(yamlString);
            var node = jsonMapper.valueToTree(yamlData);
            return encoder.encode(node);
        } catch (YamlException e) {
            throw e;
        } catch (YamlEngineException e) {
            throw new YamlParseException(e.getMessage(), -1, -1, e);
        } catch (Exception e) {
            throw new YamlException("Failed to convert YAML to TOON: " + e.getMessage(), e);
        }
    }

    /**
     * Converts a YAML file to TOON format.
     *
     * @param yamlFile the YAML file to parse
     * @return the TOON representation
     * @throws IOException if file reading fails
     */
    public String convert(File yamlFile) throws IOException {
        if (yamlFile == null) {
            throw new IllegalArgumentException("YAML file cannot be null");
        }
        var content = Files.readString(yamlFile.toPath());
        return convert(content);
    }

    /**
     * Converts a YAML input stream to TOON format.
     *
     * @param yamlStream the YAML input stream
     * @return the TOON representation
     * @throws IOException if reading fails
     */
    public String convert(InputStream yamlStream) throws IOException {
        if (yamlStream == null) {
            throw new IllegalArgumentException("YAML stream cannot be null");
        }
        var content = new String(yamlStream.readAllBytes(), StandardCharsets.UTF_8);
        return convert(content);
    }

    private Object parseYamlString(String yamlString) {
        var settings = LoadSettings.builder()
            .setLabel("toon4j-yaml")
            .setAllowDuplicateKeys(options.allowDuplicateKeys())
            .build();

        var loader = new Load(settings);

        if (options.allowMultiDocument()) {
            var documents = new ArrayList<>();
            for (Object doc : loader.loadAllFromString(yamlString)) {
                documents.add(doc);
            }
            return documents;
        }

        return loader.loadFromString(yamlString);
    }
}
