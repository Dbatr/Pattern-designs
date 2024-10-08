package ru.patterns.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.patterns.abstractfactory.factory.StationeryFactory;
import ru.patterns.abstractfactory.factory.SchoolStationeryFactory;


@Configuration
public class Config {

    @Bean
    public StationeryFactory stationeryFactory() {
        return new SchoolStationeryFactory();
    }
}
