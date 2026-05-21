package com.weather.kafka.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import com.weather.kafka.config.CityConfig;
import com.weather.kafka.model.WeatherData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WeatherConsumer {

    private final ElasticsearchClient elasticsearchClient;
    private static final Logger log = LoggerFactory.getLogger(WeatherConsumer.class);

    public WeatherConsumer(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    @KafkaListener(
            topics = "weather-events",
            groupId = "weather-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(WeatherData data) {

        try {
            // validation
            if (data.getTemperature() < -50 || data.getTemperature() > 60) return;
            if (data.getHumidity() < 0 || data.getHumidity() > 100) return;

            // normalisation unique et propre
            String city = CityConfig.normalize(data.getCity());
            data.setCity(city);

            // region fiable
            String region = CityConfig.getRegion(city);

            if (region == null) {
                region = "Unknown";
            }

            data.setRegion(region);

            // index elastic
            IndexRequest<WeatherData> request = IndexRequest.of(i -> i
                    .index("weather-events")
                    .id(data.getId())
                    .document(data)
            );

            elasticsearchClient.index(request);

            log.info("Indexé city={} region={}", city, region);

        } catch (Exception e) {
            log.error("Erreur traitement {} : {}", data.getCity(), e.getMessage(), e);
        }
    }
}