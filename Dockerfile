#
# Build stage
#
FROM maven:3.8.7-eclipse-temurin-19-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:19-jdk-alpine
COPY --from=build /target/try1.jar demo.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]