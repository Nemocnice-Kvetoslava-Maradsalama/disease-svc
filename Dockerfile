#FROM maven:3.6.0-jdk-11-slim AS build
#COPY src /home/app/src
#COPY pom.xml /home/app
#RUN mvn -f /home/app/pom.xml install

FROM openjdk:8-jdk-alpine

COPY ./target/disease*.jar ./disease-svc.jar

ENTRYPOINT ["java", "-jar", "./disease-svc.jar"]