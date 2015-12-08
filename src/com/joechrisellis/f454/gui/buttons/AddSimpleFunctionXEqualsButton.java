package com.joechrisellis.f454.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.joechrisellis.f454.gui.windows.InputSimpleFunctionWindow2;

public class AddSimpleFunctionXEqualsButton extends JButton implements ActionListener {
	
	private final String TEXT = "x = f(y)";
	
	public AddSimpleFunctionXEqualsButton() {
		setText(TEXT);
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		new InputSimpleFunctionWindow2(InputSimpleFunctionWindow2.TITLE,
				InputSimpleFunctionWindow2.WIDTH, InputSimpleFunctionWindow2.HEIGHT);
	}

}
