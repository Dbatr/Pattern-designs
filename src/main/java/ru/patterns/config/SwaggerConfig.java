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
    public GroupedOpenApi canvasFolderApi() {
        return GroupedOpenApi.builder()
                .group("canvas-folder-api")
                .packagesToScan("ru.patterns.controllers")
                .pathsToMatch("/folders/**")
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

    @Bean
    public GroupedOpenApi swaggerTemperatureApi() {
        return GroupedOpenApi.builder()
                .group("temperature-api")
                .packagesToScan("ru.patterns.controllers")
                .pathsToMatch("/temperature/**")
                .build();
    }

    @Bean
    public GroupedOpenApi eventApi() {
        return GroupedOpenApi.builder()
                .group("event-api")
                .packagesToScan("ru.patterns.controllers")
                .pathsToMatch("/events/**")
                .build();
    }

    @Bean
    public GroupedOpenApi contentNoteApi() {
        return GroupedOpenApi.builder()
                .group("contentnote-api")
                .packagesToScan("ru.patterns.controllers")
                .pathsToMatch("/contentnotes/**")
                .build();
    }

    @Bean
    public GroupedOpenApi ThemeApi() {
        return GroupedOpenApi.builder()
                .group("theme-api")
                .packagesToScan("ru.patterns.controllers")
                .pathsToMatch("/themes/**")
                .build();
    }
}
