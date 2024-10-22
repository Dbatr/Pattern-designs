package ru.patterns.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.patterns.abstractfactory.factory.StationeryFactory;
import ru.patterns.abstractfactory.factory.SchoolStationeryFactory;
import ru.patterns.decorator.LoggingCanvasServiceDecorator;
import ru.patterns.interfaces.CanvasServiceI;
import ru.patterns.repositories.CanvasRepository;
import ru.patterns.repositories.ShapeRepository;
import ru.patterns.services.CanvasService;


@Configuration
public class AppConfig {

    @Bean(name = "loggingDecoratedCanvasService")
    public CanvasServiceI canvasService(CanvasRepository canvasRepository, ShapeRepository shapeRepository) {
        CanvasServiceI originalService = new CanvasService(canvasRepository, shapeRepository);
        return new LoggingCanvasServiceDecorator(originalService);
    }

    @Bean
    public StationeryFactory stationeryFactory() {
        return new SchoolStationeryFactory();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
