package ExprAST;

public class ExprFactory {
    private static ExprFactory instance;

    private ExprFactory() {

    }

    private Expr newBinaryArithExpr(Expr left, String op, Expr right) {
        return new BinaryArithExpr(left, op, right);
    }

    public static Expr instanceBinaryArith(Expr left, String op, Expr right) {
        if (instance == null) {
            instance = new ExprFactory();

        }
        return instance.newBinaryArithExpr(left, op, right);
    }

    private Expr newDirectionExpr(String direction) {
        return new DirectionExpr(direction);
    }

    public static Expr instanceDirection(String direction) {
        if (instance == null) {
            instance = new ExprFactory();

        }
        return instance.newDirectionExpr(direction);
    }

    private Expr newIdentifier(String name) {
        return new Identifier(name);
    }

    public static Expr instanceIdentifier(String name) {
        if (instance == null) {
            instance = new ExprFactory();

        }
        return instance.newIdentifier(name);
    }

    private Expr newInfoNearby(Expr Direction) {
        return new InfoNearby(Direction);
    }

    public static Expr instanceInfoNearby(Expr Direction) {
        if (instance == null) {
            instance = new ExprFactory();

        }
        return instance.newInfoNearby(Direction);
    }

    private Expr newInfoOppo() {
        return new InfoOppo();
    }

    public static Expr instanceInfoOppo() {
        if (instance == null) {
            instance = new ExprFactory();
        }
        return instance.newInfoOppo();
    }

    private Expr newLongLit(Long val) {
        return new LongLit(val);
    }

    public static Expr instanceLongLit(Long val) {
        if (instance == null) {
            instance = new ExprFactory();

        }
        return instance.newLongLit(val);
    }


}
