import postfix.MalformedInfixExpressionException;
import postfix.MalformedPostfixExpressionException;
import postfix.Postfix;

public class CalcEnginePostfix {
    private StringBuilder sb;
    private Postfix pf;

    public CalcEnginePostfix() {
        sb = new StringBuilder();
        pf = new Postfix();
    }

    void buttonPressed(String button) {
        sb.append(button);
    }

    void equals () {
        String pfx = null;
        try {
            pfx = pf.infixToPostfix(getDisplay());
        } catch (MalformedInfixExpressionException e) {
            clear();
            sb.append("error");

        }
        clear();
        try {
            sb.append(pf.evaluate(pfx));
        } catch (MalformedPostfixExpressionException e) {
            clear();
            sb.append("error");
        }
    }

    void clear () {
        sb.delete(0, sb.length());
    }

    String getDisplay () {
        return sb.toString();
    }


}
