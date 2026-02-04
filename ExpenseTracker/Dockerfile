# Stage 1: Build the JAR using Java 21 and Maven Wrapper
FROM eclipse-temurin:21-jdk-jammy AS builder

WORKDIR /app

# Copy Maven wrapper and pom.xml first for dependency caching
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make mvnw executable
RUN chmod +x mvnw

# Download dependencies only (cache them)
RUN ./mvnw dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the Spring Boot JAR without running tests
RUN ./mvnw clean package -DskipTests

# Stage 2: Minimal runtime image
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copy the JAR from builder stage
COPY --from=builder /app/target/expense-tracker-0.0.1-SNAPSHOT.jar app.jar

# Expose port dynamically (Render will set PORT env)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java","-jar","app.jar"]
