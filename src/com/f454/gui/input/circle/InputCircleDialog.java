package com.f454.gui.input.circle;

import java.awt.Color;

import com.f454.graph.mathobject.basic.Circle;
import com.f454.gui.input.InputDialog;
import com.f454.gui.mainwindow.MainWindow;

public class InputCircleDialog extends InputDialog {
	
	public static final String TITLE = "Input Circle";
	
	private InputCircleBasicPanel basicPanel;
	private InputCircleAdvancedPanel advancedPanel;
		
	public InputCircleDialog() {
		super(TITLE, 600);
		
		basicPanel = new InputCircleBasicPanel();
		advancedPanel = new InputCircleAdvancedPanel();
		
		tabs.addTab("Basic", basicPanel);
		tabs.addTab("Advanced", advancedPanel);
		
		add(tabs);
		
		setVisible(true);
	}
	
	/**
	 * Raises a dialogue allowing the user to input the circle and creates
	 * a circle object from the information that they have inputed. 
	 * @return Circle A circle object created using the inputed information.
	 */
	public static Circle getCircle() {
		InputCircleDialog window = new InputCircleDialog();
		
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
