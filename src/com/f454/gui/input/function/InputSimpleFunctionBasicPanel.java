package com.f454.gui.input.function;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class InputSimpleFunctionBasicPanel extends JPanel {
	
	private JRadioButton yEquals;
	private JRadioButton xEquals;
	
	private JTextField input;
	
	public InputSimpleFunctionBasicPanel() {
		super();
		
		input = new JTextField(30);
		
		ButtonGroup g = new ButtonGroup();
		yEquals = new JRadioButton("y = ", true);
		xEquals = new JRadioButton("x = ", false);
		g.add(yEquals);
		g.add(xEquals);
		
		add(yEquals);
		add(xEquals);
		add(input);
	}
	
	public String getExpression() {
		return input.getText();
	}
	
	public boolean isyEquals() {
		return yEquals.isSelected();
	}
	
}
