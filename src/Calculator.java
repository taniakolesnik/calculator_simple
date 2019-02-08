import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {

    private JPanel displayPanel;
    private JTextField displayTextField;

    private JPanel rootPanel;
    private JButton buttonOne,buttonTwo,buttonThree,buttonFour,buttonFive,buttonSix,buttonSeven,buttonEight,buttonNine,buttonZero;
    private JButton buttonDelete,buttonDelimiter;

    private JPanel mathPanel;
    private JButton buttonAdd,buttonResult,buttonDivide,buttonSubstract,buttonMultiply,buttonReset;

    private StringBuilder inputBuilder;
    private double numberOne, numberTwo;
    private boolean isNumberOneSet, isNumberTwoSet, canProceedWithOperation = false;
    private String mathOperation;


    public Calculator() throws HeadlessException {
        setTitle("Calculator");
        setSize(400, 300);
        add(rootPanel);

        setButtonListeners();
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
                        if (inputBuilder.length() == 0) {
                            canProceedWithOperation = false;
                        }

                        if (!inputBuilder.toString().contains(".")) {
                            buttonDelimiter.setEnabled(true);
                        }
                    }
                    displayTextField.setText(inputBuilder.toString());
                    break;

                case "+":
                case "-":
                case "/":
                case "*":
                    if (canProceedWithOperation) {
                        if (!isNumberOneSet && !isNumberTwoSet) { // when both number are NOT set
                            if (inputBuilder.length() != 0) { // get number One
                                numberOne = returnNumber(inputBuilder);
                                isNumberOneSet = true;
                                inputBuilder.setLength(0);
                                buttonDelimiter.setEnabled(true);
                                buttonDelete.setEnabled(false);
                            }

                        }
                        else if (isNumberOneSet && !isNumberTwoSet) { // when we have to take number two now
                            if (inputBuilder.length() != 0) { // get number Two
                                numberTwo = returnNumber(inputBuilder);
                                isNumberTwoSet = true;
                                inputBuilder.setLength(0); //clean inputbuilder
                                buttonDelimiter.setEnabled(true);
                                buttonDelete.setEnabled(false);
                                getResult(value); // get result for number One and Two
                            }
                        } else {
                            inputBuilder.setLength(0);
                            isNumberTwoSet = false;
                            canProceedWithOperation=false;
                            getResult(value);
                        }
                    }
                    buttonDelimiter.setEnabled(true);
                    mathOperation = value;
                    break;
                case "=":
                    if (isNumberOneSet && !isNumberTwoSet) {
                        if (inputBuilder.length() != 0) { // get number Two
                            numberTwo = returnNumber(inputBuilder);
                            isNumberTwoSet = true;
                            inputBuilder.setLength(0); //clean inputbuilder
                            getResult(mathOperation);
                            buttonDelimiter.setEnabled(false);
                            buttonDelete.setEnabled(false);
                        }
                    } else if (isNumberOneSet&isNumberTwoSet){
                        getResult(mathOperation);
                        buttonDelimiter.setEnabled(false);
                        buttonDelete.setEnabled(false);
                    }
                    break;
                case ".":
                    if (!inputBuilder.toString().startsWith(".")){
                        buttonDelimiter.setEnabled(false);
                    }
                default:
                    display(value);
            }
        }


    }

    private void display(String value) {
        System.out.println(value);
        canProceedWithOperation = true;
        buttonDelete.setEnabled(true);
        if (value.equals("C")){
            inputBuilder.setLength(0);
            value = "0";
        }
        if (inputBuilder.length()!=0){
            if (inputBuilder.charAt(0)=='0'){
                inputBuilder.deleteCharAt(0);
            }
        }
        inputBuilder.append(value);
        displayTextField.setText(inputBuilder.toString());
    }

    private void getResult(String value) {
        if (canProceedWithOperation) {
            double result = 0.0;
            switch (value) {
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
            buttonDelimiter.setEnabled(true);
            if (result % 1 == 0) {
                displayTextField.setText(String.valueOf((int) result));
            } else {
                displayTextField.setText(String.valueOf(result));
            }
        }
    }

    private double returnNumber(StringBuilder stringBuilder) {
        return Double.parseDouble(stringBuilder.toString());
    }

}
