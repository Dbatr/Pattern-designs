package ru.patterns.interfaces;

import java.util.Map;

// Интерфейс адаптируемого объекта
public interface WeatherApi {
    Map<String, Object> getCurrentWeatherData(String city);
}