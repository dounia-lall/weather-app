package com.weather.kafka.controller;

import com.weather.kafka.service.WeatherProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherProducer weatherProducer;

    public WeatherController(WeatherProducer weatherProducer) {
        this.weatherProducer = weatherProducer;
    }

    @PostMapping("/{city}")
    public ResponseEntity<String> sendWeather(@PathVariable String city) {

        if (city == null || city.isBlank()) {
            return ResponseEntity.badRequest().body("City invalide");
        }

        weatherProducer.sendWeatherFromApi(city);

        return ResponseEntity.ok("OK Kafka sent");
    }
}