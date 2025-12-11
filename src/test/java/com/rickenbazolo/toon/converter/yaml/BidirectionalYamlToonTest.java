package com.rickenbazolo.toon.converter.yaml;

import com.rickenbazolo.toon.Toon;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BidirectionalYamlToonTest {

    @Test
    void roundTripPreservesContent() {
        String yaml = """
                app:
                  name: catalog
                  replicas: 3
                  image:
                    repository: example/catalog
                    tag: v1
                """;

        // YAML → TOON → YAML
        String toon = Toon.fromYaml(yaml);
        String backToYaml = Toon.toYaml(toon);

        assertThat(backToYaml).contains("app");
        assertThat(backToYaml).contains("catalog");
        assertThat(backToYaml).contains("replicas: 3");
        assertThat(backToYaml).contains("repository: example/catalog");
    }
}
