package com.example.tsunamiui;


import com.example.Tsunami;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TsunamiService {

    private static final String TSUNAMI_TOPIC = "tsunamis";
    private static final String LARGE_WAVES_TOPIC = "largewaves";
    public static final int MAX_NUMBER_OF_ALERTS_TO_DISPLAY = 20;

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    private List<Wave> tsunamiAlerts = new ArrayList<>();
    private List<Wave> largewaves = new ArrayList<>();

    @KafkaListener(topics = TSUNAMI_TOPIC)
    public void listen(ConsumerRecord<String, Tsunami> record) {
        Tsunami tsunami = record.value();
        tsunamiAlerts.add(new Wave (String.valueOf(tsunami.getCountry()),String.valueOf(tsunami.getLocalDate()), Double.valueOf(String.valueOf(tsunami.getHeight()))));
        LOGGER.info("received tsunami alert='{}'", tsunami);
    }

    @KafkaListener(topics = LARGE_WAVES_TOPIC)
    public void listenWaves(ConsumerRecord<String, Tsunami> record) {
        Tsunami tsunami = record.value();
        largewaves.add(new Wave (String.valueOf(tsunami.getCountry()),String.valueOf(tsunami.getLocalDate()), Double.valueOf(String.valueOf(tsunami.getHeight()))));
        LOGGER.info("Large tsunamiAlerts !!  tsunami alert='{}'", tsunami.getHeight());
    }

    @RequestMapping("/alerts")
    public String data() {

        int totalNumberofTsunamiAlerts = tsunamiAlerts.size();
        List<Wave> alertsToDisplay = tsunamiAlerts;

        if(totalNumberofTsunamiAlerts > MAX_NUMBER_OF_ALERTS_TO_DISPLAY) {
            alertsToDisplay = this.tsunamiAlerts.subList(totalNumberofTsunamiAlerts - 20, totalNumberofTsunamiAlerts);
        }

        return alertsToDisplay.stream().map( w -> " [\""+w.year+"\","+w.height+"]").collect(Collectors.toList()).toString();
    }

    @RequestMapping("/largewaves")
    public List<Wave> large() {
        return largewaves;
    }

}


