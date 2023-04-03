package StatementAST;

import BackEnd.GameDataStorage;
import ExprAST.Expr;

public class IfStatement implements Statement {
    private Expr expression;
    private Statement stateTure, stateFalse;

    public IfStatement(Expr expression, Statement stateTure, Statement stateFalse) {
        this.expression = expression;
        this.stateTure = stateTure;
        this.stateFalse = stateFalse;
    }

    @Override
    public boolean eval(GameDataStorage gameData) {
        boolean doneSignal = false;
        long val = this.expression.eval(gameData);
        if (val > 0) {
            doneSignal = this.stateTure.eval(gameData);
        } else {
            doneSignal = this.stateFalse.eval(gameData);
        }
        return doneSignal;
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append("if ( ");
        expression.prettyPrint(s);
        s.append(" ) ");
        s.append('\n');
        s.append("then ");
        s.append(" { ");
        s.append('\n');

        stateTure.prettyPrint(s);
        s.append('\n');
        s.append(" } ");
        s.append('\n');
        s.append(" else ");
        s.append(" { ");
        s.append('\n');

        stateFalse.prettyPrint(s);
        s.append('\n');
        s.append(" } ");

    }
}
