## Build stage
#FROM maven:3.8.5-openjdk-17 AS build
#WORKDIR /home/app
#COPY pom.xml .
#COPY src ./src
#RUN mvn clean package
#
## Package stage
#FROM openjdk:17-jdk-slim
#COPY --from=build /home/app/target/Medicory-0.0.1-SNAPSHOT.jar  /usr/local/lib/Medicory.jar
#EXPOSE 8081
#ENTRYPOINT ["java","-jar","/usr/local/lib/Medicory.jar"]
FROM openjdk:17-jdk-alpine
COPY target/Medicory-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
