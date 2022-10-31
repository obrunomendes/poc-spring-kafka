package com.example.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener {
    Logger log = LoggerFactory.getLogger(ConsumerListener.class);

    @KafkaListener(topics = "${topic.name}", groupId = "${spring.kafka.group-id}",
            containerFactory = "consumerKafkaListenerContainerFactory")
    public void listenerTopic(ConsumerRecord<String, Object> record) {
        log.info("Receiver Message Partition: {}" + record.partition());
        log.info("Receiver: {}" + record.value());
    }

}
