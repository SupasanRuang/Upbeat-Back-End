package StatementAST;

import BackEnd.GameDataStorage;
import ErrorAndException.EvalError;
import ExprAST.Expr;

public class RegionStatement implements Statement {
    private Expr expression;
    private String act;

    public RegionStatement(String act, Expr expression) {
        this.expression = expression;
        this.act=act;
    }


    @Override
    public boolean eval(GameDataStorage gameData) {
        boolean selfLose =false;
                switch (act) {
            case "invest" -> gameData.invest(gameData.nowPlayer.getCityCrew(),this.expression.eval(gameData));
            case "collect" -> {
                selfLose=gameData.collect(gameData.nowPlayer.getCityCrew(), this.expression.eval(gameData));
            }
            default -> throw new EvalError("Wrong input" + this.act);

//            default -> throw new EvalError("Wrong input" + act);
        };
        //selfLose=false;
        return selfLose;
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(" ");
        s.append(act);
        s.append(" ");
        expression.prettyPrint(s);
        s.append(" ");
    }
}
