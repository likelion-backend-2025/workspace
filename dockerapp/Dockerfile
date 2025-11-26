FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY build/libs/*-SNAPSHOT.jar dockerapp.jar

ENTRYPOINT ["java", "-jar", "dockerapp.jar"]