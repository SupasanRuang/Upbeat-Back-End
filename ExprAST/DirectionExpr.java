package ExprAST;

import BackEnd.GameDataStorage;
import ErrorAndException.EvalError;

public class DirectionExpr implements Expr {

    private final String direction;

    public DirectionExpr(String direction){
        this.direction = direction;
    }

    //private enum Direction {up , upright , downright , down , downleft ,upleft};

    @Override
    public long eval(GameDataStorage gameData) {
        return switch (direction) {
            case "up" -> 1;
            case "upright" -> 2;
            case "downright" -> 3;
            case "down" -> 4;
            case "downleft" -> 5;
            case "upleft" -> 6;
            default -> throw new EvalError("Wrong input" + direction);
        };

    }

    @Override
    public void prettyPrint(StringBuilder s) {

        s.append(direction);

    }
}
