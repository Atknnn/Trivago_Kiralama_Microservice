package com.atakan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ElacticServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElacticServiceApplication.class);
    }
}
