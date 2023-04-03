package Parse;

public class isType {
    public static boolean isCharacter(String s) {
        if (s == null) {
            return false;
        }
        boolean result = true;
        int i = 0;
        while (i < s.length() && result) {
            result = Character.isAlphabetic(s.charAt(i));
            i++;
        }
        return result;

    }

    public static boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        boolean result = true;
        int i = 0;
        while (i < s.length() && result) {
            result = Character.isDigit(s.charAt(i));
            i++;
        }
        return result;
    }

    public static boolean isIdentify(String peek) {
        if (peek == null) {
            return false;
        }
        if (!isCharacter(peek)) {
            return false;
        }

        /* up,down,upleft,,,,,,,,,,,,,,,,,,,,,,,,,,,,,  */
        /*collect, done, down, downleft, downright, else, if, invest, move, nearby, opponent, relocate, shoot, then, up, upleft, upright, while
         */
        return !(peek.equals("up") || peek.equals("down") || peek.equals("upleft") || peek.equals("upright") || peek.equals("downleft") || peek.equals("downright")
                || peek.equals("done") || peek.equals("relocate") || peek.equals("move") || peek.equals("invest") || peek.equals("collect") || peek.equals("shoot")
                || peek.equals("if") || peek.equals("then") || peek.equals("else") || peek.equals("while") || peek.equals("opponent") || peek.equals("nearby"));

    }

    public static boolean isDirection(String peek) {
        if (peek == null) {
            return false;
        }
        if (!isCharacter(peek)) {
            return false;
        }

        /*up | down | upleft | upright | downleft | downright*/
        return peek.equals("up") || peek.equals("down") || peek.equals("upleft") || peek.equals("upright") || peek.equals("downleft") || peek.equals("downright");

    }


    public static boolean isActionCommand(String peek) {
        if (peek == null) {
            return false;
        }
        if (!isCharacter(peek)) {
            return false;
        }

        return peek.equals("done") || peek.equals("relocate") || isMoveCommand(peek) || isRegionCommand(peek) || isAttackCommand(peek);

    }

    public static boolean isMoveCommand(String peek) {
        if (peek == null) {
            return false;
        }
        if (!isCharacter(peek)) {
            return false;
        }

        /*if then else*/
        return peek.equals("move");

    }

    public static boolean isRegionCommand(String peek) {
        if (peek == null) {
            return false;
        }
        if (!isCharacter(peek)) {
            return false;
        }

        /*if then else*/
        return peek.equals("invest") || peek.equals("collect");

    }

    public static boolean isAttackCommand(String peek) {
        if (peek == null) {
            return false;
        }
        if (!isCharacter(peek)) {
            return false;
        }

        /*if then else*/
        return peek.equals("shoot");
    }

    public static boolean isInfoExpression(String s) {
        if (s == null) {
            return false;
        }
        if (!isCharacter(s)) {
            return false;
        }
        /*if then else*/
        return s.equals("opponent") || s.equals("nearby");

    }
    //"rows","cols","currow","curcol","budget","deposit","int","maxdeposit","random",
    public static boolean isSpecialVariables(String peek) {
        if (peek == null) {
            return false;
        }
        if (!isCharacter(peek)) {
            return false;
        }
        return peek.equals("rows") || peek.equals("cols") || peek.equals("curcol") || peek.equals("currow") || peek.equals("budget") || peek.equals("deposit") || peek.equals("int") || peek.equals("maxdeposit") || peek.equals("random");
    }
}
