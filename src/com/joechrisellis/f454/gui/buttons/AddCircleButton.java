package com.joechrisellis.f454.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class AddCircleButton extends JButton implements ActionListener {
	
	private final String TEXT = "\u25CB";
	
	public AddCircleButton() {
		setText(TEXT);
	}
	
	public void actionPerformed(ActionEvent e) {
		// do appropriate thing.
	}
	
}
