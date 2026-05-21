package com.weather.kafka.service;

import com.weather.kafka.config.CityConfig;
import com.weather.kafka.dto.OpenWeatherResponse;
import com.weather.kafka.model.WeatherData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.UUID;

@Service
public class OpenWeatherService {

    @Value("${openweather.api.key}")
    private String apiKey;

    @Value("${openweather.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherData getWeather(String city) {

        String url = apiUrl
                + "?q=" + city
                + "&appid=" + apiKey
                + "&units=metric";

        OpenWeatherResponse response =
                restTemplate.getForObject(url, OpenWeatherResponse.class);

        if (response == null || response.getMain() == null) {
            throw new RuntimeException("API OpenWeather error for " + city);
        }

        String region = CityConfig.getRegion(city.toLowerCase());

        return new WeatherData(
                UUID.randomUUID().toString(),
                city,
                response.getMain().getTemp(),
                response.getMain().getHumidity(),
                Instant.now().toString(),
                region
        );
    }
}