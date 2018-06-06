package postfixOLD;

public class Postfix {


    public static String evaluatePfx(String pfx) throws StackUnderflow, PostfixException{
        checkPostfix(pfx);
        Stack<String> stack = new Stack<>();
        while (pfx.length() > 0) {
            String token = pfx.substring(0,1);
            if (token.matches("[0-9]")) {
                stack.push(token);
            }
            else if (token.matches("[-+*/^]")){
                String rhs = stack.top();
                stack.pop();
                String lhs = stack.top();
                stack.pop();
                stack.push((calculate(lhs, pfx.substring(0,1), rhs)));
            }
            pfx = pfx.substring(1);
        }
        return stack.top();
    }

    public static String infixToPostfix (String ifx) throws StackUnderflow, InfixException{
        checkInfix(ifx);
        Stack<String> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        while(ifx.length() > 0) {
            String token = ifx.substring(0,1);
            if (!token.matches("[-+*/^()]")) result.append(token);
            if (token.equals("(")) stack.push(token);
            if (token.equals(")")) {
                while (!stack.top().equals("(")) {
                    result.append(stack.top());
                    stack.pop();
                }
                stack.pop();
            }
            if (token.matches("[-+*/^]")) {
                while(!stack.isEmpty() && !((precedence(stack.top()) < precedence(token)) ||
                        ((precedence(token) == 3 && precedence(stack.top()) == precedence(token))))) {
                    result.append(stack.top());
                    stack.pop();
                }
                stack.push(token);
            }
            ifx = ifx.substring(1);
        }
        while(!stack.isEmpty()) {
            result.append(stack.top());
            stack.pop();
        }
        return result.toString();
    }

    private static int precedence (String operator) {
        if(operator.equals("^")) return 3;
        if(operator.matches("[+-]")) return 1;
        if(operator.matches("[*/]")) return 2;
        return 0;
    }

    private static String calculate(String lh, String operator, String rh){
        int result = 0;
        int lhs = Integer.parseInt(lh);
        int rhs = Integer.parseInt(rh);
        switch(operator) {
            case "+":
                result = lhs + rhs;
                break;
            case "-":
                result = lhs - rhs;
                break;
            case "*":
                result = lhs * rhs;
                break;
            case "/":
                result = lhs / rhs;
                break;
            case "^":
                result = (int)Math.pow(lhs, rhs);
                break;
            default:
                break;
        }
        return ""+result;
    }

    public static void checkInfix(String ifx) throws StackUnderflow, InfixException{
        Stack<String> p = new Stack<>();
        Stack<String> op = new Stack<>();
        Stack<String> num = new Stack<>();
        if (ifx.substring(0,1).matches("[-+*/^]") ||
            ifx.substring(ifx.length()-1,ifx.length()).matches("[-+*/^]")) throw new InfixException();
        while (ifx.length() > 0){
            String token = ifx.substring(0,1);
            if (token.equals("(")) p.push(token);
            if (token.equals(")") && num.isEmpty())
                throw new InfixException();
            if (token.equals(")") && !p.isEmpty()) p.pop();
            else if (token.equals(")")) throw new InfixException();

            if (token.matches("[-+*/^]") && op.isEmpty()) op.push(token);
            else if (token.matches("[-+*/^]")) throw new InfixException();
            else if (!op.isEmpty()) op.pop();

            if (token.matches("[0-9]") && num.isEmpty()) num.push(token);
            else if (token.matches("[0-9]")) throw new InfixException();
            else if (!op.isEmpty()) num.pop();


            ifx = ifx.substring(1);
        }
        if (!p.isEmpty()) throw new InfixException();
    }

    public static void checkPostfix(String pfx) throws PostfixException, StackUnderflow{
        Stack<String> operator = new Stack<>();
        Stack<String> operand = new Stack<>();
        if (pfx.length() <= 1 || pfx.matches("[-+*/^()]+")) throw new PostfixException();
        if (pfx.substring(0,1).matches("[-+*/^]")) throw new PostfixException();
        if (pfx.substring(pfx.length()-1,pfx.length()).matches("[0-9]")) throw new PostfixException();
        while (pfx.length() > 0) {
            String token = pfx.substring(0,1);
            if (token.matches("[0-9]")) operand.push(token);
            else if(token.matches("[-+*/^]")) operator.push(token);
            pfx = pfx.substring(1);
        }
        while (!operator.isEmpty()) {
            if (operand.isEmpty()) throw new PostfixException();
            else operand.pop();
            if (operand.isEmpty()) throw new PostfixException();
            operator.pop();
        }
        if (!operand.isEmpty()) operand.pop();
        if (!operand.isEmpty()) throw new PostfixException();
    }
}
