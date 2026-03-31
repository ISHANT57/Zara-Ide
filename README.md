# 🌟 ZARA Language Interpreter

<div align="center">

**A Mini Programming Language — Built in Pure Java**

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![University Project](https://img.shields.io/badge/Sitare%20University-Advanced%20OOP-blue?style=for-the-badge)](https://univ.sitare.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg?style=for-the-badge)](https://mit-license.org/)

*Create. Interpret. Execute. — Your journey into language design starts here.*

[📖 Syntax Guide](#-zara-syntax) • [🚀 Quick Start](#-quick-start) • [📚 Examples](#-examples) • [🏗️ Architecture](#-project-architecture)

</div>

---

## ✨ What is ZARA?

**ZARA** stands for **Z**ero-ceremony **A**rithmetic and **R**easoning **A**ssembler.

It's a small, clean, minimal programming language — like Python but even simpler. You write code in ZARA, and your Java-built interpreter reads it, understands it, and runs it — line by line.

When you're done, a user can write a `.zara` file, run your program, and see real output on screen. That is not a toy exercise — that is genuinely impressive software.

> 🎯 **Learning Goal**: This project teaches you how programming languages work under the hood. You'll build a complete interpreter from scratch, understanding tokenization, parsing, and execution.

---

## 🎯 Features

- ✅ **Variables & Assignment** - Simple `set` keyword for variable declaration
- ✅ **Arithmetic Operations** - Full support for `+`, `-`, `*`, `/` with proper precedence
- ✅ **String Handling** - Double-quoted strings with concatenation
- ✅ **Conditional Logic** - `when` statements for decision making
- ✅ **Loop Constructs** - `loop` with iteration count
- ✅ **Output Display** - `show` command for printing values and text
- ✅ **Clean Syntax** - Minimal, readable language design

---

## 🚀 Quick Start

### Prerequisites
- Java 8 or higher
- Basic understanding of OOP concepts

### Running the Interpreter

1. **Clone and navigate to the project:**
   ```bash
   cd ZARA-Interpreter-Engine
   ```

2. **Compile the Java files:**
   ```bash
   javac src/*.java
   ```

3. **Run a ZARA program:**
   ```bash
   java -cp src Main examples/program1_arithmetic.zara
   ```

### Expected Output:
```
╔══════════════════════════════╗
║    ZARA Language Interpreter  ║
║    Version 1.0                ║
╚══════════════════════════════╝

>>> Running: examples/program1_arithmetic.zara

16

>>> Done!
```

---

## 📖 ZARA Syntax

### Variables
```zara
set x = 10
set name = "Hello World"
```

### Arithmetic Operations
```zara
set result = x + y * 2  # Follows standard precedence
set total = (a + b) * c # Parentheses supported
```

### Output
```zara
show result           # Display variable value
show "Hello ZARA"     # Display string literal
```

### Conditionals
```zara
when score > 50:
    show "Pass"
```

### Loops
```zara
set i = 1
loop 4:
    show i
    set i = i + 1
```

---

## 📚 Examples

### Program 1: Arithmetic & Variables
```zara
set x = 10
set y = 3
set result = x + y * 2
show result
```
**Output:** `16`

### Program 2: String Operations
```zara
set name = "Sitare"
show name
show "Hello from ZARA"
```
**Output:**
```
Sitare
Hello from ZARA
```

### Program 3: Conditional Logic
```zara
set score = 85
when score > 50:
    show "Pass"
```
**Output:** `Pass`

### Program 4: Loop Construct
```zara
set i = 1
loop 4:
    show i
    set i = i + 1
```
**Output:**
```
1
2
3
4
```

---

## 🏗️ Project Architecture

```
ZARA-Interpreter-Engine/
├── src/
│   ├── Main.java              # Entry point, file I/O
│   ├── Interpreter.java       # Main execution engine
│   ├── Parser.java            # Syntax analysis & AST building
│   ├── Tokenizer.java         # Lexical analysis
│   ├── Token.java             # Token representation
│   ├── TokenType.java         # Token type definitions
│   ├── Environment.java       # Variable storage & scope
│   ├── Instruction.java       # Base instruction class
│   ├── AssignInstruction.java # Variable assignment
│   ├── PrintInstruction.java  # Output instruction
│   ├── IfInstruction.java     # Conditional execution
│   ├── RepeatInstruction.java # Loop execution
│   ├── Expression.java        # Expression evaluation
│   ├── BinaryOpNode.java      # Binary operations
│   ├── NumberNode.java        # Numeric literals
│   ├── StringNode.java        # String literals
│   ├── VariableNode.java      # Variable references
│   └── *Test.java             # Unit tests
├── examples/
│   ├── program1_arithmetic.zara
│   ├── program2_strings.zara
│   ├── program3_conditional.zara
│   └── program4_loop.zara
├── ZARA_SYNTAX.md             # Language reference
└── README.md                  # This file
```

### Core Components

1. **Tokenizer** - Breaks source code into tokens
2. **Parser** - Builds Abstract Syntax Tree (AST)
3. **Interpreter** - Executes the AST with environment management
4. **Environment** - Manages variable storage and scope

---

## 🧪 Testing

Run the test suite:
```bash
javac src/*.java
java -cp src Test
```

Individual component tests:
```bash
java -cp src TokenizerTest
java -cp src ExpressionTest
```

---

## 📋 Development Tips

### Build Incrementally
- ✅ Start with Tokenizer (break up single lines)
- ✅ Then Parser (handle one instruction type)
- ✅ Finally Interpreter (execute correctly)
- ✅ Test with simplest programs first

### Work Division Strategy
- **Member 1**: Tokenizer & Token classes
- **Member 2**: Parser & Expression classes
- **Member 3**: Interpreter & Instruction classes
- **All members**: Must understand entire codebase for viva

### Error Handling
- Read Java exception messages carefully
- They tell you exactly what's wrong and where
- Don't randomly change code hoping to fix issues

---

## 🎓 Academic Context

This project is part of the **Advanced OOP in Java** course at **Sitare University**.

### Learning Objectives
- Understanding interpreter architecture
- Implementing lexical analysis
- Building recursive descent parsers
- Managing execution environments
- Handling operator precedence
- Creating domain-specific languages

### Assessment
- **Code Submission**: Complete, working interpreter
- **Viva Examination**: 15-minute oral defense (all team members)
- **Individual Evaluation**: Each member explains their code sections

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Code Style
- Follow Java naming conventions
- Add comments for complex logic
- Write unit tests for new features
- Keep methods focused and single-purpose

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](https://mit-license.org/) file for details.

---

## 🙏 Acknowledgments

- **Sitare University** for the challenging and educational project
- **Advanced OOP Course** instructors for guidance
- **Java Community** for the robust platform

---

<div align="center">

**Happy Coding! 🚀**

*Built with ❤️ by ZARA Interpreter Team*

</div>


