FROM openjdk:11-jre-slim
WORKDIR /usr/src
EXPOSE 8099
ADD ./target/email-notification-service-0.0.1-SNAPSHOT.jar  /usr/src/email-notification-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","email-notification-service-0.0.1-SNAPSHOT.jar"]