package StatementAST;

import BackEnd.GameDataStorage;
import BackEnd.Player;

public class ActionStatement implements Statement {

    private String action;

    public ActionStatement(String action){
        this.action = action;

    }


    @Override
    public boolean eval(GameDataStorage gameData) {

        if(action.equals("relocate"))
        {
            Player player = gameData.nowPlayer;
            gameData.relocate(player.getCityCrew());
        }

        // Always true because done
        return true;
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(" ");
        s.append(action);
        s.append(" ");

    }
}
