
public class CalcEngineHex extends CalcEngine {
    protected boolean showingHex = false;

    /**
     * Creates a new calculator engine using superclass. Adds the functionality to calculate in hexadecimal
     */
    public CalcEngineHex(){
        super();
    }

    /**
     * Calculating in Hexadecimal.
     * This overrides the original numberPressed method. If you using the Hexadecimal calculation,
     * this will calculate in Hexadecimal by changing the display-value differently.
     * @param number The number pressed on the calculator.
     */
    @Override
    public void numberPressed(int number) {
        if (!showingHex) {
            super.numberPressed(number);
        } else {
            if(buildingDisplayValue) {
                // Incorporate this digit.
                displayValue = displayValue*16 + number;
            }
            else {
                // Start building a new number.
                displayValue = number;
                buildingDisplayValue = true;
            }
        }
    }

    /**
     * Converts a number into a Hexadecimal number and returns it.
     * @param number A number to be converted to Hexadecimal.
     * @return A String containing the number represented as a Hexadecimal number.
     */
    protected String calculateHex(int number){
        return Integer.toHexString(number).toUpperCase();
    }
}
