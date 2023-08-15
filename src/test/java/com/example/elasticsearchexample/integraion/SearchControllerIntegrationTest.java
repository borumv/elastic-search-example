package com.example.elasticsearchexample.integraion;
import com.example.elasticsearchexample.entity.Indeces;
import com.example.elasticsearchexample.entity.dto.SearchResponseDTO;
import com.example.elasticsearchexample.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Slf4j
class SearchControllerIntegrationTest extends IntegrationTestBase {



    @Autowired
    private SearchService searchService;

    @Test
    @DisplayName("Should find Book by id")
    public void shouldFindBookByIdTest() throws IOException {

        log.info("Docker compose containter getServiceHost - {}", dockerComposeContainer.getServiceHost("es01test", 9200));
        log.info("Docker compose containter getServicePort - {}", dockerComposeContainer.getServicePort("es01test", 9200));
        log.info("Docker compose containter getDependencies - {}", dockerComposeContainer.getDependencies());
        List<SearchResponseDTO> result = searchService.search(new Indeces[]{}, "JaneSmith");
        List<SearchResponseDTO> expected = new ArrayList<>(List.of(new SearchResponseDTO()));
        assertEquals(expected, result);
    }

    @Test
    void name() {

    }
}