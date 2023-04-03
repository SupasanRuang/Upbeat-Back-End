package ExprAST;


import BackEnd.GameDataStorage;
import BackEnd.Player;
import ErrorAndException.EvalError;
import Parse.isType;

public class Identifier implements Expr {
    private final String name;
    public Identifier(String name) {
        this.name = name;
    }
    @Override
    /*เติมตัว check Special variables*/
    public long eval(GameDataStorage gameData) {
        Player player=gameData.nowPlayer;
        if(isType.isSpecialVariables(this.name))
        {
            //"rows","cols","currow","curcol","budget","deposit","int","maxdeposit","random",
            return  switch (this.name){
                case "rows" -> gameData.getRows();
                case "cols" -> gameData.getCol();
                case "currow" -> gameData.getCurrow();
                case "curcol" -> gameData.getCurcol();
                case "budget" -> gameData.getBudget();
                case "deposit" -> gameData.getRegionDeposit(player.getCityCrew());
                case "int" -> gameData.getIntRate(player.getCityCrew());
                case "maxdeposit" -> gameData.getMaxdeposit();
                case "random" -> gameData.useRandom();
                default -> throw new EvalError("Wrong input" + this.name);

            };


        }
        if (player.getPersonalValue().containsKey(name))
            return player.getPersonalValue().get(name);

        else{
            player.getPersonalValue().put(this.name, 0L);
            return player.getPersonalValue().get(name);
        }

    }



    @Override
    public void prettyPrint(
            StringBuilder s) {
        s.append(" ");
        s.append(name);
        s.append(" ");
    }

}
