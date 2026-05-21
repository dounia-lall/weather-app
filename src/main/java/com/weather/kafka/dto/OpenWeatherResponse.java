package com.weather.kafka.dto;

import lombok.Data;

@Data
public class OpenWeatherResponse {

    private Main main;

    @Data
    public static class Main {
        private double temp;
        private double humidity;
    }
}