# Use an official OpenJDK 8 runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot jar into the container
COPY target/employees-api-0.0.1.jar app.jar

# Expose the port on which the application is running (change it if your application uses a different port)
EXPOSE 8080

# Run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]