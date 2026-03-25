
public class ExpressionTest {
    public static void main(String[] args) {

        Environment env = new Environment();

        // Set variables
        env.set("x", 10.0);
        env.set("y", 3.0);

        // y * 2
        Expression mul = new BinaryOpNode(
                new VariableNode("y"),
                "*",
                new NumberNode(2)
        );

        // x + (y * 2)
        Expression add = new BinaryOpNode(
                new VariableNode("x"),
                "+",
                mul
        );

        Object result = add.evaluate(env);

        System.out.println("Expected: 16");
        System.out.println("Actual: " + result);

        if (result.equals(16.0)) {
            System.out.println("Test Passed");
        } else {
            System.out.println("Test Failed");
        }
    }
}