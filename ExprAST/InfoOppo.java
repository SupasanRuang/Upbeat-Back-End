package ExprAST;

import BackEnd.GameDataStorage;

public class InfoOppo implements Expr {

    public InfoOppo() {

    }
    @Override
    public long eval(
            GameDataStorage gameData) {
        return gameData.opponent();
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(" opponent ");


    }
}
