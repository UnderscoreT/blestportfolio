# Step 1: Build the application JAR using Maven
FROM maven:3.8-openjdk-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
#RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Step 2: Build the runtime image
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built JAR from the build image
COPY --from=build /app/target/blestcodestudios2-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on (default is 8080)
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
