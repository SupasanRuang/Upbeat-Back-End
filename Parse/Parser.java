package Parse;

import ErrorAndException.LexicalError;
import ErrorAndException.NonePlanError;
import ErrorAndException.SyntaxError;
import StatementAST.Statement;

public interface Parser {
    Statement parse() throws SyntaxError, LexicalError,ArithmeticException, NonePlanError;
}

