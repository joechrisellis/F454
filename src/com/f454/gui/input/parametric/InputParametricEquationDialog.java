package com.f454.gui.input.parametric;

import java.awt.Color;

import com.f454.graph.mathobject.basic.constructed.ParametricEquation;
import com.f454.gui.input.InputCancelledException;
import com.f454.gui.input.InputDialog;
import com.f454.gui.input.LabelAndColorPanel;
import com.f454.gui.mainwindow.MainWindow;

public class InputParametricEquationDialog extends InputDialog {
	
	public static final String TITLE = "Input Parametric Equation";
	
	private InputParametricEquationBasicPanel basicPanel;
	private InputParametricEquationDomainPanel domainPanel;
	private LabelAndColorPanel advancedPanel;
	
	private InputParametricEquationDialog() {
		super(TITLE, 500);
		
		basicPanel = new InputParametricEquationBasicPanel(ok);
		domainPanel = new InputParametricEquationDomainPanel();
		advancedPanel = new LabelAndColorPanel("Parametric Equation");
		
		tabs.addTab("Basic", basicPanel);
		tabs.addTab("Domain", domainPanel);
		tabs.addTab("Advanced", advancedPanel);
		
		add(tabs);
		
		setVisible(true);
	}
	
	/**
	 * Raises a dialog allowing the user to input the equations and creates
	 * a parametric equation object from the expressions that they have inputed. 
	 * @return SimpleFunctionYEquals A parametric equation object created using the inputed expressions.
	 * @throws InputCancelledException 
	 */
	public static ParametricEquation getEquation() throws InputCancelledException {
		InputParametricEquationDialog window = new InputParametricEquationDialog();
		if(window.wasCancelled()) {
			throw new InputCancelledException("Input of parametric equation was cancelled.");
		}
		
		String expression1 = window.basicPanel.getExpression1();
		String expression2 = window.basicPanel.getExpression2();
		int tMax = window.domainPanel.getMax();
		
		String label = window.advancedPanel.getLabel();
		Color color = window.advancedPanel.getChosenColor();
		
		MainWindow m = MainWindow.getInstance();
		ParametricEquation p = new ParametricEquation(label, expression1, expression2, tMax,
				color, m.getGraphingPanel().getGraphingEngine(),
				m.getGraphingPanel().getGraphingEngine().getScalingManager());
		
		p.reinit();
		
		return p;
	}

}
