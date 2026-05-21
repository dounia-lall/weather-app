package com.weather.kafka.service;

import com.weather.kafka.config.CityConfig;
import com.weather.kafka.model.WeatherData;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WeatherProducer {

    private final KafkaTemplate<String, WeatherData> kafkaTemplate;
    private final OpenWeatherService openWeatherService;

    public WeatherProducer(
            KafkaTemplate<String, WeatherData> kafkaTemplate,
            OpenWeatherService openWeatherService
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.openWeatherService = openWeatherService;
    }

    public void sendWeatherBatch() {

        for (String city : CityConfig.CITIES) {

            try {
                WeatherData data = openWeatherService.getWeather(city);

                kafkaTemplate.send("weather-events", data.getId(), data);

                System.out.println("📡 Sent: " + city);

            } catch (Exception e) {
                System.out.println("❌ Error city " + city + " : " + e.getMessage());
            }
        }

        System.out.println("⚡ Streaming batch météo envoyé");
    }

    public void sendWeatherFromApi(String city) {

        try {
            WeatherData data = openWeatherService.getWeather(city);

            kafkaTemplate.send("weather-events", data.getId(), data);

            System.out.println("📡 Sent API: " + city);

        } catch (Exception e) {
            System.out.println("❌ Error API city " + city + " : " + e.getMessage());
        }
    }
}