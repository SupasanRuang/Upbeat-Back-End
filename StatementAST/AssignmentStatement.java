package StatementAST;

import BackEnd.GameDataStorage;
import ExprAST.Expr;
import Parse.isType;

public class AssignmentStatement implements Statement {

    private String assign;
    private Expr Expression;

    public AssignmentStatement(String assign, Expr Expression) {
        this.assign = assign;

        this.Expression = Expression;

    }


    @Override
    /*เติมตัว check Special variables*/
    public boolean eval(GameDataStorage gameData) {
        long val = this.Expression.eval(gameData);
        gameData.nowPlayer.addPersonalValue(this.assign,val);
        return false;

    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(" ");
        s.append(assign);
        s.append(" = ");
        Expression.prettyPrint(s);
        s.append(" ");

    }


    }


