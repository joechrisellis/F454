package com.f454.gui.mainwindow.panel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.f454.graph.mathobject.basic.Circle;
import com.f454.graph.mathobject.basic.constructed.ParametricEquation;
import com.f454.graph.mathobject.basic.constructed.SimpleFunctionYEquals;
import com.f454.gui.input.circle.InputCircleDialog;
import com.f454.gui.input.function.InputSimpleFunctionDialog1;
import com.f454.gui.input.function.InputSimpleFunctionDialog2;
import com.f454.gui.input.parametric.InputParametricEquationDialog;
import com.f454.gui.mainwindow.MainWindow;
import com.f454.gui.setting.SetResolutionDialogue;

public class MainWindowBottomPanel extends JPanel {
	
	private JButton addPoint;
	private JButton addSimpleFunction1;
	private JButton addSimpleFunction2;
	private JButton addParametric;
	private JButton addCircle;
	private JButton raiseSettings;
	
	public MainWindowBottomPanel() {
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		addPoint = new JButton("(x, y)");
		addPoint.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO: raise input points dialogue.
			}
			
		});
		
		addSimpleFunction1 = new JButton("y = f(x)");
		addSimpleFunction1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				SimpleFunctionYEquals f = InputSimpleFunctionDialog1.getFunction();
				MainWindow m = MainWindow.getInstance();
				m.getGraphingPanel().getGraphingEngine().addMathObject(f);
			}
			
		});
		
		addSimpleFunction2 = new JButton("x = f(y)");
		addSimpleFunction2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new InputSimpleFunctionDialog2();
			}
			
		});
		
		addParametric = new JButton("x(t), y(t)");
		addParametric.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ParametricEquation parametric = InputParametricEquationDialog.getEquation();
				MainWindow m = MainWindow.getInstance();
				m.getGraphingPanel().getGraphingEngine().addMathObject(parametric);
			}
			
		});
		
		addCircle = new JButton("Circle");
		addCircle.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Circle circle = InputCircleDialog.getCircle();
				MainWindow m = MainWindow.getInstance();
				m.getGraphingPanel().getGraphingEngine().addMathObject(circle);
			}
			
		});
		
		raiseSettings = new JButton("Settings");
		raiseSettings.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new SetResolutionDialogue();
			}
			
		});
		
		add(addPoint);
		add(addSimpleFunction1);
		add(addSimpleFunction2);
		add(addParametric);
		add(addCircle);
		
		add(new JSeparator(JSeparator.VERTICAL));
		add(raiseSettings);
	}
	
}
