package com.example.tsunamiservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TsnunamiEventsProducer {


    Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    KafkaTemplate <String, Tsnunami> kafkaTemplate;

    @Scheduled(fixedDelay = 2000)
    public void tsnunamiAlert() {

        Tsnunami tsnunami = new Tsnunami("Greenland",10.9, "2010-10");

        kafkaTemplate.send("tsunamis", tsnunami);
    }
}
