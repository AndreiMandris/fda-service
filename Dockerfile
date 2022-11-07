FROM openjdk:17-jdk-slim
ADD build/libs/fda-service.jar fda-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=docker", "fda-service.jar"]
