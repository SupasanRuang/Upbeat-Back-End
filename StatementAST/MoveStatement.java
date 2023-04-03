package StatementAST;

import BackEnd.GameDataStorage;
import BackEnd.Player;
import ExprAST.Expr;

public class MoveStatement implements Statement {

    private final Expr direction;

    public MoveStatement(Expr direction) {
        this.direction=direction;
    }

    @Override
    public boolean eval(GameDataStorage gameData) {
        gameData.move(this.direction.eval(gameData));
        return false;/*donesignal*/
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append("move ");
        direction.prettyPrint(s);
        s.append(" ");

    }
}
