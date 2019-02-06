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
    private String operation = "";
    private String prevOperation = "";

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

                    if (!prevOperation.equals("")){
                        System.out.println("CASE operation IF");
                        prevOperation = "";
                        operation = value;
                        inputBuilder.setLength(0);
                        buttonDelimiter.setEnabled(false);
                        buttonDelete.setEnabled(false);
                        getResult();
                    } else if(prevOperation.equals("=")) {
                        System.out.println("CASE operation ELSE IF");
                        prevOperation = value;
                        operation = value;
                        inputBuilder.setLength(0);
                        buttonDelimiter.setEnabled(false);
                        buttonDelete.setEnabled(false);
                    } else {
                        System.out.println("CASE operation ELSE");
                        prevOperation = value;
                        operation = value;
                        numberOne = returnNumber(inputBuilder);
                        inputBuilder.setLength(0);
                        buttonDelimiter.setEnabled(false);
                        buttonDelete.setEnabled(false);

                }
                    break;
                case "=":
                    if (!prevOperation.isEmpty()){
                        System.out.println("CASE =");
                        numberTwo = returnNumber(inputBuilder);
                        buttonDelimiter.setEnabled(false);
                        buttonDelete.setEnabled(false);
                        getResult();
                        prevOperation = value;
                        operation=value;
                    }

                    break;
                default:
                    System.out.println("CASE DEFAULT");
                    buttonDelimiter.setEnabled(true);
                    buttonDelete.setEnabled(true);
                    inputBuilder.append(value);
                    displayTextField.setText(inputBuilder.toString());
            }

            System.out.println("actionPerformed n1=" + numberOne + ";n2=" + numberTwo + "operation: "+ operation);

        }


    }

    private void getResult() {
        double result = 0.0;
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
        numberOne = result;
        if (result % 1 == 0) {
            displayTextField.setText(String.valueOf((int) result));
        } else {
            displayTextField.setText(String.valueOf(result));
        }
        System.out.println("getResult n1=" + numberOne + ";n2=" + numberTwo);
    }

    private double returnNumber(StringBuilder stringBuilder) {
        return Double.parseDouble(stringBuilder.toString());
    }

}
