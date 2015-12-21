package com.f454.gui.function;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.f454.gui.mainwindow.MainWindow;

public class InputSimpleFunctionDialogue2 extends JDialog {
	
	public static final String TITLE = "Input Simple Function of the Form x = f(y)";
	public static final int HEIGHT = 500;
	public static final int WIDTH = (int) (MainWindow.GOLDEN_RATIO * HEIGHT);
	
	public InputSimpleFunctionDialogue2() {
		super();
		
		setTitle(TITLE);
		setModal(true);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}