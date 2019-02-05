import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::startCalculator);
    }

    private static void startCalculator() {
        Calculator calculator = new Calculator();
//      CalculatorJFrame calculator = new CalculatorJFrame("Calculator");
        calculator.setVisible(true);
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
