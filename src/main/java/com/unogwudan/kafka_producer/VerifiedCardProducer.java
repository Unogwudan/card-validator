package com.unogwudan.kafka_producer;

import com.unogwudan.model.VerifiedCardDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
@Component
public class VerifiedCardProducer {

    @Value("${kafka.verified.card.topic}")
    private String verifiedCardTopic;
    @Autowired
    private KafkaTemplate<String, VerifiedCardDetails> producerTemplate;

    public void publishVerifiedCardMessage(VerifiedCardDetails request) {
        producerTemplate.send(verifiedCardTopic, request);
    }
}
