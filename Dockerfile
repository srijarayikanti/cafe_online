FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /workspace

# Copy wrapper and pom first to leverage Docker layer caching
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make the mvnw executable
RUN chmod +x ./mvnw

# Download dependencies (offline) so subsequent builds are faster
RUN ./mvnw -q dependency:go-offline -B

# Copy source and build the application
COPY src ./src
RUN ./mvnw -q -DskipTests clean package

# Runtime image
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy the packaged jar from the build stage
COPY --from=build /workspace/target/*.jar app.jar

# Expose common ports (dev and qa profiles may use 8082/8083)
EXPOSE 8082 8083

# Use PORT env var if provided by the hosting platform, otherwise default to 8082
ENTRYPOINT ["sh", "-c", "java -jar /app/app.jar --server.port=${PORT:-8082} --spring.profiles.active=${SPRING_PROFILES_ACTIVE:-dev}"]

