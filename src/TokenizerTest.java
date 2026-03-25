import java.util.*;

public class TokenizerTest {
    public static void main(String[] args) {

        List<String> testCases = List.of(
                "set x = 5",
                "show \"Hello\"",
                "set score = 85\nwhen score > 50:\nshow \"Pass\"",
                "set i = 1\nloop 4:\nshow i\nset i = i + 1"
        );

        testCases.forEach(input -> {
            System.out.println("INPUT:\n" + input);

            Tokenizer tokenizer = new Tokenizer(input);
            List<Token> tokens = tokenizer.tokenize();

            tokens.forEach(System.out::println);

           System.out.println("----------------");
        });
    }
}