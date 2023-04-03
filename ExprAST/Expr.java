package ExprAST;

import BackEnd.GameDataStorage;

public interface Expr {
    long eval(GameDataStorage gameData);

    void prettyPrint(StringBuilder s);
}
