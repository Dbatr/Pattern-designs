package ru.patterns.services;

import org.springframework.stereotype.Service;
import ru.patterns.interfaces.TemperatureService;
import ru.patterns.utils.WeatherApiClient;

import java.util.Map;


/**
 * Паттерн Adapter.
 */
@Service
public class TemperatureServiceAdapter implements TemperatureService {

    private final WeatherApiClient weatherApiClient;

    public TemperatureServiceAdapter(WeatherApiClient weatherApiClient) {
        this.weatherApiClient = weatherApiClient;
    }

    @Override
    public Double getTemperature(String city) {
        var current = (Map) weatherApiClient.getCurrentWeatherData(city).get("current");
        return (Double) current.get("temp_c");
    }
}
