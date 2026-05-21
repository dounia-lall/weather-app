package com.weather.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {

    private String id;
    private String city;
    private double temperature;
    private double humidity;
    private String timestamp;
    private String region;
}