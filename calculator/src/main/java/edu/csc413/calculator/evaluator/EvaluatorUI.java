package edu.csc413.calculator.evaluator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluatorUI extends JFrame implements ActionListener {

    private TextField txField = new TextField();
    private Panel buttonPanel = new Panel();

    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] bText = {
            "7", "8", "9", "+", "4", "5", "6", "- ", "1", "2", "3",
            "*", "0", "^", "=", "/", "(", ")", "C", "CE"
    };

    private String infix = "";
    public int result = 0;

    /**
     * C  is for clear, clears entire expression
     * CE is for clear expression, clears last entry up until the last operator.
     */
    private Button[] buttons = new Button[bText.length];

    public static void main(String argv[]) {
        EvaluatorUI calc = new EvaluatorUI();
    }

    public EvaluatorUI() {
        setLayout(new BorderLayout());
        this.txField.setPreferredSize(new Dimension(600, 50));
        this.txField.setFont(new Font("Courier", Font.BOLD, 28));

        add(txField, BorderLayout.NORTH);
        txField.setEditable(true);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //create 20 buttons with corresponding text in bText[] array
        Button bt;
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            bt = new Button(bText[i]);
            bt.setFont(new Font("Courier", Font.BOLD, 28));
            buttons[i] = bt;
        }

        //add buttons to button panel
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttonPanel.add(buttons[i]);
        }

        //set up buttons to listen for mouse input
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttons[i].addActionListener(this);
        }

        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // You need to fill in this fuction

        //buttons
        if (e.getSource() == buttons[0]) {
            txField.setText("7");
            infix += "7";
        }
        if (e.getSource() == buttons[1]) {
            txField.setText("8");
            infix += "8";
        }
        if (e.getSource() == buttons[2]) {
            txField.setText("9");
            infix += "9";
        }
        if (e.getSource() == buttons[4]) {
            txField.setText("4");
            infix += "4";
        }
        if (e.getSource() == buttons[5]) {
            txField.setText("5");
            infix += "5";
        }
        if (e.getSource() == buttons[6]) {
            txField.setText("6");
            infix += "6";
        }
        if (e.getSource() == buttons[8]) {
            txField.setText("1");
            infix += "1";
        }
        if (e.getSource() == buttons[9]) {
            txField.setText("2");
            infix += "2";
        }
        if (e.getSource() == buttons[10]) {
            txField.setText("3");
            infix += "3";
        }
        if (e.getSource() == buttons[12]) {
            txField.setText("0");
            infix += "0";
        }
        if (e.getSource() == buttons[18]) {
            txField.setText("");
            infix = "";
        }
        if (e.getSource() == buttons[19]) {
            txField.setText("");
            infix = "";
        }

        //operators
        if (e.getSource() == buttons[3]) {
            infix += "+";
        }
        if (e.getSource() == buttons[7]) {
            infix += "-";
        }
        if (e.getSource() == buttons[11]) {
            infix += "*";
        }
        if (e.getSource() == buttons[15]) {
            infix += "/";
        }
        if (e.getSource() == buttons[19]) {
            infix += "";
        }
        if (e.getSource() == buttons[13]) {
            infix += "^";
        }

        // paren
        if (e.getSource() == buttons[16]) {
            infix += "(";
        }
        if (e.getSource() == buttons[17]) {
            infix += ")";
        }

        // equal
        if (e.getSource() == buttons[14]) {
            Evaluator ev = new Evaluator();
            result = ev.eval(infix);
            infix = Integer.toString(result);
            txField.setText(infix);
            infix = "";
        }

        System.out.println(infix);
    }
}
