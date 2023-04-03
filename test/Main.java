package test;

import BackEnd.*;
import ErrorAndException.LexicalError;
import ErrorAndException.NonePlanError;
import ErrorAndException.SyntaxError;
import Parse.Parser;
import Parse.PlanParser;
import Parse.PlanTokenizer;
import Parse.Tokenizer;
import StatementAST.Statement;

public class Main {
    public static void main(String[] args) {System.out.println("Hello world!");

        Region land=new Region(10000,5,1,2);
        land.setDeposit(5000);
        System.out.println(land.Int(2));
        System.out.println(land.Int(5));
        System.out.println(land.Int(10));
        land.updateDeposit(2);
        System.out.println(land.giveDeposit(""));
        Player p=new Player("55",1000,5,5);
        System.out.println(p.toString());
        ConfigurationValue confi= new ConfigurationValue(3,4,5,0,2000,1000,30,0,5,10000,5);
        GameDataStorage game =new GameDataStorage(2,confi);
        System.out.println(game.distance(new Address(1,1),new Address(2,3)));
        System.out.println(game.distance(new Address(1,1),new Address(2,4)));
        System.out.println(game.distance(new Address(2,4),new Address(2,4)));
        System.out.println(game.distance(new Address(2,4),new Address(1,1)));
        System.out.println(game.distance(new Address(3,1),new Address(1,4)));
        game.addPlayer("p1");
        game.addPlayer("p2");
        game.startTurn();



        String code =" t = t + 1  # keeping track of the turn number\n" +
                "m = 0  # number of random moves\n" +
                "while (deposit) { # still our region\n" +
                "  if (deposit - 100)\n" +
                "  then collect (deposit / 4)  # collect 1/4 of available deposit\n" +
                "  else if (budget - 25) then invest 25\n" +
                "  else {}\n" +
                "  if (budget - 100) then {} else done  # too poor to do anything else\n" +
                "  opponentLoc = opponent\n" +
                "  if (opponentLoc / 10 - 1)\n" +
                "  then  # opponent afar\n" +
                "    if (opponentLoc % 10 - 5) then move downleft\n" +
                "    else if (opponentLoc % 10 - 4) then move down\n" +
                "    else if (opponentLoc % 10 - 3) then move downright\n" +
                "    else if (opponentLoc % 10 - 2) then move upleft\n" +
                "    else if (opponentLoc % 10 - 1) then move upright\n" +
                "    else move up\n" +
                "  else if (opponentLoc)\n" +
                "  then  # opponent adjacent to city crew\n" +
                "    if (opponentLoc % 10 - 5) then {\n" +
                "      cost = 10 ^ (nearby upleft % 100 + 1)\n" +
                "      if (budget - cost) then shoot upleft cost else {}\n" +
                "    }\n" +
                "    else if (opponentLoc % 10 - 4) then {\n" +
                "      cost = 10 ^ (nearby downleft % 100 + 1)\n" +
                "      if (budget - cost) then shoot downleft cost else {}\n" +
                "    }\n" +
                "    else if (opponentLoc % 10 - 3) then {\n" +
                "      cost = 10 ^ (nearby down % 100 + 1)\n" +
                "      if (budget - cost) then shoot down cost else {}\n" +
                "    }\n" +
                "    else if (opponentLoc % 10 - 2) then {\n" +
                "      cost = 10 ^ (nearby downright % 100 + 1)\n" +
                "      if (budget - cost) then shoot downright cost else {}\n" +
                "    }\n" +
                "    else if (opponentLoc % 10 - 1) then {\n" +
                "      cost = 10 ^ (nearby upright % 100 + 1)\n" +
                "      if (budget - cost) then shoot upright cost else {}\n" +
                "    }\n" +
                "    else {\n" +
                "      cost = 10 ^ (nearby up % 100 + 1)\n" +
                "      if (budget - cost) then shoot up cost else {}\n" +
                "    }\n" +
                "  else {  # no visible opponent; move in a random direction\n" +
                "    dir = random % 6\n" +
                "    if (dir - 4) then move upleft\n" +
                "    else if (dir - 3) then move downleft\n" +
                "    else if (dir - 2) then move down\n" +
                "    else if (dir - 1) then move downright\n" +
                "    else if (dir) then move upright\n" +
                "    else move up\n" +
                "    m = m + 1\n" +
                "  }\n" +
                "}  # end while\n" +
                "\n\n\n\n\n # end while\n" +
                "\n\n\n\n\n # end while\n" +
                "\n\n\n\n\n # end        while\n" +
                "\n\n\n\n\n # end while\n" +
                "                   " +
                " # end while\n" +
                "# city crew on a region belonging to nobody, so claim it\n" +
                "if (budget - 1) then invest 1 else {}\n"
                +" move up\n";
        //+"budget =5 \n";
        //code ="move up";


        Statement listStatement = null;
        try {
            Tokenizer tkz=new PlanTokenizer(code);
            Parser plan =new PlanParser(tkz);
            listStatement= plan.parse();
            StringBuilder s =new StringBuilder();
            listStatement.prettyPrint(s);
            System.out.println(s.toString());
        } catch (SyntaxError | LexicalError | ArithmeticException | NonePlanError e ) {
            System.out.println(e);
        }
        if(listStatement!=null)
            System.out.println(listStatement.eval(game));
        else System.out.println("null");


    }
}