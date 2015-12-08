package com.joechrisellis.f454.gui.windows;

import javax.swing.JFrame;

public class InputSimpleFunctionWindow2 extends JFrame {
	
	public static final String TITLE = "Input Simple Function of the Form x = f(y)";
	public static final int HEIGHT = 500;
	public static final int WIDTH = (int) (MainWindow.GOLDEN_RATIO * HEIGHT);
	
	public InputSimpleFunctionWindow2(String title, int width, int height) {
		super(title);
		setSize(width, height);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}
