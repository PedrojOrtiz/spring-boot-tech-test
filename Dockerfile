FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/spring-boot-tech-test-1.0.jar /app/spring-boot-tech-test-1.0.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "spring-boot-tech-test-1.0.jar"]
