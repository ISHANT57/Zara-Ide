import java.util.List;

public class Interpreter {
    public void run(String sourceCode) {
        // STEP 1: Tokenize
        Tokenizer tokenizer = new Tokenizer(sourceCode);
        List<Token> tokens = tokenizer.tokenize();

        // STEP 2: Parse
        Parser parser = new Parser(tokens);
        List<Instruction> instructions = parser.parse();

        // STEP 3: Execute
        Environment env = new Environment();
        for (Instruction instr : instructions) {
            instr.execute(env);
        }
    }
}
