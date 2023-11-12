package com.jc.kafkatutorial.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    // Configuring port to use for establishing the initial connection to the Kafka cluster
    // Configuring Kafka so that keys and values will be deserialized into the StringSerializer.class
    public Map<String, Object> consumerConfig() {  // For consuming Kafka topics, all consumers will consume keys and values that are deserialized into strings.
        Map<String, Object> property = new HashMap<>();
        property.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        property.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        property.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return property;
    }

    // Defining the consumer factory, which is responsible for consuming instances
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    // This listener receives all messages from all topics, all partitions, on a single thread
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> factory(ConsumerFactory<String, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
