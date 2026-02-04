# Stage 1: Build the JAR using Maven
FROM maven:3.9.0-eclipse-temurin-17 AS builder

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first for caching dependencies
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the Spring Boot application
RUN ./mvnw clean package -DskipTests

# Stage 2: Create minimal runtime image
FROM eclipse-temurin:17-jdk-jammy

# Set working directory
WORKDIR /app

# Copy the JAR from the builder stage
COPY --from=builder /app/target/expense-tracker-0.0.1-SNAPSHOT.jar app.jar

# Expose port (Render sets PORT dynamically)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java","-jar","app.jar"]
