package postfix;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PostfixTest {
    Postfix pf;
    @Before
    public void setUp() throws Exception {
        pf = new Postfix();
    }

    @Test
    public void precedence() throws Exception{
        assertEquals("3.0", pf.evaluate(pf.infixToPostfix("1+1*2")));
        assertEquals("4.0", pf.evaluate(pf.infixToPostfix("(1+1)*2")));
        assertEquals("13.0", pf.evaluate(pf.infixToPostfix("2+1*2+1*2*1+2*1+1+2*2")));

        assertEquals("3.0", pf.evaluate(pf.infixToPostfix("2*1+1")));
        assertEquals("4.0", pf.evaluate(pf.infixToPostfix("2*(1+1)")));
        assertEquals("3.0", pf.evaluate(pf.infixToPostfix("(2*1)+1")));
        assertEquals("3.0", pf.evaluate(pf.infixToPostfix("1+(2*1)")));

        assertEquals("18.0", pf.evaluate(pf.infixToPostfix("2*(2*4+1)")));
        assertEquals("12.0", pf.evaluate(pf.infixToPostfix("(2*(2+1*4))")));
        assertEquals("16.0", pf.evaluate(pf.infixToPostfix("(2*2)*4")));
        assertEquals("16.0", pf.evaluate(pf.infixToPostfix("2*(2*4)")));

        assertEquals("20.0", pf.evaluate(pf.infixToPostfix("2*(2*(4+1))")));
        assertEquals("18.0", pf.evaluate(pf.infixToPostfix("2*((2*4)+1)")));
        assertEquals("20.0", pf.evaluate(pf.infixToPostfix("((4+1)*2)*2")));
        assertEquals("18.0", pf.evaluate(pf.infixToPostfix("(1+(2*4))*2")));
    }
}