package com.joechrisellis.f454.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class AddPointButton extends JButton implements ActionListener {
	
	private final String TEXT = "(x, y)";
	
	public AddPointButton() {
		setText(TEXT);
	}

	public void actionPerformed(ActionEvent e) {
		// raise add new point dialogue.
	}
	
}
