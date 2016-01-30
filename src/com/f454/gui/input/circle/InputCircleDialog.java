package com.f454.gui.input.circle;

import java.awt.Color;

import com.f454.graph.mathobject.basic.Circle;
import com.f454.gui.input.InputCancelledException;
import com.f454.gui.input.InputDialog;
import com.f454.gui.input.NameAndColorPanel;
import com.f454.gui.mainwindow.MainWindow;

public class InputCircleDialog extends InputDialog {
	
	public static final String TITLE = "Input Circle";
	
	private InputCircleBasicPanel basicPanel;
	private NameAndColorPanel advancedPanel;
	
	private InputCircleDialog() {
		super(TITLE, 600);
		
		basicPanel = new InputCircleBasicPanel();
		advancedPanel = new NameAndColorPanel("Circle");
		
		tabs.addTab("Basic", basicPanel);
		tabs.addTab("Advanced", advancedPanel);
		
		add(tabs);
		
		setVisible(true);
	}
	
	/**
	 * Raises a dialogue allowing the user to input the circle and creates
	 * a circle object from the information that they have inputed. 
	 * @return Circle A circle object created using the inputed information.
	 * @throws InputCancelledException 
	 */
	public static Circle getCircle() throws InputCancelledException {
		InputCircleDialog window = new InputCircleDialog();
		if(window.wasCancelled()) {
			throw new InputCancelledException("Input of circle was cancelled.");
		}
		
		double a = window.basicPanel.getA();
		double b = window.basicPanel.getB();
		double r = window.basicPanel.getR();
		
		String label = window.advancedPanel.getLabel();
		Color color = window.advancedPanel.getChosenColor();
		
		MainWindow m = MainWindow.getInstance();
		Circle circle = new Circle(label, a, b, r,
				color, m.getGraphingPanel().getGraphingEngine().getScalingManager());
		
		return circle;
	}
		
}
