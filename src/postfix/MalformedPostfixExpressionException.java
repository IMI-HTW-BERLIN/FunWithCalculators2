package postfix;

public class MalformedPostfixExpressionException extends Exception {
    public MalformedPostfixExpressionException() {

    }

    public MalformedPostfixExpressionException(String message) {
        super(message);
    }
}
