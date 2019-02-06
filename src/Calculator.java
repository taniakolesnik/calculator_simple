import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {

    private JButton buttonOne;
    private JButton buttonTwo;
    private JButton buttonThree;
    private JButton buttonFour;
    private JButton buttonFive;
    private JButton buttonSix;
    private JButton buttonSeven;
    private JButton buttonEight;
    private JButton buttonNine;
    private JButton buttonZero;
    private JButton buttonAdd;
    private JButton buttonResult;
    private JButton buttonDivide;
    private JButton buttonSubstract;
    private JButton buttonMultiply;
    private double numberOne;
    private double numberTwo;

    private StringBuilder summaryBuilder;
    private StringBuilder inputBuilder;
    private String operation;

    private JButton buttonDelete;
    private JButton buttonDelimiter;
    private JPanel displayPanel;
    private JTextField displayTextField;
    private JTextField previewTextField;
    private JPanel rootPanel;
    private JPanel mathPanel;


    public Calculator() throws HeadlessException {
        setTitle("Calculator");
        setSize(400, 300);
        add(rootPanel);

        setButtonListeners();
        summaryBuilder = new StringBuilder();
        inputBuilder = new StringBuilder();

    }

    private void setButtonListeners() {
        for (Component component : rootPanel.getComponents()) {
            if (component.getClass() == JButton.class) {
                ((JButton) component).addActionListener(this);
            }
        }

        for (Component component : mathPanel.getComponents()) {
            if (component.getClass() == JButton.class) {
                ((JButton) component).addActionListener(this);
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String value = e.getActionCommand();
        Object source = e.getSource();

        if (source instanceof JButton) {

            switch (value) {

                case "Del":
                    if (!inputBuilder.toString().isEmpty()) {
                        inputBuilder.deleteCharAt(inputBuilder.length() - 1);
                    }
                    displayTextField.setText(inputBuilder.toString());
                    break;

                case "+":
                case "-":
                case "/":
                case "*":
                    operation = value;
                    numberOne = returnNumber(inputBuilder);
                    inputBuilder.setLength(0);
                    break;

                case "=":
                    double result = 0.0;
                    numberTwo = returnNumber(inputBuilder);

                    switch (operation) {
                        case "+":
                            result = numberOne + numberTwo;
                            break;
                        case "-":
                            result = numberOne - numberTwo;
                            break;
                        case "/":
                            result = numberOne / numberTwo;
                            break;
                        case "*":
                            result = numberOne * numberTwo;
                            break;
                    }
                    if (result % 1 == 0){
                        displayTextField.setText(String.valueOf((int)result));
                    } else {
                        displayTextField.setText(String.valueOf(result));
                    }
                    break;
                default:
                    inputBuilder.append(value);
                    displayTextField.setText(inputBuilder.toString());
            }


        }
    }

    private double returnNumber(StringBuilder stringBuilder) {
        return Double.parseDouble(stringBuilder.toString());
    }

}
