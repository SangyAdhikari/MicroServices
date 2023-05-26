FROM openjdk:11-jre-slim
WORKDIR /usr/src
EXPOSE 8888
ADD ./target/config-server-0.0.1-SNAPSHOT.jar  /usr/src/config-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","config-server-0.0.1-SNAPSHOT.jar"]