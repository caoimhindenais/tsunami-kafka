package com.example.tsunamiui;


import com.example.Tsunami;
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

    List<Wave> waves = new ArrayList<>();

    @PostConstruct
    public void setup() {

    }

    @KafkaListener(topics = "tsunamis")
    public void listen(ConsumerRecord<String, Tsunami> record) {
        waves.add(new Wave (String.valueOf(record.value().getLocalDate()), Double.valueOf(String.valueOf(record.value().getHeight()))));
        LOGGER.info("received tsunami alert='{}'", record.value());
    }

    //@StreamListener


    @RequestMapping("/data")
    public String data() {
        //Highcharts format
        return waves.stream().map( w -> " [\""+w.year+"\","+w.height+"]").collect(Collectors.toList()).toString();
    }

}


