package com.example.tsunamiservice;

import com.example.Tsunami;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TsunamiEventsProducer {

    private static final int COUNTRY_INDEX = 1;
    private static final int WAVE_HEIGHT_INDEX = 2;
    private static final int DATE_INDEX = 0;

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    private List<Tsunami> tsunamis = new ArrayList<>();
    private Integer alert = 0;

    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
    //YearMonth.parse(l.get(DATE_INDEX),formatter).toString()

    @Autowired
    KafkaTemplate<String, Tsunami> kafkaTemplate;

    @PostConstruct
    public void load() throws IOException {
        File file = ResourceUtils.getFile("classpath:alerts/tsunami.txt");
        try (Stream<String> lines = Files.lines(file.toPath())) {
            tsunamis = lines.map(line -> Arrays.asList(line.split(",")))
                    .map(l -> new Tsunami(l.get(COUNTRY_INDEX), Double.valueOf(l.get(WAVE_HEIGHT_INDEX)), l.get(DATE_INDEX))).collect(Collectors.toList());
        }

    }


    @Scheduled(fixedDelay = 2000)
    public void TsunamiAlert() {
        if (alert < tsunamis.size()) {
            Tsunami tsunami = tsunamis.get(alert++);
            LOGGER.info("New Tsunami Alert !  in " + tsunami.getCountry());
            kafkaTemplate.send("tsunamis", tsunami);
        }

    }
}
