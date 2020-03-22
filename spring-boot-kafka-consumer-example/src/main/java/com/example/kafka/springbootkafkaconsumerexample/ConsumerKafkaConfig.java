package com.example.kafka.springbootkafkaconsumerexample;

import com.example.kafka.CarDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class ConsumerKafkaConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "library1");
        return props;
    }

    @Bean
    public ConsumerFactory<String, String> consumerUsrFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>(String.class)
        );
    }

    @Bean
    public ConsumerFactory<String, CarDto> consumerCarFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>(CarDto.class)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CarDto> kafkaListenerContainerCarFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CarDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerCarFactory());
        return factory;
    }

    /**
     * ВНИМАНИЕ!!! Очень важно делать имя kafkaListenerContainerFactory как минимум для 1 бина,
     * посколько Spring kafka ищет именно это название для фабрики по усолчанию.
     *
     * Для указачния другой фабрики, необходимо явным образом это сделать в @KafkaListener
     * Пример: @KafkaListener(topics = "cars", groupId = "library1", containerFactory = "kafkaListenerContainerCarFactory")
     * мы указали в параметр containerFactory наименование бина.
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerUsrFactory());
        return factory;
    }

}
