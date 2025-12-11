package com.rickenbazolo.toon.converter.yaml;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ToonToYamlConverterTest {

    @Test
    void convertsToonToYaml() {
        String toon = """
                user:
                  name: Bob
                    age: 28
                """;

        var converter = new ToonToYamlConverter();
        String yaml = converter.convert(toon);

        assertThat(yaml).contains("user");
        assertThat(yaml).contains("name: Bob");
        assertThat(yaml).contains("age: 28");
    }

    @Test
    void emitsFlowStyleWhenConfigured() {
        String toon = "numbers[3]: 1,2,3";

        var options = ToonToYamlOptions.builder()
            .flowStyle(org.snakeyaml.engine.v2.common.FlowStyle.FLOW)
            .build();

        var converter = new ToonToYamlConverter(options);
        String yaml = converter.convert(toon);

        assertThat(yaml).contains("[1, 2, 3]");
    }
}
