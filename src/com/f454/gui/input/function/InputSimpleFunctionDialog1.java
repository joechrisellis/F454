package com.f454.gui.input.function;

import java.awt.Color;

import com.f454.graph.mathobject.SimpleFunctionYEquals;
import com.f454.gui.input.InputDialog;
import com.f454.gui.mainwindow.MainWindow;

public class InputSimpleFunctionDialog1 extends InputDialog {
	
	public static final String TITLE = "Input Simple Function of the Form y = f(x)";
	
	private InputSimpleFunctionBasicPanel basicPanel;
	private InputSimpleFunctionAdvancedPanel advancedPanel;
	
	public InputSimpleFunctionDialog1() {
		super(TITLE, 500);
				
		basicPanel = new InputSimpleFunctionBasicPanel();
		advancedPanel = new InputSimpleFunctionAdvancedPanel();
		
		tabs.addTab("Basic", basicPanel);
		tabs.addTab("Advanced", advancedPanel);
		
		add(tabs);
		
		setVisible(true);
	}
	
	/**
	 * Raises a dialogue allowing the user to input the function and creates
	 * a function object from the expression that they have inputed. 
	 * @return SimpleFunctionYEquals A function object created using the inputed expression.
	 */
	public static SimpleFunctionYEquals getFunction() {
		InputSimpleFunctionDialog1 window = new InputSimpleFunctionDialog1();
		
		String expression = window.basicPanel.getExpression();
		
		String label = window.advancedPanel.getLabel();
		Color color = window.advancedPanel.getChosenColor();
		
		MainWindow m = MainWindow.getInstance();
		SimpleFunctionYEquals f = new SimpleFunctionYEquals(label, expression,
				color, m.getGraphingPanel().getGraphingEngine().getScalingManager());
		
		return f;
	}
	
}
