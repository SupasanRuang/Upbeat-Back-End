package StatementAST;

import ExprAST.Expr;

import java.util.ArrayList;

public class StatementFactory {
    private static StatementFactory instance;

    private StatementFactory() {
    }

    private Statement newListStatement(ArrayList<Statement> List) {
        return new ListStatement(List);
    }


    public static Statement instanceList(ArrayList<Statement> List) {
        if (instance == null) {
            instance = new StatementFactory();
        }
        return instance.newListStatement(List);
    }

    private Statement newWhileStatement(Expr expression, Statement statement) {
        return new WhileStatement(expression, statement);
    }

    public static Statement instanceWhile(Expr expression, Statement statement) {
        if (instance == null) {
            instance = new StatementFactory();
        }
        return instance.newWhileStatement(expression, statement);
    }

    private Statement newRegionStatement(String act, Expr expression) {
        return new RegionStatement(act, expression);
    }


    public static Statement instanceRegion(String act, Expr expression) {
        if (instance == null) {
            instance = new StatementFactory();
        }
        return instance.newRegionStatement(act, expression);
    }

    private Statement newMoveStatement(Expr direction) {
        return new MoveStatement(direction);
    }

    public static Statement instanceMove(Expr direction) {
        if (instance == null) {
            instance = new StatementFactory();
        }
        return instance.newMoveStatement(direction);
    }


    private Statement newIfStatement(Expr expression, Statement stateTure, Statement stateFalse) {
        return new IfStatement(expression, stateTure, stateFalse);
    }

    public static Statement instanceIf(Expr expression, Statement stateTure, Statement stateFalse) {
        if (instance == null) {
            instance = new StatementFactory();
        }
        return instance.newIfStatement(expression, stateTure, stateFalse);
    }

    private Statement newActionStatement(String action) {
        return new ActionStatement(action);

    }

    public static Statement instanceAction(String action) {
        if (instance == null) {
            instance = new StatementFactory();
        }
        return instance.newActionStatement(action);
    }

    private Statement newAssignmentStatement(String assign,  Expr Expression) {
        return new AssignmentStatement(assign, Expression);
    }

    public static Statement instanceAssignment(String assign, Expr Expression) {
        if (instance == null) {
            instance = new StatementFactory();
        }
        return instance.newAssignmentStatement(assign, Expression);
    }

    private Statement newAttackStatement(Expr direction, Expr expression) {
        return new AttackStatement(direction, expression);
    }

    public static Statement instanceAttack(Expr direction, Expr expression) {
        if (instance == null) {
            instance = new StatementFactory();
        }
        return instance.newAttackStatement(direction, expression);
    }
}

