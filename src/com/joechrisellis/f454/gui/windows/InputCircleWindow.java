package com.joechrisellis.f454.gui.windows;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class InputCircleWindow extends JFrame {
	
	public static final String TITLE = "Input Circle";
	
	public InputCircleWindow() {
		super(TITLE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}
