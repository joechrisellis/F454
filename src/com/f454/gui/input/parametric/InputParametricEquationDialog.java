package com.f454.gui.input.parametric;

import java.awt.Color;

import com.f454.graph.mathobject.basic.constructed.ParametricEquation;
import com.f454.gui.input.InputDialog;
import com.f454.gui.mainwindow.MainWindow;

public class InputParametricEquationDialog extends InputDialog {
	
	public static final String TITLE = "Input Parametric Equation";
	
	private InputParametricEquationBasicPanel basicPanel;
	private InputParametricEquationAdvancedPanel advancedPanel;
	
	public InputParametricEquationDialog() {
		super(TITLE, 500);
		
		basicPanel = new InputParametricEquationBasicPanel();
		advancedPanel = new InputParametricEquationAdvancedPanel();
		tabs.addTab("Basic", basicPanel);
		tabs.addTab("Advanced", advancedPanel);
		
		add(tabs);
		
		setVisible(true);
	}
	
	/**
	 * Raises a dialogue allowing the user to input the equations and creates
	 * a parametric equation object from the expressions that they have inputed. 
	 * @return SimpleFunctionYEquals A parametric equation object created using the inputed expressions.
	 */
	public static ParametricEquation getEquation() {
		InputParametricEquationDialog window = new InputParametricEquationDialog();
		
		String expression1 = window.basicPanel.getExpression1();
		String expression2 = window.basicPanel.getExpression2();
		
		String label = window.advancedPanel.getLabel();
		Color color = window.advancedPanel.getChosenColor();
		
		MainWindow m = MainWindow.getInstance();
		ParametricEquation p = new ParametricEquation(label, expression1, expression2,
				color, m.getGraphingPanel().getGraphingEngine().getScalingManager());
		
		return p;
	}

}
