package StatementAST;

import BackEnd.GameDataStorage;
import ExprAST.Expr;

public class WhileStatement implements Statement {

    private Statement statement;
    private Expr expression;
    public WhileStatement(Expr expression, Statement statement) {
        this.expression = expression;
        this.statement= statement;
    }

    @Override
    public boolean eval(GameDataStorage gameData) {
        boolean doneSignal =false;
        for (int counter = 0; counter < 10000 && this.expression.eval(gameData) > 0&& !doneSignal; counter++) {
            doneSignal=statement.eval(gameData);

        }
        return  doneSignal;
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append("while ( ");
        expression.prettyPrint(s);
        s.append(" ) "+'\n' +"{ ");
        statement.prettyPrint(s);
        s.append(" } ");


    }
}
