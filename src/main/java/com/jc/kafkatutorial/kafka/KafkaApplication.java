package com.jc.kafkatutorial.kafka;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

	// This method will run as soon as application starts
	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {  // Dependency injection, where KafkaTemplate is from KafkaProducerConfig.java
		return args -> {
			//kafkaTemplate.send("jc", "hello kafka 2");  // Recall that topic "jc" is defined in KafkaTopicConfig.java

			for (int i = 0; i < 100; i++) {   // Pushing 100 events into the "jc" topic
				kafkaTemplate.send("jc", "hello kafka! " + i);
			}

		};
	}

}
