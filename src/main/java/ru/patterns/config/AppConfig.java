package ru.patterns.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.patterns.abstractfactory.factory.StationeryFactory;
import ru.patterns.abstractfactory.factory.SchoolStationeryFactory;


@Configuration
public class AppConfig {

    @Bean
    public StationeryFactory stationeryFactory() {
        return new SchoolStationeryFactory();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
