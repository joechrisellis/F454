package com.joechrisellis.f454.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.joechrisellis.f454.gui.windows.InputSimpleFunctionWindow1;

public class AddSimpleFunctionYEqualsButton extends JButton implements ActionListener {

	private final String TEXT = "y = f(x)";
	
	public AddSimpleFunctionYEqualsButton() {
		setText(TEXT);
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		new InputSimpleFunctionWindow1(InputSimpleFunctionWindow1.TITLE, 
				InputSimpleFunctionWindow1.WIDTH, InputSimpleFunctionWindow1.HEIGHT);
	}
	
}
