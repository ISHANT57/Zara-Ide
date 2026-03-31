public class PrintInstruction implements Instruction {

    private final Expression expr;

    public PrintInstruction(Expression expr) {
        this.expr = expr;
    }

    @Override
    public void execute(Environment env) {

        Object result = expr.evaluate(env);

        if (result instanceof Double) {
            double d = (Double) result;

            if (d == Math.floor(d) && !Double.isInfinite(d)) {
                System.out.println((long) d);
                return;
            }
        }

        System.out.println(result);
    }
}
