package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.service.MailService;

@Component
public class MailController {

    @Autowired
    MailService service;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping
    public void getMessage() {
        kafkaTemplate.send("mail", service.getMessage());
    }
}
