# Use OpenJDK 17 as a base image
FROM openjdk:17-jdk-slim as build

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build context
COPY target/config-server.jar /app/config-server.jar

# Expose the port that the service will run on
EXPOSE 8888

# Set environment variables for Consul
ENV CONSUL_HOST=consul
ENV CONSUL_PORT=8500

# Command to run the service
ENTRYPOINT ["java", "-jar", "/app/config-server.jar"]
