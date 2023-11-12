package com.jc.kafkatutorial.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    // Listener that listens on the jc topic
    @KafkaListener(topics = "jc", groupId = "groupId")
    void listener(String data) {
        System.out.println("Listener received: " + data + "🎉");
    }
}
