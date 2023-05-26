FROM openjdk:11-jre-slim
WORKDIR /usr/src
EXPOSE 8090
ADD ./target/event-details-service-0.0.1-SNAPSHOT.jar  /usr/src/event-details-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","event-details-service-0.0.1-SNAPSHOT.jar"]