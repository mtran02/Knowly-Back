# Step 1: Use an official Maven image with JDK 17
FROM maven:17-openjdk-slim AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and src directory
COPY pom.xml .
COPY src ./src

# Run Maven to build the application and create the JAR file
RUN mvn clean package

# Step 2: Use a JRE image to run the application
FROM openjdk:17-jre-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/rest-api-0.0.1-SNAPSHOT.jar /app/rest-api.jar

# Run the application
ENTRYPOINT ["java", "-jar", "rest-api.jar"]
