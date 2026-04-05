# ZARA Interpreter - Spring Boot Web Application

A modern web-based deployment of the ZARA programming language interpreter built with Spring Boot, featuring a beautiful interactive frontend and comprehensive REST API.

## Features

✨ **Interactive Web UI**
- Code editor with syntax highlighting
- Real-time code execution
- Beautiful output visualization
- Execution history tracking
- Syntax guide and examples

🚀 **REST API**
- Execute ZARA code via HTTP
- Session-based execution history
- Real-time output capture
- Health monitoring
- Performance metrics

💻 **Core Features**
- ZARA language interpreter
- Variable management
- Arithmetic operations
- String manipulation
- Conditionals (when)
- Loops (loop)
- Error handling

## Quick Start

### Prerequisites
- Java 17+
- Maven 3.8+

### Installation & Run
```bash
# Build the project
mvn clean package

# Run the application
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`

## Project Structure
```
springboot/
├── src/main/java/com/zara/interpreter/
│   ├── ZaraInterpreterApplication.java    # Main application
│   ├── controller/                        # REST & Web controllers
│   ├── service/                           # Business logic
│   ├── model/                             # Data models
│   └── core/                              # ZARA interpreter engine
├── src/main/resources/
│   ├── application.properties             # Configuration
│   ├── templates/
│   │   ├── index.html                     # Web UI
│   │   └── docs.html                      # Documentation
│   └── static/                            # CSS/JS assets
├── pom.xml                                # Maven dependencies
└── DEPLOYMENT_GUIDE.md                    # Detailed deployment info
```

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/execute` | Execute ZARA code |
| GET | `/api/history/{sessionId}` | Get execution history |
| GET | `/api/execution/{executionId}` | Get execution details |
| GET | `/api/health` | Check service status |
| GET | `/api/stats` | Get statistics |
| DELETE | `/api/history/{sessionId}` | Clear history |

## Example Usage

### Via Web UI
1. Open http://localhost:8080/
2. Write ZARA code in the editor
3. Click "Execute" button
4. View output and execution history

### Via REST API
```bash
curl -X POST http://localhost:8080/api/execute \
  -H "Content-Type: application/json" \
  -d '{"code": "set x = 10\nshow x", "sessionId": "session-1"}'
```

## ZARA Language Example

```zara
set x = 10
set y = 20
set sum = x + y
show "Sum is: " + sum

when sum > 25: show "Sum is greater than 25"

loop 3: show "Hello!"
```

## Deployment

See [DEPLOYMENT_GUIDE.md](./DEPLOYMENT_GUIDE.md) for detailed deployment instructions for:
- Local development
- Docker
- Heroku
- AWS
- Google Cloud
- Azure
- Kubernetes

## Configuration

Edit `src/main/resources/application.properties`:
- Change server port: `server.port=8080`
- Set logging level: `logging.level.com.zara.interpreter=DEBUG`
- Configure max execution time: `zara.max-execution-time-ms=30000`

## Build Options

```bash
# Clean build
mvn clean package

# Skip tests
mvn clean package -DskipTests

# Run directly
mvn spring-boot:run

# Run with custom port
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=9090"

# Check dependencies
mvn dependency:tree
```

## Documentation

- **Web UI Guide:** Access `/docs` for complete language documentation
- **API Reference:** See [DEPLOYMENT_GUIDE.md](./DEPLOYMENT_GUIDE.md#rest-api-reference)
- **ZARA Syntax:** Check the built-in syntax guide in the web UI

## Features Coming Soon

- 🎨 Syntax highlighting for code editor
- 📊 Execution statistics dashboard
- 💾 Save/load programs
- 🌙 Dark mode
- 🔒 User authentication
- 📱 Mobile app
- 🐛 Advanced debugging tools

## License

Same as the parent ZARA Interpreter project

## Support

For issues, suggestions, or contributions, refer to the main project repository.

---

**Last Updated:** 2026  
**Version:** 1.0.0
