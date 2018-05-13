import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator {
 
    private JFrame frame = new JFrame("Calculator");
    private String result = "";
    private JTextField resultTextField = new JTextField(result, 5);
    private JTextField fNumberTextField = new JTextField("", 5);
    private JTextField sNumberTextField = new JTextField("", 5);
	private JLabel signlabel = new JLabel("");
    private JLabel equallabel = new JLabel("=");
    private JButton buttonadd = new JButton("+");
    private JButton buttonsub = new JButton("-");
    private JButton buttonmultiple = new JButton("*");
    private JButton buttondivide = new JButton("/");
    private JButton buttonequal = new JButton("OK");
    private int sign = 0;
    private Calculator() {
        fNumberTextField.setHorizontalAlignment(JTextField.CENTER);
        sNumberTextField.setHorizontalAlignment(JTextField.CENTER);
        resultTextField.setHorizontalAlignment(JTextField.CENTER);
        resultTextField.setEditable(false);
        signlabel.setHorizontalAlignment(JTextField.CENTER);
        equallabel.setHorizontalAlignment(JTextField.CENTER);
        JPanel uiPan = new JPanel();
        uiPan.setLayout(new GridLayout(2, 2, 5, 5));
        uiPan.add(fNumberTextField);
        uiPan.add(signlabel);
        uiPan.add(sNumberTextField);
        uiPan.add(equallabel);
        uiPan.add(resultTextField);
        uiPan.add(buttonadd);
        uiPan.add(buttonsub);
        uiPan.add(buttonmultiple);
        uiPan.add(buttondivide);
        uiPan.add(buttonequal);
        uiPan.setBorder(BorderFactory.createEmptyBorder(5, 2, 5, 5));


        frame.setLocation(300, 200);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(uiPan, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);

        buttonequal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Double number1 = Double.parseDouble(fNumberTextField.getText());
                Double number2 = Double.parseDouble(sNumberTextField.getText());
                Double res = 0.0;
                if(sign == 0) {
                	return;
                } else if(sign == 1) { 
                    res = number1 + number2;         
                } else if(sign == 2) {
                    res = number1 - number2;
                } else if(sign == 3) {
                    res = number1 * number2;                    
                } else if(sign == 4) {
                    res = number1 / number2;
                }
                resultTextField.setText(res.toString());
            }
        });
        buttonadd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signlabel.setText("+");
                sign = 1;
            }
        });
        buttonsub.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signlabel.setText("-");
                sign = 2;
            }
        });
        buttonmultiple.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signlabel.setText("*");
                sign = 3;
            }
        });
        buttondivide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signlabel.setText("/");
                sign = 4;
            }
        });
    }
    public static void main(String[] args) {
        new Calculator();	
    }
}
