package Parse;

import ErrorAndException.LexicalError;

public interface Tokenizer {
    boolean hasNextToken();

    String peek();

    String consume() throws LexicalError;

    boolean peek(String s);




    void comsume(String s) throws LexicalError;
}
