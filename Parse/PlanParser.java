package Parse;


import ErrorAndException.LexicalError;
import ErrorAndException.NonePlanError;
import ErrorAndException.SyntaxError;
import ExprAST.Expr;
import ExprAST.ExprFactory;
import StatementAST.Statement;
import StatementAST.StatementFactory;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PlanParser implements Parser {

    private final Tokenizer tkz;


    public PlanParser(Tokenizer tkz) {
        this.tkz = tkz;
    }

    // Plan → Statement
    @Override
    public Statement parse() throws SyntaxError, NonePlanError, LexicalError, ArithmeticException , NoSuchElementException {
        // begin parsing at start symbol
        // reject if there is remaining token
        if (!tkz.hasNextToken()) {
            throw new NonePlanError("leftover token");
        }
        ArrayList<Statement> list = new ArrayList<>();
        Statement nextStatement;

        while (tkz.hasNextToken()) {
            nextStatement = parseStatement();
            if (nextStatement != null) {
                list.add(nextStatement);
            } else break;

        }


        return StatementFactory.instanceList(list);


    }


    // Statement → Command | BlockStatement | IfStatement | WhileStatement
    private Statement parseStatement() throws LexicalError, SyntaxError, ArithmeticException, NoSuchElementException {
        Statement s;
        System.out.println("Statement");
        if (tkz.peek("while")) {
            s = parseWhileStatement();
        } else if (tkz.peek("if")) {
            s = parseIfStatement();
        } else if (tkz.peek("{")) {
            s = parseBlockStatement();
        } else
            s = parseCommand();

        return s;
    }

    // Command → AssignmentStatement | ActionCommand
    private Statement parseCommand() throws LexicalError, SyntaxError, ArithmeticException, NoSuchElementException {
        Statement c;
        System.out.println("Command");
        if (isType.isActionCommand(tkz.peek())) {
            c = parseActionCommand();
        } else if (isType.isIdentify(tkz.peek())) {

            c = parseAssignStatement();
        } else throw new SyntaxError("Wrong input : " + tkz.peek());


        return c;
    }

    // AssignmentStatement → <identifier> = Expression
    private Statement parseAssignStatement() throws SyntaxError, LexicalError, ArithmeticException , NoSuchElementException{
        Statement ass = null;
        System.out.println("AssignStatement");
        if (isType.isIdentify(tkz.peek())) {
            if (isType.isSpecialVariables(tkz.peek())) {
                throw new ArithmeticException(tkz.peek() + " is Special Variables ,you can't Assign it");
            }
            String assign = tkz.consume();
            tkz.comsume("=");
            ass = StatementFactory.instanceAssignment(assign, parseExpression());
        }

        return ass;
    }

    // ActionCommand → done | relocate | MoveCommand | RegionCommand | AttackCommand
    private Statement parseActionCommand() throws LexicalError, SyntaxError, ArithmeticException , NoSuchElementException{
        System.out.println("ActionCommand");
        Statement a = null;
        try {
            if (isType.isAttackCommand(tkz.peek())) {
                a = parseAttackCommand();
            } else if (isType.isMoveCommand(tkz.peek())) {
                a = parseMoveCommand();
            } else if (isType.isRegionCommand(tkz.peek())) {
                a = parseRegionCommand();
            } else if (isType.isCharacter(tkz.peek())) {
                if (tkz.peek("done") || tkz.peek("relocate")) {
                    a = StatementFactory.instanceAction(tkz.consume()); /*new "Something" tkz.consume();*/
                }
            }

        } catch (SyntaxError | ArithmeticException e ) {
            throw new RuntimeException(e);
        }

        return a;
    }

    // MoveCommand → move Direction
    private Statement parseMoveCommand() throws LexicalError, SyntaxError, RuntimeException,NoSuchElementException {
        System.out.println("MoveCommand");
        Statement m = null;
        try {
            if (isType.isCharacter(tkz.peek())) {
                if (tkz.peek("move")) {
                    try {
                        tkz.comsume("move");
                    } catch (LexicalError e) {
                        throw new RuntimeException(e);
                    }
                    m = StatementFactory.instanceMove(parseDirection()); /*new "Something" tkz.consume(); parseDirection()*/

                }
            }
        } catch (RuntimeException | LexicalError | SyntaxError e)
        {
            throw new RuntimeException(e);
        }


        return m;
    }

    // Direction → up | down | upleft | upright | downleft | downright
    private Expr parseDirection() throws LexicalError, SyntaxError, RuntimeException , NoSuchElementException{
        System.out.println("Direction");
        Expr d;
        try {
            if (isType.isDirection(tkz.peek())) {
                d = ExprFactory.instanceDirection(tkz.consume()); /*new "Something" tkz.consume(); */
            } else throw new SyntaxError(tkz.peek() + " is wrong at Direction");
        } catch (LexicalError | NoSuchElementException | SyntaxError e) {
            throw new RuntimeException(e);
        }

        return d;
    }

    // RegionCommand → invest Expression | collect Expression
    private Statement parseRegionCommand() throws LexicalError, SyntaxError, ArithmeticException , NoSuchElementException{
        Statement r = null;
        System.out.println("RegionCommand");
        try {


        if (isType.isCharacter(tkz.peek())) {
            if (tkz.peek("invest") || tkz.peek("collect")) {

                r = StatementFactory.instanceRegion(tkz.consume(), parseExpression()); /*new "Something" tkz.consume();with parseExpression()*/
            }

        } else throw new SyntaxError("Direction is wrong");
        } catch (LexicalError | SyntaxError | ArithmeticException | NoSuchElementException e) {
            throw new RuntimeException(e);
        }

        return r;
    }

    // AttackCommand → shoot Direction Expression
    private Statement parseAttackCommand() throws LexicalError, SyntaxError, ArithmeticException , NoSuchElementException{
        Statement att = null;
        System.out.println("AttackStatement");
        try{
        if (isType.isAttackCommand(tkz.peek())) {
            try {
                tkz.comsume("shoot");
            } catch (LexicalError lexicalError) {
                throw new RuntimeException(lexicalError);
            }

            att = StatementFactory.instanceAttack(parseDirection(), parseExpression());
        }} catch (RuntimeException | LexicalError | SyntaxError e) {
            throw new RuntimeException(e);
        }

        return att;
    }

    // BlockStatement → { Statement* }
    private Statement parseBlockStatement() throws LexicalError, SyntaxError, ArithmeticException , NoSuchElementException{
        Statement block;
        System.out.println("BlockStatement");
        ArrayList<Statement> list = new ArrayList<>();
        try {


        if (tkz.peek("{")) {
            try {
                tkz.comsume("{");
            } catch (LexicalError lexicalError) {
                throw new RuntimeException(lexicalError);
            }
            Statement nextStatement;
            while (!tkz.peek("}")) {

                nextStatement = parseStatement();
                if (nextStatement != null) {
                    list.add(nextStatement);
                } else {
                    throw new SyntaxError("parse null at " + " parseBlockStatement()");
                }
            }
            try {
                tkz.comsume("}");
            } catch (LexicalError lexicalError) {
                throw new RuntimeException(lexicalError);
            }
        }
        } catch (RuntimeException | LexicalError | SyntaxError e) {
            throw new RuntimeException(e);
        }
        block = StatementFactory.instanceList(list);
        return block;

    }

    // IfStatement → if ( Expression ) then Statement else Statement
    private Statement parseIfStatement() throws SyntaxError, ArithmeticException , NoSuchElementException{
        Statement condition = null;
        System.out.println("ifStatement");
        try {
        if (tkz.peek("if")) {
            tkz.comsume("if");
            tkz.comsume("(");
            Expr e = parseExpression();
            tkz.comsume(")");
            tkz.comsume("then");
            Statement t = parseStatement();
            tkz.comsume("else");
            Statement f = parseStatement();/*new "Something" tkz.consume();with parseExpression() and Two parseStatement() */
            condition = StatementFactory.instanceIf(e, t, f);
        }} catch (LexicalError | ArithmeticException | NoSuchElementException e) {
            throw new RuntimeException(e);
        } catch (SyntaxError e) {
            throw new SyntaxError (e+"<= IfStatement");
        }


        return condition;
    }

    // WhileStatement → while ( Expression ) Statement
    private Statement parseWhileStatement() throws LexicalError, SyntaxError, ArithmeticException, NoSuchElementException {
        System.out.println("WhileStatement");
        Statement w = null;
        try {


            if (tkz.peek("while")) {
                try {
                    tkz.comsume("while");
                } catch (LexicalError lexicalError) {
                    throw new RuntimeException(lexicalError);
                }
                try {
                    tkz.comsume("(");
                } catch (LexicalError lexicalError) {
                    throw new RuntimeException(lexicalError);
                }
                Expr e = parseExpression();
                try {
                    tkz.comsume(")");
                } catch (LexicalError lexicalError) {
                    throw new RuntimeException(lexicalError);
                }
                Statement s = parseStatement();/*new "Something" tkz.consume();with parseExpression()  and parseStatement()*/
                w = StatementFactory.instanceWhile(e, s);
            }
        } catch (RuntimeException | LexicalError | SyntaxError e) {
            throw new RuntimeException(e);
        }
        return w;
    }

    //Expression → Expression + Term | Expression - Term | Term
    private Expr parseExpression() throws LexicalError, SyntaxError, ArithmeticException , NoSuchElementException{
        Expr e = parseTerm();
        System.out.println("Expression");
        try {
            while (tkz.peek("+") || tkz.peek("-")) {
                if (tkz.peek("+")) {
                    tkz.consume();
                    e = ExprFactory.instanceBinaryArith(e, "+", parseTerm());
                } else if (tkz.peek("-")) {
                    tkz.consume();
                    e = ExprFactory.instanceBinaryArith(e, "-", parseTerm());
                }
            }
        } catch (LexicalError | SyntaxError | ArithmeticException | NoSuchElementException ex) {
            throw new RuntimeException(ex);
        }

        return e;
    }

    // Term → Term * Factor | Term / Factor | Term % Factor | Factor
    private Expr parseTerm() throws LexicalError, SyntaxError, ArithmeticException , NoSuchElementException{
        Expr t = parseFactor();
        System.out.println("Term");
        try {


        while (tkz.peek("*") || tkz.peek("/") || tkz.peek("%")) {
            if (tkz.peek("*")) {
                tkz.consume();
                t = ExprFactory.instanceBinaryArith(t, "*", parseFactor());
            } else if (tkz.peek("/")) {
                tkz.consume();
                t = ExprFactory.instanceBinaryArith(t, "/", parseFactor());
            } else if (tkz.peek("%")) {
                tkz.consume();
                t = ExprFactory.instanceBinaryArith(t, "%", parseFactor());
            }
        }

        } catch (LexicalError | SyntaxError | ArithmeticException | NoSuchElementException e) {
            throw new RuntimeException(e);
        }
        return t;
    }

    // Factor → Power ^ Factor | Power
    private Expr parseFactor() throws LexicalError, SyntaxError, ArithmeticException , NoSuchElementException{
        Expr f = parsePower();
        System.out.println("Factor");
        try {
            while (tkz.peek("^")) {
                if (tkz.peek("^")) {
                    tkz.consume();
                    f = ExprFactory.instanceBinaryArith(f, "^", parsePower());
                }
            }
        } catch (LexicalError | SyntaxError | ArithmeticException | NoSuchElementException e) {
            throw new RuntimeException(e);
        }
        return f;
    }

    // Power → <number> | <identifier> | ( Expression ) | InfoExpression
    private Expr parsePower() throws SyntaxError, ArithmeticException , NoSuchElementException{
        Expr power;
        System.out.println("Power");
        try

        {


        if (isType.isNumeric(tkz.peek())) {
            power = ExprFactory.instanceLongLit(Long.parseLong(tkz.consume()));
        } else if (isType.isInfoExpression(tkz.peek())) {
            System.out.println("InfoExpression");
            power = parseInfoExpression();
        } else if (isType.isIdentify(tkz.peek())) {
            return ExprFactory.instanceIdentifier(tkz.consume());
        } else {
            tkz.comsume("(");
            power = parseExpression();
            tkz.comsume(")");

        }
    } catch (NumberFormatException | LexicalError | SyntaxError | ArithmeticException | NoSuchElementException e) {
            throw new RuntimeException(e);
        }
        return power;
    }

    // InfoExpression → opponent | nearby Direction
    private Expr parseInfoExpression() throws LexicalError, SyntaxError, ArithmeticException , NoSuchElementException{
        Expr i;
        System.out.println("InfoExpression");

        try {


        if (tkz.peek("opponent")) {

            tkz.comsume("opponent");
            i = ExprFactory.instanceInfoOppo();

        } else {

            tkz.comsume("nearby");
            Expr direction = parseDirection();
            i = ExprFactory.instanceInfoNearby(direction);

        }
        } catch (LexicalError | SyntaxError | NoSuchElementException | ArithmeticException e) {
            throw new RuntimeException(e);
        }

        return i;
    }


}



