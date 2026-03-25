public class BinaryOpNode implements Expression{
    private final Expression left;
    private final Expression right;
    private final String op;

    public BinaryOpNode(Expression left, String op, Expression right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    @Override
    public Object evaluate(Environment env) {
        Object l = left.evaluate(env);
        Object r = right.evaluate(env);

        if (l instanceof Double && r instanceof Double) {
            double ld=(Double)l, rd=(Double)r;
            switch(op) {
                case "+": return ld+rd;   
                case "-": return ld-rd;
                case "*": return ld*rd;   
                case "/": return ld/rd;
                case ">": return ld>rd;   
                case "<": return ld<rd;
                case "==": return ld==rd;
            }
        }
        if (op.equals("+"))  
            return l.toString()+r.toString();
        if (op.equals("==")) 
            return l.equals(r);

        throw new RuntimeException("Unknown operator: " + op);
    }
}