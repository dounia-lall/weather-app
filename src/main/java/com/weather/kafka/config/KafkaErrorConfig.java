package com.weather.kafka.config;

import com.weather.kafka.model.WeatherData;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaErrorConfig{

    @Bean
    public DefaultErrorHandler errorHandler(KafkaTemplate<String, WeatherData> template) {

        // 🔥 destination DLQ
        DeadLetterPublishingRecoverer recoverer =
                new DeadLetterPublishingRecoverer(template,
                        (record, ex) -> new TopicPartition("weather-dlq", record.partition()));

        // 🔥 retry policy
        FixedBackOff backOff = new FixedBackOff(2000L, 3); // 3 retries / 2 sec

        return new DefaultErrorHandler(recoverer, backOff);
    }
}