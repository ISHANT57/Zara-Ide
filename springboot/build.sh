#!/bin/bash

# ZARA Interpreter - Spring Boot Setup & Build Script
# This script sets up and builds the ZARA Interpreter Spring Boot application

echo "╔═══════════════════════════════════════════════════╗"
echo "║  ZARA Interpreter - Spring Boot Setup             ║"
echo "║  Version 1.0.0                                    ║"
echo "╚═══════════════════════════════════════════════════╝"
echo ""

# Check if Java is installed
echo "Checking Java installation..."
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed."
    echo "Please install Java 17 or higher:"
    echo "  Ubuntu/Debian: sudo apt install openjdk-17-jdk"
    echo "  macOS: brew install openjdk@17"
    echo "  Windows: Download from https://www.oracle.com/java/technologies/downloads/"
    exit 1
else
    java_version=$(java -version 2>&1 | head -1)
    echo "✓ Java found: $java_version"
fi

# Check if Maven is installed
echo ""
echo "Checking Maven installation..."
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven is not installed."
    echo "Please install Maven 3.8 or higher:"
    echo "  Ubuntu/Debian: sudo apt install maven"
    echo "  macOS: brew install maven"
    echo "  Windows: Download from https://maven.apache.org/download.cgi"
    echo ""
    echo "Or add Maven to your PATH if already installed."
    exit 1
else
    mvn_version=$(mvn -version 2>&1 | head -2)
    echo "✓ Maven found"
    echo "$mvn_version"
fi

# Move to the springboot directory
cd "$(dirname "$0")" || exit 1

echo ""
echo "Building ZARA Interpreter Spring Boot Application..."
echo ""

# Clean previous builds
echo "Step 1: Cleaning previous builds..."
mvn clean

if [ $? -ne 0 ]; then
    echo "❌ Clean failed"
    exit 1
fi

# Compile and package
echo ""
echo "Step 2: Compiling and packaging..."
mvn package -DskipTests

if [ $? -ne 0 ]; then
    echo "❌ Build failed"
    exit 1
fi

echo ""
echo "╔═══════════════════════════════════════════════════╗"
echo "║  ✓ Build Successful!                             ║"
echo "╚═══════════════════════════════════════════════════╝"
echo ""

JAR_FILE="target/zara-interpreter-1.0.0.jar"

if [ -f "$JAR_FILE" ]; then
    echo "JAR file created: $JAR_FILE"
    echo "Size: $(ls -lh $JAR_FILE | awk '{print $5}')"
    echo ""
    echo "To run the application:"
    echo "  java -jar $JAR_FILE"
    echo ""
    echo "Or use Maven:"
    echo "  mvn spring-boot:run"
    echo ""
    echo "Then open your browser to:"
    echo "  http://localhost:8080"
else
    echo "❌ JAR file not found at $JAR_FILE"
    exit 1
fi
