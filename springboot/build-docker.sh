#!/bin/bash

# ZARA Interpreter - Docker Build Workaround
# Builds the Spring Boot app inside a container, avoiding local dependency issues

echo "╔═══════════════════════════════════════════════════╗"
echo "║  ZARA Interpreter - Docker Build                 ║"
echo "║  Building & running in isolated container...     ║"
echo "╚═══════════════════════════════════════════════════╝"
echo ""

cd "$(dirname "$0")" || exit 1

# Check if Docker is installed
if ! command -v docker &> /dev/null; then
    echo "❌ Docker is not installed"
    echo ""
    echo "To install Docker:"
    echo "  Ubuntu/Debian: sudo apt install docker.io"
    echo "  macOS: brew install docker"
    echo "  Windows: Download from https://www.docker.com/products/docker-desktop"
    echo ""
    echo "Or try: ./build.sh (using system Maven)"
    exit 1
fi

echo "✓ Docker found"
echo ""
echo "Building Docker image..."
docker build -t zara-interpreter:local .

if [ $? -eq 0 ]; then
    echo ""
    echo "╔═══════════════════════════════════════════════════╗"
    echo "║  ✓ Build Successful!                             ║"
    echo "╚═══════════════════════════════════════════════════╝"
    echo ""
    echo "To run the application:"
    echo "  docker run -p 8080:8080 zara-interpreter:local"
    echo ""
    echo "Or use docker-compose:"
    echo "  docker-compose up"
    echo ""
    echo "Then open: http://localhost:8080"
else
    echo "❌ Docker build failed"
    exit 1
fi
