package com.example.elasticsearchexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
@SpringBootApplication
public class ElasticSearchExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticSearchExampleApplication.class, args);
	}

}
