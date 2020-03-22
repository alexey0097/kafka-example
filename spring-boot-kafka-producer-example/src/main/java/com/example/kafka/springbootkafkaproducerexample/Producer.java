package com.example.kafka.springbootkafkaproducerexample;


import com.example.kafka.CarDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC_1 = "users";
    private static final String TOPIC_2 = "cars";

    private KafkaTemplate <String, String> kafkaTemplate1;
    private KafkaTemplate <String, CarDto> kafkaTemplate2;

    public Producer(KafkaTemplate<String, String> kafkaTemplate1, KafkaTemplate<String, CarDto> kafkaTemplate2) {
        this.kafkaTemplate1 = kafkaTemplate1;
        this.kafkaTemplate2 = kafkaTemplate2;
    }

    public void sendMessage(String message){
        logger.info(String.format("$$ -> Producing message --> %s",message));
        this.kafkaTemplate1.send(TOPIC_1, message);
    }

    public void sendCar(CarDto car) {
        logger.info(String.format("$$ -> Producing Car --> %s", car.toString()));
        this.kafkaTemplate2.send(TOPIC_2, car);
    }


}
