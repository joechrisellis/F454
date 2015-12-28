package com.f454.gui.input.parametric;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputParametricEquationBasicPanel extends JPanel {
	
	private JTextField inputExpression1, inputExpression2;
	
	public InputParametricEquationBasicPanel() {
		super();
		
		inputExpression1 = new JTextField(38);
		inputExpression2 = new JTextField(38);
		
		add(new JLabel("x(t) = "));
		add(inputExpression1);
		add(new JLabel("y(t) = "));
		add(inputExpression2);
	}
	
	public String getExpression1() {
		return inputExpression1.getText();
	}
	
	public String getExpression2() {
		return inputExpression2.getText();
	}

}
