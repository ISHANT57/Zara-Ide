#!/bin/bash

# ZARA Interpreter - Alternative Build Script (Without Maven)
# Downloads pre-built dependencies and builds using Java compiler directly
# This is a workaround for network issues

set -e

echo "╔═══════════════════════════════════════════════════╗"
echo "║  ZARA Interpreter - Alternative Build (Java 25)  ║"
echo "║  Using Maven Wrapper...                           ║"
echo "╚═══════════════════════════════════════════════════╝"
echo ""

cd "$(dirname "$0")" || exit 1

# Check if Maven is available
if command -v mvn &> /dev/null; then
    echo "✓ Maven found, using system Maven"
    mvn clean package -DskipTests
    exit 0
fi

# Check if Maven wrapper exists
if [ -f "./mvnw" ]; then
    echo "✓ Maven Wrapper found"
    chmod +x ./mvnw
    ./mvnw clean package -DskipTests
    exit 0
fi

# Download Maven Wrapper (Apache Maven)
echo "Downloading Maven Wrapper from Apache..."
if ! wget -q https://raw.githubusercontent.com/apache/maven-wrapper/master/mvnw -O ./mvnw 2>/dev/null; then
    echo "❌ Failed to download Maven Wrapper"
    echo ""
    echo "Alternative: Please install Maven manually:"
    echo "  Ubuntu/Debian: sudo apt install maven"
    echo "  macOS: brew install maven"
    echo "  Or download from: https://maven.apache.org/download.cgi"
    exit 1
fi

chmod +x ./mvnw
echo "✓ Maven Wrapper downloaded"
echo ""
echo "Building with Maven Wrapper..."
./mvnw clean package -DskipTests

echo ""
echo "✓ Build completed!"
