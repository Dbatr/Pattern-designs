package ru.patterns.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Паттерн Builder.
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi notesApi() {
        return GroupedOpenApi.builder()
                .group("notes-api")
                .packagesToScan("ru.patterns.controllers")
                .pathsToMatch("/notes/**")
                .build();
    }

    @Bean
    public GroupedOpenApi homeApi() {
        return GroupedOpenApi.builder()
                .group("home-api")
                .packagesToScan("ru.patterns.controllers")
                .pathsToMatch("/")
                .build();
    }
}
