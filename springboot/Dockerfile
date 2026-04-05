FROM maven:3.8-openjdk-17 as builder

WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Second stage - runtime image
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /build/target/zara-interpreter-1.0.0.jar app.jar

# Create a non-root user for security
RUN useradd -m -u 1000 appuser && chown -R appuser:appuser /app
USER appuser

# Expose the application port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
    CMD java -cp app.jar org.springframework.boot.loader.JarLauncher curl -f http://localhost:8080/api/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
CMD ["--server.port=8080"]
