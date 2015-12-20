package com.joechrisellis.f454.gui.windows;

import javax.swing.JFrame;

public class InputSimpleFunctionWindow1 extends JFrame {
	
	public static final String TITLE = "Input Simple Function of the Form y = f(x)";
	public static final int HEIGHT = 500;
	public static final int WIDTH = (int) (MainWindow.GOLDEN_RATIO * HEIGHT);
	
	public InputSimpleFunctionWindow1() {
		super(TITLE);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}
