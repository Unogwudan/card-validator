package com.unogwudan.config;

import com.unogwudan.model.VerifiedCardDetails;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
@Configuration
@EnableKafka
public class KafkaProducerConfig {

    @Value("${kafka.instances}")
    private String kafkaUrl;

    @Bean
    public ProducerFactory<String, VerifiedCardDetails> producerFactory() {

        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaUrl);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, VerifiedCardDetails> producerTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

}
