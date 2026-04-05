# 🚀 ZARA Interpreter - Running Instructions

<div align="center">

**How to Compile and Run ZARA Programs**

*Step-by-step guide to execute your ZARA language interpreter*

</div>

---

## 📋 Prerequisites

Before running ZARA programs, ensure you have:
- **Java Development Kit (JDK)** installed (version 8 or higher)
- **Terminal/Command Prompt** access
- **ZARA source files** (`.zara` extension)

---

## 🛠️ Compilation Steps

### 1. Navigate to Project Directory
```bash
cd /home/ishant57/Zara/ZARA-Interpreter-Engine
```

### 2. Compile Java Source Files
```bash
javac src/*.java
```

**Expected Output:** No errors (clean compilation)

**What this does:**
- Compiles all `.java` files in the `src/` directory
- Generates `.class` bytecode files
- Prepares the interpreter for execution

---

## ▶️ Running ZARA Programs

### Basic Command Structure
```bash
java -cp src Main <zara-file-path>
```

### Parameter Explanation
- `java`: Java runtime command
- `-cp src`: Classpath pointing to compiled classes in `src/` directory
- `Main`: Main class containing the entry point
- `<zara-file-path>`: Path to your `.zara` program file

---

## 📚 Example Executions

### Example 1: Arithmetic Operations
```bash
java -cp src Main examples/program1_arithmetic.zara
```

**Program Content:**
```zara
set x = 10
set y = 3
set result = x + y * 2
show result
```

**Expected Output:**
```
ZARA Interpreter
Running: examples/program1_arithmetic.zara
16
Done!
```

### Example 2: String Operations
```bash
java -cp src Main examples/program2_strings.zara
```

**Program Content:**
```zara
set name = "Sitare"
show name
show "Hello from ZARA"
```

**Expected Output:**
```
ZARA Interpreter
Running: examples/program2_strings.zara
Sitare
Hello from ZARA
Done!
```

### Example 3: Conditional Logic
```bash
java -cp src Main examples/program3_conditional.zara
```

**Program Content:**
```zara
set score = 85
when score > 50:
    show "Pass"
```

**Expected Output:**
```
ZARA Interpreter
Running: examples/program3_conditional.zara
Pass
Done!
```

### Example 4: Loop Operations
```bash
java -cp src Main examples/program4_loop.zara
```

**Program Content:**
```zara
set i = 1
loop 4:
    show i
    set i = i + 1
```

**Expected Output:**
```
ZARA Interpreter
Running: examples/program4_loop.zara
1
2
3
4
Done!
```

---

## 🔧 Creating Your Own ZARA Programs

### 1. Create a New File
```bash
touch myprogram.zara
```

### 2. Write ZARA Code
Edit the file with any text editor and write ZARA syntax:

```zara
# Example: Calculate and display area
set length = 5
set width = 3
set area = length * width
show "Rectangle Area:"
show area
```

### 3. Run Your Program
```bash
java -cp src Main myprogram.zara
```

---

## 🐛 Troubleshooting

### Compilation Errors
**Problem:** `javac: file not found: src/*.java`
**Solution:** Ensure you're in the correct directory and `src/` folder exists

**Problem:** Java syntax errors during compilation
**Solution:** Check the Java source files for syntax issues

### Runtime Errors
**Problem:** `Error: Could not find or load main class Main`
**Solution:** Ensure compilation completed successfully and use correct classpath

**Problem:** `File not found` error
**Solution:** Check that the `.zara` file path is correct

**Problem:** Runtime exceptions during execution
**Solution:** Check your ZARA program syntax against the language specification

### Common Issues
- **Wrong directory**: Always run commands from the project root
- **Missing compilation**: Always compile before running
- **File permissions**: Ensure read permissions on `.zara` files
- **Java version**: Ensure JDK 8+ is installed

---

## 📁 Project Structure

```
ZARA-Interpreter-Engine/
├── src/                    # Java source files
│   ├── Main.java          # Entry point
│   ├── Interpreter.java   # Main execution engine
│   ├── Parser.java        # Syntax parser
│   ├── Tokenizer.java     # Lexical analyzer
│   └── *.java             # Other components
├── examples/              # Sample ZARA programs
│   ├── program1_arithmetic.zara
│   ├── program2_strings.zara
│   ├── program3_conditional.zara
│   └── program4_loop.zara
└── *.md                   # Documentation files
```

---

## ⚡ Quick Commands Reference

```bash
# One-time setup
cd /home/ishant57/Zara/ZARA-Interpreter-Engine
javac src/*.java

# Run examples
java -cp src Main examples/program1_arithmetic.zara
java -cp src Main examples/program2_strings.zara
java -cp src Main examples/program3_conditional.zara
java -cp src Main examples/program4_loop.zara

# Run custom program
java -cp src Main myprogram.zara
```

---

## 🎯 ZARA Language Syntax Quick Reference

| Feature | Syntax | Example |
|---------|--------|---------|
| Variables | `set name = value` | `set x = 10` |
| Arithmetic | `+ - * /` | `set result = x + y * 2` |
| Strings | `"text"` | `set name = "Hello"` |
| Output | `show value` | `show result` |
| Conditionals | `when condition:` | `when score > 50:` |
| Loops | `loop count:` | `loop 4:` |

---

<div align="center">

**Happy Coding with ZARA! 🎉**

*Remember: Compile once, run many times*

</div></content>
<parameter name="filePath">/home/ishant57/Zara/ZARA-Interpreter-Engine/INSTRUCTION.md