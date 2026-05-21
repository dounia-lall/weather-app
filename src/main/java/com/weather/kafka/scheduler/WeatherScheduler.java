package com.weather.kafka.scheduler;

import com.weather.kafka.service.WeatherProducer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.weather.kafka.config.CityConfig;

@Component
public class WeatherScheduler {

    private final WeatherProducer weatherProducer;

    public WeatherScheduler(WeatherProducer weatherProducer) {
        this.weatherProducer = weatherProducer;
    }

    @Scheduled(fixedRate = 5000)
    public void streamWeatherData() {

        try {
            weatherProducer.sendWeatherBatch();

            System.out.println("⚡ Streaming batch météo envoyé");

        } catch (Exception e) {
            System.err.println("❌ Erreur scheduler: " + e.getMessage());
        }
    }
}