import java.util.ArrayList;
import java.util.List;

public class Parser {
    private List<Token> tokens;
    private int pos;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.pos    = 0;
    }

    private Token current() {
        return tokens.get(pos);
    }

    private Token consume(TokenType expected) {
        Token t = current();
        if (t.type != expected) {
            throw new RuntimeException(
                "Syntax Error: Expected " + expected +
                " but got '" + t.value + "' (" + t.type + ")"
            );
        }
        pos++;
        return t;
    }

    public List<Instruction> parse() {
        List<Instruction> instructions = new ArrayList<>();
        while (current().type != TokenType.EOF) {
            instructions.add(parseInstruction());
        }
        return instructions;
    }

    private Instruction parseInstruction() {
        Token t = current();
        if (t.type == TokenType.SET)  return parseAssign();
        if (t.type == TokenType.SHOW) return parsePrint();
        if (t.type == TokenType.WHEN) return parseIf();
        if (t.type == TokenType.LOOP) return parseLoop();
        throw new RuntimeException("Unknown instruction: '" + t.value + "'");
    }

    private Instruction parseAssign() {
        consume(TokenType.SET);
        String name = consume(TokenType.IDENTIFIER).value;
        consume(TokenType.EQUALS);
        Expression expr = parseExpression();
        return new AssignInstruction(name, expr);
    }

    private Instruction parsePrint() {
        consume(TokenType.SHOW);
        Expression expr = parseExpression();
        return new PrintInstruction(expr);
    }

    private Instruction parseIf() {
        consume(TokenType.WHEN);
        Expression condition = parseExpression();
        consume(TokenType.LBRACE);
        List<Instruction> body = new ArrayList<>();
        while (current().type != TokenType.RBRACE && current().type != TokenType.EOF) {
            body.add(parseInstruction());
        }
        consume(TokenType.RBRACE);
        return new IfInstruction(condition, body);
    }

    private Instruction parseLoop() {
        consume(TokenType.LOOP);
        int times = (int) Double.parseDouble(consume(TokenType.NUMBER).value);
        consume(TokenType.LBRACE);
        List<Instruction> body = new ArrayList<>();
        while (current().type != TokenType.RBRACE && current().type != TokenType.EOF) {
            body.add(parseInstruction());
        }
        consume(TokenType.RBRACE);
        return new RepeatInstruction(times, body);
    }

    // Handles: + - > < ==
    private Expression parseExpression() {
        Expression left = parseTerm();
        while (current().type == TokenType.PLUS  ||
               current().type == TokenType.MINUS  ||
               current().type == TokenType.GT     ||
               current().type == TokenType.LT     ||
               current().type == TokenType.EQEQ) {
            String op = current().value;
            pos++;
            Expression right = parseTerm();
            left = new BinaryOpNode(left, op, right);
        }
        return left;
    }

    // Handles: * /
    private Expression parseTerm() {
        Expression left = parsePrimary();
        while (current().type == TokenType.MULTIPLY ||
               current().type == TokenType.DIVIDE) {
            String op = current().value;
            pos++;
            Expression right = parsePrimary();
            left = new BinaryOpNode(left, op, right);
        }
        return left;
    }

    // Simplest unit: number, string, variable
    private Expression parsePrimary() {
        Token t = current();
        if (t.type == TokenType.NUMBER) {
            pos++;
            return new NumberNode(Double.parseDouble(t.value));
        }
        if (t.type == TokenType.STRING) {
            pos++;
            return new StringNode(t.value);
        }
        if (t.type == TokenType.IDENTIFIER) {
            pos++;
            return new VariableNode(t.value);
        }
        if (t.type == TokenType.LPAREN) {
            pos++;
            Expression expr = parseExpression();
            consume(TokenType.RPAREN);
            return expr;
        }
        throw new RuntimeException("Unexpected token: '" + t.value + "'");
    }
}
