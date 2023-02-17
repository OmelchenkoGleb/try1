#
# Build stage
#
FROM maven:3.8.7-eclipse-temurin-19 AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
FROM openjdk:19-jdk-slim
COPY --from=build /target/try1-0.0.1-SNAPSHOT.jar demo.jar
ENV PORT=10000
ENV HOST=0.0.0.0
ENTRYPOINT ["java","-jar","demo.jar"]