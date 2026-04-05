@echo off
REM ZARA Interpreter - Build Script (Windows)
REM This script builds the ZARA Interpreter Spring Boot application

echo.
echo ╔═══════════════════════════════════════════════════╗
echo ║  ZARA Interpreter - Spring Boot Setup             ║
echo ║  Version 1.0.0                                    ║
echo ╚═══════════════════════════════════════════════════╝
echo.

REM Check if Java is installed
echo Checking Java installation...
where java >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo ❌ Java is not installed or not in PATH.
    echo Please install Java 17 or higher or add it to your PATH.
    echo Download from: https://www.oracle.com/java/technologies/downloads/
    exit /b 1
)

for /f "tokens=3" %%g in ('java -version 2^>^&1 ^| find "version"') do (
    echo ✓ Java found: %%g
)

REM Check if Maven is installed
echo.
echo Checking Maven installation...
where mvn >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo ❌ Maven is not installed or not in PATH.
    echo Please install Maven 3.8 or higher.
    echo Download from: https://maven.apache.org/download.cgi
    exit /b 1
)
echo ✓ Maven found

echo.
echo Building ZARA Interpreter Spring Boot Application...
echo.

REM Clean previous builds
echo Step 1: Cleaning previous builds...
call mvn clean
if %ERRORLEVEL% NEQ 0 (
    echo ❌ Clean failed
    exit /b 1
)

REM Compile and package
echo.
echo Step 2: Compiling and packaging...
call mvn package -DskipTests
if %ERRORLEVEL% NEQ 0 (
    echo ❌ Build failed
    exit /b 1
)

echo.
echo ╔═══════════════════════════════════════════════════╗
echo ║  ✓ Build Successful!                             ║
echo ╚═══════════════════════════════════════════════════╝
echo.

set JAR_FILE=target\zara-interpreter-1.0.0.jar

if exist "%JAR_FILE%" (
    echo JAR file created: %JAR_FILE%
    echo.
    echo To run the application:
    echo   java -jar %JAR_FILE%
    echo.
    echo Or use Maven:
    echo   mvn spring-boot:run
    echo.
    echo Then open your browser to:
    echo   http://localhost:8080
) else (
    echo ❌ JAR file not found
    exit /b 1
)
