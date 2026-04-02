import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║    ZARA Language Interpreter  ║");
        System.out.println("║    Version 1.0                ║");
        System.out.println("╚══════════════════════════════╝");

        if (args.length == 0) {
            System.out.println("\nUsage:   java Main <file.zara>");
            System.out.println("Example: java Main hello.zara\n");
            return;
        }

        String filename = args[0];
        try {
            String sourceCode = new String(Files.readAllBytes(Paths.get(filename)));
            System.out.println("\n>>> Running: " + filename + "\n");

            Interpreter interpreter = new Interpreter();
            interpreter.run(sourceCode);

            System.out.println("\n>>> Done!");

        } catch (IOException e) {
            System.err.println("\n[ERROR] File not found: " + filename);
        } catch (RuntimeException e) {
            System.err.println("\n[ZARA ERROR] " + e.getMessage());
        }
    } 
}
