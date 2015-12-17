package com.joechrisellis.f454.gui.windows;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class InputSimpleFunctionWindow1 extends JFrame {
	
	public static final String TITLE = "Input Simple Function of the Form y = f(x)";
	public static final int HEIGHT = 500;
	public static final int WIDTH = (int) (MainWindow.GOLDEN_RATIO * HEIGHT);
	
	public InputSimpleFunctionWindow1(String title, int width, int height) {
		super(title);
		setSize(width, height);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}
