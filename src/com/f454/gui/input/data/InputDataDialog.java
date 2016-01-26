package com.f454.gui.input.data;

import java.awt.Color;

import com.f454.graph.mathobject.basic.DataSet;
import com.f454.graph.mathobject.basic.constructed.SimpleFunction;
import com.f454.gui.input.InputCancelledException;
import com.f454.gui.input.InputDialog;
import com.f454.gui.mainwindow.MainWindow;

public class InputDataDialog extends InputDialog {
	
	public static final String TITLE = "Input Parametric Equation";
	
	private InputDataBasicPanel basicPanel;
	private InputDataAdvancedPanel advancedPanel;
	
	public InputDataDialog() {
		super(TITLE, 500);
		
		basicPanel = new InputDataBasicPanel(ok);
		advancedPanel = new InputDataAdvancedPanel();
		
		tabs.addTab("Basic", basicPanel);
		tabs.addTab("Advanced", advancedPanel);
		
		add(tabs);
		setVisible(true);
	}
	
	/**
	 * Raises a dialogue allowing the user to input the function and creates
	 * a function object from the expression that they have inputed. 
	 * @return SimpleFunction A SimpleFunction object created using the inputed expression.
	 * @throws InputCancelledException 
	 */
	public static DataSet getData() throws InputCancelledException {
		InputDataDialog window = new InputDataDialog();
		if(window.wasCancelled()) {
			throw new InputCancelledException("Input of data cancelled.");
		}
		
		return null;
//		
//		String expression = window.basicPanel.getData();
//		
//		String label = window.advancedPanel.getLabel();
//		Color color = window.advancedPanel.getChosenColor();
//		
//		MainWindow m = MainWindow.getInstance();
//		SimpleFunction f = new SimpleFunction(label, window.basicPanel.isyEquals(), expression,
//				color, m.getGraphingPanel().getGraphingEngine().getScalingManager());
//		
//		return f;
	}

}
