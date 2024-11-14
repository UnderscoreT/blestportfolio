# Step 1: Build the application JAR using Maven
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app

# Copy the Maven configuration file and source code into the container
COPY pom.xml .
COPY src ./src

# Build the application, skipping tests if you want a faster build
RUN mvn clean package -DskipTests

# Step 2: Use a lightweight OpenJDK image to run the application
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the JAR file from the build stage to the final image
COPY --from=build /app/target/*.jar app.jar

# Expose the port the app runs on (adjust if necessary)
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
