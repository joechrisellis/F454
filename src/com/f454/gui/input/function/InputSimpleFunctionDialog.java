package com.f454.gui.input.function;

import java.awt.Color;

import com.f454.graph.mathobject.basic.constructed.SimpleFunction;
import com.f454.gui.input.InputCancelledException;
import com.f454.gui.input.InputDialog;
import com.f454.gui.input.LabelAndColorPanel;
import com.f454.gui.mainwindow.MainWindow;

public class InputSimpleFunctionDialog extends InputDialog {
	
	public static final String TITLE = "Input Simple Function";
	
	private InputSimpleFunctionBasicPanel basicPanel;
	private InputSimpleFunctionDomainPanel domainPanel;
	private LabelAndColorPanel advancedPanel;
	
	protected InputSimpleFunctionDialog() {
		this(null);
	}
	
	protected InputSimpleFunctionDialog(SimpleFunction f) {
		super(TITLE, 500);
				
		basicPanel = new InputSimpleFunctionBasicPanel(ok);
		
		domainPanel = new InputSimpleFunctionDomainPanel();
		advancedPanel = new LabelAndColorPanel("Function");
		
		tabs.addTab("Basic", basicPanel);
		tabs.addTab("Domain & Range", domainPanel);
		tabs.addTab("Advanced", advancedPanel);
		
		add(tabs);
		
		// If an object has been passed in, we are expected to 
		// recreate the window for editing.
		if(f != null) {
			basicPanel.input.setText(f.getExpression());
			basicPanel.yEquals.setSelected(f.isyEquals());
			basicPanel.xEquals.setSelected(!f.isyEquals());
			domainPanel.hasDomain.setSelected(f.hasDomain());
			
		}
		
		setVisible(true);
	}
	
	/**
	 * Raises a dialogue allowing the user to input the function and creates
	 * a function object from the expression that they have inputed. 
	 * @return SimpleFunction A SimpleFunction object created using the inputted expression.
	 * @throws InputCancelledException 
	 */
	public static SimpleFunction getFunction() throws InputCancelledException {
		InputSimpleFunctionDialog window = new InputSimpleFunctionDialog();
		if(window.wasCancelled()) {
			throw new InputCancelledException("Input of function cancelled.");
		}
		
		String expression = window.basicPanel.getExpression();
		
		String label = window.advancedPanel.getLabel();
		Color color = window.advancedPanel.getChosenColor();
		
		MainWindow m = MainWindow.getInstance();
		SimpleFunction f = new SimpleFunction(label, window.basicPanel.isyEquals(), expression,
				color, m.getGraphingPanel().getGraphingEngine().getScalingManager());
		
		if(window.domainPanel.hasDomain()) {
			f.setDomain(window.domainPanel.getDomainLBound(), window.domainPanel.getDomainUBound());
		}
		
		if(window.domainPanel.hasRange()) {
			f.setRange(window.domainPanel.getRangeLBound(), window.domainPanel.getRangeUBound());
		}
		
		f.reinit();
		
		return f;
	}
	
	public static void editFunction(SimpleFunction f) throws InputCancelledException {
		InputSimpleFunctionDialog window = new InputSimpleFunctionDialog(f);
		if(window.wasCancelled()) {
			throw new InputCancelledException("Input of function cancelled.");
		}
	}
	
}
