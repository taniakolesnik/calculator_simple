import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    private JButton buttonDelete;
    private JButton buttonDelimeter;
    private JPanel displayPanel;
    private JTextField displayTextField;
    private JPanel rootPanel;

    public Calculator() throws HeadlessException {
        setTitle("Calculator");
        setSize(400, 300);
        add(rootPanel);

        setButtonListeners();

    }

    private void setButtonListeners() {
        for (Component component : rootPanel.getComponents()){
            if (component.getClass() == JButton.class){
                ((JButton) component).addActionListener(this);
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source instanceof JButton) System.out.println(e.getActionCommand());
    }
}
