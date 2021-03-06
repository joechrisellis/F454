package com.f454.gui.input.data;

import java.awt.Color;

import com.f454.graph.mathobject.basic.DataSet;
import com.f454.gui.input.InputCancelledException;
import com.f454.gui.input.InputDialog;
import com.f454.gui.input.LabelAndColorPanel;
import com.f454.gui.mainwindow.MainWindow;

public class InputDataDialog extends InputDialog {
	
	public static final String TITLE = "Input Data";
	
	private InputDataBasicPanel basicPanel;
	private LabelAndColorPanel advancedPanel;
	
	public InputDataDialog() {
		super(TITLE, 500);
		
		basicPanel = new InputDataBasicPanel(ok);
		advancedPanel = new LabelAndColorPanel("Data Set");
		
		tabs.addTab("Basic", basicPanel);
		tabs.addTab("Advanced", advancedPanel);
		
		add(tabs);
		setVisible(true);
	}
	
	/**
	 * Raises a dialog allowing the user to input the function and creates
	 * a function object from the expression that they have inputed. 
	 * @return SimpleFunction A SimpleFunction object created using the inputed expression.
	 * @throws InputCancelledException 
	 */
	public static DataSet getData() throws InputCancelledException {
		InputDataDialog window = new InputDataDialog();
		if(window.wasCancelled()) {
			throw new InputCancelledException("Input of data cancelled.");
		}
		
		String label = window.advancedPanel.getLabel();
		Color color = window.advancedPanel.getChosenColor();
		
		MainWindow m = MainWindow.getInstance();
		DataSet ds = new DataSet(label, window.basicPanel.getPoints(),
				color, m.getGraphingPanel().getGraphingEngine().getScalingManager());
		
		return ds;
		
	}

}
