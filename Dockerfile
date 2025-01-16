# Step 1: Use a base image with OpenJDK 17 and Maven
FROM maven:3.8.6-jdk-17-slim AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and the src directory into the container
COPY pom.xml .
COPY src ./src

# Run Maven to build the application and create the JAR file
RUN mvn clean package

# Step 2: Use OpenJDK JRE 17 for running the application
FROM openjdk:17-jre-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build container to the runtime container
COPY --from=build /app/target/rest-api-0.0.1-SNAPSHOT.jar /app/rest-api.jar

# Expose the port for the app to run (default Spring Boot port is 8080)
EXPOSE 8080

# Run the JAR file as the entrypoint of the container
ENTRYPOINT ["java", "-jar", "rest-api.jar"]
