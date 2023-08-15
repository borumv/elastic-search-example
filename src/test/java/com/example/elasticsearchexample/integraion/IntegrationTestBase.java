package com.example.elasticsearchexample.integraion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@Testcontainers
public abstract class IntegrationTestBase {

    @Container
    public static DockerComposeContainer dockerComposeContainer =
            new DockerComposeContainer(new File("src/test/resources/docker-compose-test.yml"))
                    .withExposedService("es01test", 9200, Wait.forListeningPort())
                    .withOptions("--compatibility")
                    .withLocalCompose(true);

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {

        var host = dockerComposeContainer.getServiceHost("es01test", 9200);
        var port = dockerComposeContainer.getServicePort("es01test", 9200);
        registry.add("elasticsearch.host", () -> host);
        registry.add("elasticsearch.port", () -> port);

    }

}
