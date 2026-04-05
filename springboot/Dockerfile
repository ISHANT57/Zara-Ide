# Stage 1 - Build
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Stage 2 - Runtime
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=builder /build/target/zara-interpreter-1.0.0.jar app.jar

RUN useradd -m -u 1000 appuser && chown -R appuser:appuser /app
USER appuser

ENV PORT=10000
EXPOSE 10000

USER root
RUN apt-get update && apt-get install -y curl
USER appuser

HEALTHCHECK CMD curl -f http://localhost:10000/api/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
CMD ["--server.port=10000"]