import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * A graphical user interface extending the original one by adding Hexadecimal buttons.
 */
public class UserInterfaceHex extends UserInterface {
    private JLabel labelDecHexSwitch;
    private JPanel buttonsForHex;

    private boolean hexMode;
    private boolean showRoundingInfo;

    /**
     * Create a user interface using the superclass. Adds buttons for hexadecimal calculation.
     *
     * @param engine The calculator engine.
     */
    public UserInterfaceHex(CalcEnginePostfix engine) {
        super(engine);
        makeHexFrame();
        hexMode = false;
        showRoundingInfo = true;
    }

    /**
     * Adds buttons for Hexadecimal calculation and a button to switch between Hex and Dec.
     */
    private void makeHexFrame() {
        JPanel contentPane = (JPanel) frame.getContentPane();
        labelDecHexSwitch = new JLabel();
        labelDecHexSwitch.setText("Dec");

        //Hex buttons
        buttonsForHex = new JPanel(new GridLayout(8, 1));
        addButton(buttonsForHex, "A");
        addButton(buttonsForHex, "B");
        addButton(buttonsForHex, "C");
        addButton(buttonsForHex, "D");

        addButton(buttonsForHex, "E");
        addButton(buttonsForHex, "F");
        buttonsForHex.add(new JLabel(" "));
        buttonsForHex.add(new JLabel(" "));

        //Dec/Hex switch-button and text
        JPanel buttonDecHexSwitch = new JPanel(new GridLayout(1, 4));
        addButton(buttonDecHexSwitch, "Dec/Hex");
        buttonDecHexSwitch.add(labelDecHexSwitch);
        buttonDecHexSwitch.add(new JLabel(" "));
        buttonDecHexSwitch.add(new JLabel(" "));

        contentPane.add(buttonsForHex, BorderLayout.EAST);
        buttonsForHex.setVisible(false);
        buttonsForHex.setPreferredSize(new Dimension(50, 100));
        contentPane.add(buttonDecHexSwitch, BorderLayout.SOUTH);
        frame.pack();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    /**
     * Overrides the original actionPerformed method by adding Hthe buttons for Hexadecimal calculation.
     *
     * @param event The event that has occured.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        super.actionPerformed(event);
        switch (command) {
            case "A":
            case "B":
            case "C":
            case "D":
            case "E":
            case "F":
                calc.buttonPressed(command);
                break;
            case "Dec/Hex":
                toggleMode();
                break;
        }
        redisplay();
    }

    /**
     * Configures the button "Dec/Hex" and text next to this button.
     */
    private void toggleMode() {
        hexMode = !hexMode;

        //Check if result was rounded during conversion
        boolean wasRounded = calc.hexMode(hexMode);

        if (showRoundingInfo && wasRounded) {
            String message = "The result was rounded before converting it to hexadecimal.";
            JCheckBox chkShowAgain = new JCheckBox("Don't show this hint again");
            chkShowAgain.addActionListener(e -> showRoundingInfo = !chkShowAgain.isSelected());
            Object[] dialogContent = {message, chkShowAgain};
            JOptionPane.showMessageDialog(frame, dialogContent,"Rounded result", JOptionPane.INFORMATION_MESSAGE);
        }

        buttonsForHex.setVisible(hexMode);
        dotButton.setEnabled(!hexMode);
        labelDecHexSwitch.setText(hexMode ? "Hex" : "Dex");

        redisplay();
    }
}
