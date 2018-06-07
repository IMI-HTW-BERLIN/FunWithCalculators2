/**
 * The main class of a simple calculator. Create one of these and you'll
 * get the calculator on screen.
 *
 * @author David Panagiotopulos and Bernhard Hoffmann | Original: David J. Barnes and Michael Kolling
 * @version 2018.05.24
 */
public class Calculator
{
    private UserInterfaceHex gui;
    private CalcEnginePostfix engine;

    public static void main(String[] args) {
        Calculator c = new Calculator();
    }
    /**
     * Create a new calculator and show it.
     */
    public Calculator()
    {
        engine = new CalcEnginePostfix();
        gui = new UserInterfaceHex(engine);
    }

    /**
     * In case the window was closed, show it again.
     */
    public void show()
    {
        gui.setVisible(true);
    }
}
