package ExprAST;

import BackEnd.GameDataStorage;
import ErrorAndException.EvalError;

public class BinaryArithExpr implements Expr {
    private final Expr left;
    private final Expr right;
    private final String op;
    public BinaryArithExpr(
            Expr left, String op, Expr right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }
    public long eval(GameDataStorage gameData)throws EvalError  {
        long lv = left.eval(gameData);
        long rv = right.eval(gameData);
        if (op.equals("+")) return lv + rv;
        if (op.equals("-")) return lv - rv;
        if (op.equals("*")) return lv * rv;
        if (op.equals("/"))
        {
            if(rv==0)
            {
                throw new ArithmeticException(" Division by zero");
            }
            return (long) Math.floor(lv / rv);
        }
        if (op.equals("%"))
        {
            if(rv==0) {
                throw new ArithmeticException(" Modulo by zero");
            }
            return  lv %  rv;
        }
        if(op.equals(("^"))) return (long) Math.floor(Math.pow(lv,rv));
        throw new EvalError("unknown op: " + op);
    }
    public void prettyPrint(StringBuilder s) {
        s.append("(");
        left.prettyPrint(s);
        s.append(op);
        right.prettyPrint(s);
        s.append(")");
    }

}
