package com.example.kafka.springbootkafkaconsumerexample;

import com.example.kafka.CarDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "users", groupId = "library1")
    public void consume(String message){
        logger.info(String.format("$$ -> Consumed Message -> %s", message));
    }

    @KafkaListener(topics = "cars", groupId = "library1", containerFactory = "kafkaListenerContainerCarFactory")
    public void consume2(CarDto car){
        logger.info(String.format("$$ -> Consumed Car -> %s", car.toString()));
    }

}
