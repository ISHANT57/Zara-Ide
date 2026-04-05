#!/bin/bash

# ZARA Interpreter - Run Script
# Runs the ZARA Interpreter Spring Boot application

echo "╔═══════════════════════════════════════════════════╗"
echo "║  ZARA Interpreter - Spring Boot                  ║"
echo "║  Starting application...                          ║"
echo "╚═══════════════════════════════════════════════════╝"
echo ""

# Check if JAR exists
JAR_FILE="target/zara-interpreter-1.0.0.jar"

if [ ! -f "$JAR_FILE" ]; then
    echo "❌ JAR file not found. Running build first..."
    bash build.sh
    if [ $? -ne 0 ]; then
        echo "Build failed"
        exit 1
    fi
fi

echo "Starting ZARA Interpreter..."
echo "Configuration: src/main/resources/application.properties"
echo ""
echo "📍 Application URL: http://localhost:8080"
echo "📍 Documentation: http://localhost:8080/docs"
echo "📍 API Health: http://localhost:8080/api/health"
echo ""
echo "Press Ctrl+C to stop the application"
echo ""

# Run with optional custom port
PORT=${1:-8080}

java -jar "$JAR_FILE" --server.port=$PORT
