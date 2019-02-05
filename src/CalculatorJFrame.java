
import javax.swing.*;
import java.awt.*;

public class CalculatorJFrame extends JFrame{

    private int result = 0;
    private JTextField display;
    private StringBuilder displayBuilder;

    CalculatorJFrame(String title) throws HeadlessException {
        super(title);
        this.setSize(300, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setCalculatorInterface();

        JPanel displayPanel = new JPanel();
        display = new JTextField();
        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(display, BorderLayout.NORTH);
        add(displayPanel);
        setResult();

        displayBuilder = new StringBuilder();
    }

    private void setCalculatorInterface() {
        setNumberButtons();
        setMathActionButtons();
    }
    private void setNumberButtons() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(0, 3));
        for (int i=9; i>=0; i--){
            JButton button = new JButton(String.valueOf(i));
            buttonsPanel.add(button);
            button.addActionListener(e -> {
                updateDisplayNumber(e.getActionCommand());
            });
        }
        add(buttonsPanel,BorderLayout.PAGE_END);
    }

    private void updateDisplayNumber(String buttonValue){
        displayBuilder.append(buttonValue);
        System.out.println(displayBuilder.toString());
        result = Integer.parseInt(displayBuilder.toString());
        setResult();
    }

    private void setResult(){
        display.setText(String.valueOf(result));
    }

    private void setMathActionButtons() {

    }

}
