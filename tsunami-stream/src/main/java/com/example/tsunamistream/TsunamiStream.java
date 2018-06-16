package com.example.tsunamistream;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.example.Tsunami;


@Configuration
@EnableKafka
@EnableKafkaStreams
public class TsunamiStream {

    private final KafkaProperties kafkaProperties;

    @Value("${schema.registry.url}")
    private String schemaRegistryUrl;

    @Value("${application.id}")
    private String applicationId;

    @Value("${in.topic}")
    private String inTopic;

    @Value("${out.topic}")
    private String outTopic;

    @Value("${min.wave.height}")
    private Integer minWaveHeight;

    @Autowired
    public TsunamiStream(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }


    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public StreamsConfig kStreamsConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationId);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);
        props.put("schema.registry.url", schemaRegistryUrl);
        return new StreamsConfig(props);
    }

    @Bean
    public KStream<String, Tsunami> kStreamTsunami(StreamsBuilder builder) {
        final SpecificAvroSerde<Tsunami> tsunamiSerde = new SpecificAvroSerde<>();
        tsunamiSerde.configure(
                Collections.singletonMap(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl),
                false);

        KStream<String, Tsunami> stream = builder.stream(inTopic, Consumed.with(Serdes.String(), tsunamiSerde));
        stream.filter((k, v) -> v.getHeight() > minWaveHeight).to(outTopic);

        return stream;
    }
}