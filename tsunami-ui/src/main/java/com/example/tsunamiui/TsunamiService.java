package com.example.tsunamiui;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TsunamiService {

    Logger LOGGER = LoggerFactory.getLogger(getClass());

    //Integer year = 1900;
    List<Wave> waves = new ArrayList<>();

    @PostConstruct
    public void setup() {
        //waves.add(new Wave(String.valueOf(year++)+"-08",Math.random()*100));
        //waves.add(new Wave(String.valueOf(year++)+"-09",Math.random()*100));
        //waves.add(new Wave(String.valueOf(year++)+"-11",Math.random()*100));
        //waves.add(new Wave(String.valueOf(year++)+"-12",Math.random()*100));
    }

    @KafkaListener(topics = "tsunamis")
    public void listen(ConsumerRecord<String, Tsnunami> kafkaMessage) {
        //waves.add(new Wave (String.valueOf(kafkaMessage.value().get(2)), Double.valueOf(String.valueOf(kafkaMessage.value().get(1)))));
        LOGGER.info("received message='{}'", kafkaMessage.value());
    }

    //@StreamListener


    @RequestMapping("/data")
    public String data() {
        //waves.add(new Wave(String.valueOf(year++)+"-01",Math.random()*100));
        return waves.stream().map( w -> " [\""+w.year+"\","+w.height+"]").collect(Collectors.toList()).toString();
    }

}


