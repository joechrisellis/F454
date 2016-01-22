package com.f454.gui.mainwindow.panel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.f454.graph.mathobject.basic.Circle;
import com.f454.graph.mathobject.basic.constructed.ParametricEquation;
import com.f454.graph.mathobject.basic.constructed.SimpleFunction;
import com.f454.gui.input.InputCancelledException;
import com.f454.gui.input.circle.InputCircleDialog;
import com.f454.gui.input.function.InputSimpleFunctionDialog;
import com.f454.gui.input.parametric.InputParametricEquationDialog;
import com.f454.gui.mainwindow.MainWindow;
import com.f454.gui.setting.SetResolutionDialogue;

public class MainWindowBottomPanel extends JPanel {
	
	private JButton addPoint;
	private JButton addSimpleFunction;
	private JButton addParametric;
	private JButton addCircle;
	
	private JButton home;
	private JButton raiseSettings;
	
	public MainWindowBottomPanel() {
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		addPoint = new JButton("(x, y)");
		addPoint.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO: raise input points dialogue.
			}
			
		});
		
		addSimpleFunction = new JButton("f(x)");
		addSimpleFunction.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				SimpleFunction f;
				
				try {
					f = InputSimpleFunctionDialog.getFunction();
					MainWindow m = MainWindow.getInstance();
					m.getGraphingPanel().getGraphingEngine().addMathObject(f);
				} catch (InputCancelledException err) {}
				
			}
			
		});
		
		addParametric = new JButton("x(t), y(t)");
		addParametric.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ParametricEquation parametric;
				
				try {
					parametric = InputParametricEquationDialog.getEquation();
					MainWindow m = MainWindow.getInstance();
					m.getGraphingPanel().getGraphingEngine().addMathObject(parametric);
				} catch (InputCancelledException err) {}
				
			}
			
		});
		
		addCircle = new JButton("Circle");
		addCircle.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Circle circle;
				try {
					circle = InputCircleDialog.getCircle();
					MainWindow m = MainWindow.getInstance();
					m.getGraphingPanel().getGraphingEngine().addMathObject(circle);
				} catch (InputCancelledException err) {}
			}
			
		});
		
		home = new JButton("Home");
		home.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				MainWindow m = MainWindow.getInstance();
				m.getGraphingPanel().getGraphingEngine().getScalingManager().reset();
			}
			
		});
		
		raiseSettings = new JButton("Settings");
		raiseSettings.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new SetResolutionDialogue();
			}
			
		});
		
		add(addPoint);
		add(addSimpleFunction);
		add(addParametric);
		add(addCircle);
		
		add(new JSeparator(JSeparator.VERTICAL));
		
		add(home);
		add(raiseSettings);
	}
	
}
