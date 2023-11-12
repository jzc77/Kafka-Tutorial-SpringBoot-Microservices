package com.jc.kafkatutorial.kafka.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    // variable that will hold the bootstrap server url defined in application.properties
    @Value("{spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    // Configuration to pass to a producer factory
    // Configuring port to use for establishing the initial connection to the Kafka cluster
    // Configuring Kafka so that keys and values will be serialized into the StringSerializer.class
    public Map<String, Object> producerConfig() {  // For producing Kafka producers. All producers will produce keys and values that are serialized into strings.
        Map<String, Object> property = new HashMap<>();
        property.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        property.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        property.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return property;
    }

    // Defining the producer factory, which is responsible for creating producer instances
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    // A way to send messages
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory());  // Dependency injection
    }
}
