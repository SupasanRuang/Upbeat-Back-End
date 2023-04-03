package StatementAST;

import BackEnd.GameDataStorage;

import java.util.ArrayList;

public class ListStatement implements Statement {
    private ArrayList<Statement> List ;

    public ListStatement(ArrayList<Statement> plan) {
        List=new ArrayList<>(plan);
    }


    @Override
    public boolean eval(GameDataStorage gameData) {
        boolean doneSignal =false;
        for(int i = 0; i<List.size()&&!doneSignal; i++)
        {
            doneSignal =List.get(i).eval(gameData);
        }

        return doneSignal;
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(" { ");
        s.append('\n');
        for(int i=0;i<List.size();i++)
        {

            List.get(i).prettyPrint(s);
            s.append('\n');
        }
        s.append(" } ");

    }
}
