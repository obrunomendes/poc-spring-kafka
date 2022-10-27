package com.example.demo.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping
public class MessageApi {
    private MessageProducer messageProducer;

    MessageApi(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping("/message/producer")
    public Map<String, Object> createMessage(@RequestBody final Map<String, Object> request) {
        String uuid = UUID.randomUUID().toString();

        Map<String, Object> message = new HashMap<>();
        message.putAll(request);

        message.put("trace_id", uuid);
        message.put("date", LocalDateTime.now());

        messageProducer.send(message);
        return message;
    }
}
