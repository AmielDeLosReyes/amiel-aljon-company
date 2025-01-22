# Use the official OpenJDK 17 image (non-Alpine)
FROM openjdk:17-jdk

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/service-registry.jar service-registry.jar

# Expose the port for the registry
EXPOSE 8761

# Set environment variable for Consul agent's host to be consul-server-client (the container name)
ENV CONSUL_HOST=consul-server-client
ENV CONSUL_PORT=8500

# Run the application
ENTRYPOINT ["java", "-jar", "service-registry.jar"]
