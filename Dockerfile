# Step 1: Build the application using Maven
FROM maven:3.8.1-openjdk-11 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and source code into the container
COPY pom.xml .
COPY src ./src

# Run Maven to build the application and create the JAR file
RUN mvn clean package

# Step 2: Create the runtime environment with JRE
FROM openjdk:11-jre-slim

# Copy the JAR file from the build container to the runtime container
COPY --from=build /app/target/rest-api-0.0.1-SNAPSHOT.jar /usr/local/lib/app.jar

# Define the entry point for the container to run the JAR file
ENTRYPOINT ["java", "-jar", "/usr/local/lib/app.jar"]

# Expose port 8080 for the app to listen on
EXPOSE 8080
