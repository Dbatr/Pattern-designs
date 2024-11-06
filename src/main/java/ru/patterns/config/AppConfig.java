package ru.patterns.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.patterns.abstractfactory.factory.StationeryFactory;
import ru.patterns.abstractfactory.factory.SchoolStationeryFactory;
import ru.patterns.decorator.LoggingCanvasServiceDecorator;
import ru.patterns.interfaces.CanvasServiceI;
import ru.patterns.interfaces.NoteServiceI;
import ru.patterns.repositories.CanvasRepository;
import ru.patterns.repositories.ShapeRepository;
import ru.patterns.services.CanvasService;
import ru.patterns.services.NoteService;
import ru.patterns.services.NoteServiceCacheProxy;
import ru.patterns.utils.CanvasValidationChainBuilder;


@Configuration
public class AppConfig {

    @Bean(name = "loggingDecoratedCanvasService")
    public CanvasServiceI canvasService(CanvasRepository canvasRepository, ShapeRepository shapeRepository,
                                        CanvasValidationChainBuilder validationChainBuilder) {
        CanvasServiceI originalService = new CanvasService(canvasRepository, shapeRepository, validationChainBuilder);
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

    @Bean
    public NoteServiceI cachedNoteService(NoteService noteService) {
        return new NoteServiceCacheProxy(noteService);
    }
}
