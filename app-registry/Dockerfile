# Use OpenJDK 23 as a base image
FROM openjdk:17-jdk-slim as build

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build context (after it's built in the CI/CD pipeline)
COPY target/app-registry.jar /app/app-registry.jar

# Expose the port that the service will run on
EXPOSE 8761

# Command to run the service
ENTRYPOINT ["java", "-jar", "/app/app-registry.jar"]
