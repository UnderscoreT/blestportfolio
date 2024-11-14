# Use OpenJDK as the base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from your local machine to the container
COPY target/blestcodestudios2-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on (default is 8080)
EXPOSE 8080

# Run the Spring Boot app when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
