# Use Eclipse Temurin JRE base image (Java 8 to match project compilation)
FROM eclipse-temurin:8-jre-alpine

# Set working directory
WORKDIR /app

# Copy the pre-built JAR file from Jenkins workspace
COPY target/main-1.0-SNAPSHOT.jar .

# Run the application
CMD ["java", "-jar", "main-1.0-SNAPSHOT.jar"]