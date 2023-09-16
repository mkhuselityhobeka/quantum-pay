FROM openjdk:11.0.11
EXPOSE 8082
ARG JAR_FILE=target/quantum-pay-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
MAINTAINER Mkhuseli Tyhobeka
ENTRYPOINT ["java","-jar","/app.jar"]