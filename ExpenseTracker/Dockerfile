# Stage 1: Build the JAR using Maven with Java 21
FROM maven:3.9.0-eclipse-temurin-21 AS builder

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make mvnw executable
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline -B

COPY src ./src

# Build the Spring Boot application
RUN ./mvnw clean package -DskipTests

# Stage 2: Minimal runtime with Java 21
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY --from=builder /app/target/expense-tracker-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
