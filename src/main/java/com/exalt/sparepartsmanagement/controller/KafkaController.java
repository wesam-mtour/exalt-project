package com.exalt.sparepartsmanagement.controller;

import com.exalt.sparepartsmanagement.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    private  KafkaProducer kafkaProducer;

    public KafkaController(KafkaProducer producer) {
        this.kafkaProducer = producer;
    }

    @PostMapping("/publish")
    public void writeMessageToTopic(@RequestParam("message") String message){
        this.kafkaProducer.writeMessage(message);
    }

}


