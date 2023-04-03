package ExprAST;

import BackEnd.GameDataStorage;

public class LongLit implements Expr {
    private Long val;
    public LongLit(Long val) {
        this.val = val;
    }
    @Override
    public long eval(
            GameDataStorage gameData) {
        return val;
    }
    @Override
    public void prettyPrint(StringBuilder s) {

        s.append(val);
    }

}
