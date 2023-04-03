package ExprAST;

import BackEnd.GameDataStorage;

public class InfoNearby implements Expr {

    private final Expr Direction;

    public InfoNearby(Expr Direction) {
        this.Direction = Direction;
    }
    @Override
    public long eval(GameDataStorage gameData) {

        return gameData.nearby(this.Direction.eval(gameData));
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(" ");
        s.append("nearby");
        s.append(" ");
        Direction.prettyPrint(s);
        s.append(" ");


    }
}
