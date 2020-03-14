FROM openjdk:8

EXPOSE 8002

MAINTAINER Zemtsov Alexey Vladimirovich <zemtsov1997@yandex.ru>

ENV DEBUG_LEVEL INFO

WORKDIR /opt/producer

ADD target/spring-boot-kafka-producer-example-*.jar producer.jar

ENTRYPOINT java -jar -DDEBUG_LEVEL=$DEBUG_LEVEL producer.jar