package com.f454.gui.input.function;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputSimpleFunctionBasicPanel extends JPanel {
	
	private JLabel label;
	private JTextField input;
	
	public InputSimpleFunctionBasicPanel() {
		super();
		
		label = new JLabel("y = ");
		input = new JTextField(30);
		
		add(label);
		add(input);
	}
	
	public String getExpression() {
		return input.getText();
	}
	
}
