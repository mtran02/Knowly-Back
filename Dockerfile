# Step 1: Use Render's official Maven image or Java + Maven image (if Render supports it)
FROM maven:3.8.6-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and src directory
COPY pom.xml .
COPY src ./src

# Run Maven to build the application and create the JAR file
RUN mvn clean package

# Step 2: Use OpenJDK 17 JRE image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build container
COPY --from=build /app/target/rest-api-0.0.1-SNAPSHOT.jar /app/rest-api.jar

# Expose the application port
EXPOSE 8080

# Define the entry point to run the application
ENTRYPOINT ["java", "-jar", "rest-api.jar"]
