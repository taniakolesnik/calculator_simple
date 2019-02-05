import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {

    private JButton buttonTwo;
    private JButton buttonFive;
    private JButton buttonThree;
    private JButton buttonSix;
    private JButton buttonEight;
    private JButton buttonNine;
    private JButton buttonFour;
    private JButton buttonOne;
    private JButton buttonSeven;
    private JButton buttonDelete;
    private JButton buttonZero;
    private JButton buttonDelimeter;
    private JPanel displayPanel;
    private JTextField displayTextField;
    private JPanel rootPanel;

    public Calculator() throws HeadlessException {
        setTitle("Calculator");
        setSize(400, 300);
        add(rootPanel);
        System.out.println("T");
    }
}
