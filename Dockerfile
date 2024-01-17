
# Use the OpenJDK base image suitable for running Java applications
FROM openjdk:17-jdk-alpine as builder

# Working directory inside the container
WORKDIR /app

# Copy the compiled JAR file into the container
COPY build/libs/Individual_Assignment_Hristo_Ganchev-0.0.1-SNAPSHOT.jar /app/app.jar

# Copy the .env file where the jwt secret key is stored
COPY jwt-secret-key.env /app/

# Install bash
RUN apk --no-cache add bash

# Expose the port that the application runs on
EXPOSE 8080

# Command to run your application
CMD ["bash", "-c", "java -jar /app/app.jar"]