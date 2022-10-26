package com.example.demo.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MessageProducer {
    Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    @Value("${topic.name}")
    private String topic;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    MessageProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Map<String, Object> message) {
        kafkaTemplate.send(topic, message)
                .addCallback(
                        success -> logger.info("Message send" + success.getProducerRecord().value()),
                        fail -> logger.info("Message failure" + fail.getMessage())
                );
    }

}
