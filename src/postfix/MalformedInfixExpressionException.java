package postfix;

public class MalformedInfixExpressionException extends Exception {
    public MalformedInfixExpressionException() {

    }

    public MalformedInfixExpressionException(String message) {
        super(message);
    }
}
