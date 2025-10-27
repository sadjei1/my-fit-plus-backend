# syntax=docker/dockerfile:1

### --- Build Stage --- ###
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies first (for build cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source and build the application (skip tests for speed)
COPY src ./src
RUN mvn clean package -DskipTests

### --- Run Stage --- ###
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Create a non-root user for security
RUN useradd --system --uid 1000 appuser
USER appuser

# Copy the built jar from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose Spring Boot default port
EXPOSE 8080

# Use JAVA_OPTS env var for runtime configuration
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS:-} -jar /app/app.jar"]
