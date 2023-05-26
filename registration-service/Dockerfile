FROM openjdk:11-jre-slim
WORKDIR usr/src
EXPOSE 8086
ADD ./target/registration-service-0.0.1-SNAPSHOT.jar /usr/src/registration-service-0.0.1-SNAPSHOT.jar 
ENTRYPOINT ["java","-jar","registration-service-0.0.1-SNAPSHOT.jar"]