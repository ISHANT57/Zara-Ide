#!/bin/bash

# ZARA Interpreter - Server Manager
# Provides commands to start, stop, restart, and monitor the ZARA interpreter server

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
JAR_FILE="$SCRIPT_DIR/target/zara-interpreter-1.0.0.jar"
PORT=8080

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Check if JAR exists
check_jar() {
    if [ ! -f "$JAR_FILE" ]; then
        echo -e "${RED}✗ JAR file not found: $JAR_FILE${NC}"
        echo "Please run: ./build.sh"
        exit 1
    fi
}

# Start server
start_server() {
    check_jar
    
    echo -e "${BLUE}Starting ZARA Interpreter...${NC}"
    
    # Kill any existing process on port 8080
    if netstat -tuln 2>/dev/null | grep -q ":$PORT "; then
        echo -e "${YELLOW}Port $PORT is already in use, stopping existing process...${NC}"
        pkill -f "zara-interpreter" 2>/dev/null || true
        sleep 2
    fi
    
    # Start server
    java -jar "$JAR_FILE" > /tmp/zara-server.log 2>&1 &
    SERVER_PID=$!
    
    # Wait for server to start
    echo "Waiting for server to start..."
    sleep 5
    
    # Check if server is running
    if kill -0 $SERVER_PID 2>/dev/null; then
        echo -e "${GREEN}✓ ZARA Interpreter started successfully!${NC}"
        echo -e "  PID: $SERVER_PID"
        echo -e "  Port: $PORT"
        echo -e "  URL: http://localhost:$PORT"
        echo -e "  Logs: /tmp/zara-server.log"
    else
        echo -e "${RED}✗ Failed to start ZARA Interpreter${NC}"
        echo "Check logs: cat /tmp/zara-server.log"
        exit 1
    fi
}

# Stop server
stop_server() {
    echo -e "${BLUE}Stopping ZARA Interpreter...${NC}"
    
    if pkill -f "zara-interpreter" 2>/dev/null; then
        echo -e "${GREEN}✓ ZARA Interpreter stopped${NC}"
    else
        echo -e "${YELLOW}✓ No ZARA process found${NC}"
    fi
}

# Restart server
restart_server() {
    stop_server
    sleep 2
    start_server
}

# Check server status
status_server() {
    if pgrep -f "zara-interpreter" > /dev/null; then
        PID=$(pgrep -f "zara-interpreter")
        echo -e "${GREEN}✓ ZARA Interpreter is running (PID: $PID)${NC}"
        echo "  URL: http://localhost:$PORT"
        
        # Test API
        if command -v curl &> /dev/null; then
            if curl -s http://localhost:$PORT/api/health > /dev/null 2>&1; then
                echo -e "${GREEN}✓ API is responding${NC}"
            else
                echo -e "${RED}✗ API is not responding${NC}"
            fi
        fi
    else
        echo -e "${RED}✗ ZARA Interpreter is not running${NC}"
    fi
}

# Show logs
show_logs() {
    if [ -f "/tmp/zara-server.log" ]; then
        echo -e "${BLUE}Server logs:${NC}"
        tail -50 /tmp/zara-server.log
    else
        echo -e "${YELLOW}No log file found${NC}"
    fi
}

# Test examples
test_examples() {
    echo -e "${BLUE}Testing example programs...${NC}"
    
    if [ ! -f "examples-diagnostic.py" ]; then
        echo -e "${RED}examples-diagnostic.py not found${NC}"
        exit 1
    fi
    
    python3 examples-diagnostic.py
}

# Show help
show_help() {
    cat << EOF
ZARA Interpreter - Server Manager

USAGE: ./manage-server.sh [command]

COMMANDS:
  start      - Start the ZARA Interpreter server
  stop       - Stop the ZARA Interpreter server
  restart    - Restart the ZARA Interpreter server
  status     - Check server status
  logs       - Show server logs (last 50 lines)
  test       - Run example diagnostics
  help       - Show this help message

EXAMPLES:
  ./manage-server.sh start     # Start server
  ./manage-server.sh restart   # Restart server
  ./manage-server.sh status    # Check if running
  ./manage-server.sh test      # Test all examples

SERVER INFO:
  URL: http://localhost:$PORT
  Docs: http://localhost:$PORT/docs
  API: http://localhost:$PORT/api/health

EOF
}

# Main
case "${1:-help}" in
    start)
        start_server
        ;;
    stop)
        stop_server
        ;;
    restart)
        restart_server
        ;;
    status)
        status_server
        ;;
    logs)
        show_logs
        ;;
    test)
        test_examples
        ;;
    help|--help|-h)
        show_help
        ;;
    *)
        echo -e "${RED}Unknown command: $1${NC}"
        echo "Run './manage-server.sh help' for usage"
        exit 1
        ;;
esac
