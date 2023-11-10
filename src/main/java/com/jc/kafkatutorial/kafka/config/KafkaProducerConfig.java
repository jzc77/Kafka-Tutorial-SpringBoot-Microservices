package com.jc.kafkatutorial.kafka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfig {

    // variable that will hold the bootstrap server url defined in application.properties
    @Value("{spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
}
