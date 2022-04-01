package com.kosta.kostapay;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class KafkaConsumer {

    final PayRepository payRepository;

    public KafkaConsumer(PayRepository payRepository) {
        this.payRepository = payRepository;
    }

    @KafkaListener(topics = "order-topic")
    public void updateBalance(String kafkaMessage) {

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }

        Optional<Pay> pay = payRepository.findById((String)map.get("userId"));
        if(pay.isPresent()){
            pay.get().withdrawal((int) map.get("totalPrice"));
            payRepository.save(pay.get());
        }
    }
}
