package ru.patterns.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.patterns.services.TemperatureServiceAdapter;

@RestController
@RequestMapping("/temperature")
public class TemperatureController {

    private final TemperatureServiceAdapter temperatureService;

    public TemperatureController(TemperatureServiceAdapter temperatureService) {
        this.temperatureService = temperatureService;
    }

    @GetMapping("/{city}")
    public ResponseEntity<Double> getTemperature(@PathVariable String city) {
        var temperature = temperatureService.getTemperature(city);
        return ResponseEntity.ok(temperature);
    }
}
