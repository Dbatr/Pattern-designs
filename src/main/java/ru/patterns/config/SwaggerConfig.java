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

    @Bean
    public GroupedOpenApi canvasApi() {
        return GroupedOpenApi.builder()
                .group("canvas-api")
                .packagesToScan("ru.patterns.controllers")
                .pathsToMatch("/canvas/**")
                .build();
    }

    @Bean
    public GroupedOpenApi shopApi() {
        return GroupedOpenApi.builder()
                .group("shop-api")
                .packagesToScan("ru.patterns.abstractfactory.controllers")
                .pathsToMatch("/shop/**")
                .build();
    }
}
