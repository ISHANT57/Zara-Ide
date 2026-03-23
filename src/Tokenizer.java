import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private final String source;
    private int pos;
    private int line;

    public Tokenizer(String source) {
        this.source = source;
        this.pos = 0;
        this.line = 1;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while (pos < source.length()) {

            char current = source.charAt(pos);

            if (Character.isWhitespace(current)) {
                skipSpaces();
                continue;
            }

            if (Character.isDigit(current)) {
                tokens.add(readNumber());
                continue;
            }

            if (current == '"') {
                tokens.add(readString());
                continue;
            }

            if (current == '=') {
                if (pos + 1 < source.length() && source.charAt(pos + 1) == '=') {
                    tokens.add(new Token(TokenType.EQEQ, "==", line));
                    pos += 2;
                } else {
                    tokens.add(new Token(TokenType.EQUALS, "=", line));
                    pos++;
                }
                continue;
            }

            if (Character.isLetter(current)) {
                tokens.add(readIdentifierOrKeyword());
                continue;
            }

            pos++;
        }

        tokens.add(new Token(TokenType.EOF, "", line));
        return tokens;
    }

    private void skipSpaces() {
        while (pos < source.length() && Character.isWhitespace(source.charAt(pos))) {
            if (source.charAt(pos) == '\n') line++;
            pos++;
        }
    }

    private Token readNumber() {
        int start = pos;
        while (pos < source.length() &&
                (Character.isDigit(source.charAt(pos)) || source.charAt(pos) == '.')) {
            pos++;
        }
        return new Token(TokenType.NUMBER, source.substring(start, pos), line);
    }

    private Token readString() {
        pos++;
        int start = pos;
        while (pos < source.length() && source.charAt(pos) != '"') {
            if (source.charAt(pos) == '\n') line++;
            pos++;
        }

        String text = source.substring(start, pos);
        if (pos < source.length()) pos++;
        return new Token(TokenType.STRING, text, line);
    }

    private Token readIdentifierOrKeyword() {
        int start = pos;

        while (pos < source.length() &&
                (Character.isLetterOrDigit(source.charAt(pos)) ||
                 source.charAt(pos) == '_')) {
            pos++;
        }

        String word = source.substring(start, pos);

        switch (word) {
            case "set":
                return new Token(TokenType.SET, word, line);
            case "show":
                return new Token(TokenType.SHOW, word, line);
            case "when":
                return new Token(TokenType.WHEN, word, line);
            case "loop":
                return new Token(TokenType.LOOP, word, line);
            default:
                return new Token(TokenType.IDENTIFIER, word, line);
        }
    }

    private Token readSymbol() {
        char c = source.charAt(pos);
        switch (c) {
            case '+': 
                pos++; 
                return new Token(TokenType.PLUS, "+", line);
            case '-': 
                pos++; 
                return new Token(TokenType.MINUS, "-", line);
            case '*': 
                pos++; 
                return new Token(TokenType.MULTIPLY, "*",line);
            case '/': 
                pos++; 
                return new Token(TokenType.DIVIDE, "/", line);
            case ':': 
                pos++; 
                return new Token(TokenType.COLON, ":", line);
            case '>': 
                pos++; 
                return new Token(TokenType.GT, ">", line);
            case '<': 
                pos++; 
                return new Token(TokenType.LT, "<", line);
            case '=':
                if (pos+1 < source.length() && source.charAt(pos+1)=='=') {
                    pos += 2; return new Token(TokenType.EQEQ, "==", line);
                }
                pos++; 
                return new Token(TokenType.EQUALS, "=", line);
            default: 
                pos++; 
                return new Token(TokenType.IDENTIFIER, ""+c, line);
        }
    }
}