package StatementAST;

import BackEnd.GameDataStorage;

public interface Statement {

    boolean eval(GameDataStorage gameData);

    void prettyPrint(StringBuilder s);


}
