package com.example.kafka.springbootkafkaproducerexample;

import com.example.kafka.CarDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final Producer producer;

    public KafkaController(Producer producer) {
        this.producer = producer;
    }

    @GetMapping(value = "/publish/user")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message){
        this.producer.sendMessage(message);
    }

    @GetMapping(value = "/publish/car")
    public void sendMessageToKafkaTopic(@RequestParam("name") String name, @RequestParam("number") String number){
        this.producer.sendCar(new CarDto(name, number));
    }

}
