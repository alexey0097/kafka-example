FROM openjdk:8

EXPOSE 8001

MAINTAINER Zemtsov Alexey Vladimirovich <zemtsov1997@yandex.ru>

ENV DEBUG_LEVEL INFO

WORKDIR /opt/consumer

ADD target/spring-boot-kafka-consumer-example-*.jar consumer.jar

ENTRYPOINT java -jar -DDEBUG_LEVEL=$DEBUG_LEVEL consumer.jar