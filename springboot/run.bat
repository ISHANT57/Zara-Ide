@echo off
REM ZARA Interpreter - Run Script (Windows)
REM Runs the ZARA Interpreter Spring Boot application

echo.
echo ╔═══════════════════════════════════════════════════╗
echo ║  ZARA Interpreter - Spring Boot                  ║
echo ║  Starting application...                          ║
echo ╚═══════════════════════════════════════════════════╝
echo.

set JAR_FILE=target\zara-interpreter-1.0.0.jar

if not exist "%JAR_FILE%" (
    echo ❌ JAR file not found. Running build first...
    call build.bat
    if %ERRORLEVEL% NEQ 0 (
        echo Build failed
        exit /b 1
    )
)

echo Starting ZARA Interpreter...
echo Configuration: src/main/resources/application.properties
echo.
echo 📍 Application URL: http://localhost:8080
echo 📍 Documentation: http://localhost:8080/docs
echo 📍 API Health: http://localhost:8080/api/health
echo.
echo Press Ctrl+C to stop the application
echo.

set PORT=8080
if not "%1"=="" set PORT=%1

java -jar "%JAR_FILE%" --server.port=%PORT%
