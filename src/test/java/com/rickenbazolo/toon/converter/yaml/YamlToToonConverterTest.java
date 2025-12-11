package com.rickenbazolo.toon.converter.yaml;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class YamlToToonConverterTest {

    @Test
    void convertsBasicYamlToToon() {
        String yaml = """
                user:
                  name: Alice
                  age: 30
                """;

        var converter = new YamlToToonConverter();
        String toon = converter.convert(yaml);

        assertThat(toon).contains("user");
        assertThat(toon).contains("Alice");
        assertThat(toon).contains("30");
    }

    @Test
    void handlesMultiDocumentStreamWhenEnabled() {
        String yaml = """
                ---
                name: app
                ---
                name: worker
                """;

        var options = YamlToToonOptions.builder()
            .allowMultiDocument(true)
            .build();

        var converter = new YamlToToonConverter(options);
        String toon = converter.convert(yaml);

        assertThat(toon).contains("[2]");
        assertThat(toon).contains("app");
        assertThat(toon).contains("worker");
    }

    @Test
    void convertsYamlFileResource() throws Exception {
        var resource = getClass().getClassLoader().getResource("yaml/sample-application.yaml");
        assertThat(resource).isNotNull();

        var converter = new YamlToToonConverter();
        String toon = converter.convert(new java.io.File(resource.toURI()));

        assertThat(toon).contains("app");
        assertThat(toon).contains("catalog");
        assertThat(toon).contains("replicas");
        assertThat(toon).contains("JAVA_OPTS");
    }
}
