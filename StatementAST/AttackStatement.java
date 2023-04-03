package StatementAST;

import BackEnd.Address;
import BackEnd.GameDataStorage;
import ExprAST.Expr;

public class AttackStatement implements Statement {
    private Expr direction,expression;

    public AttackStatement(Expr direction, Expr expression) {
        this.direction = direction;
        this.expression=expression;
    }

    @Override
    public boolean eval(GameDataStorage gameData) {
        Address address=gameData.directionAddress(gameData.nowPlayer.getCityCrew(),this.direction.eval(gameData));
        boolean selfLose =gameData.shoot(address,this.expression.eval(gameData));
        return selfLose;
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(" shoot ");
        direction.prettyPrint(s);
        s.append(" ");
        expression.prettyPrint(s);
        s.append(" ");

    }
}
