FROM openjdk:11-jre-slim
WORKDIR /usr/src
EXPOSE 8085
ADD ./target/booking-ticket-service-0.0.1-SNAPSHOT.jar  /usr/src/booking-ticket-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","booking-ticket-service-0.0.1-SNAPSHOT.jar"]