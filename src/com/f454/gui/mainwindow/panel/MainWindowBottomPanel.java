package com.f454.gui.mainwindow.panel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.f454.graph.mathobject.basic.Circle;
import com.f454.graph.mathobject.basic.DataSet;
import com.f454.graph.mathobject.basic.constructed.ParametricEquation;
import com.f454.graph.mathobject.basic.constructed.SimpleFunction;
import com.f454.gui.input.InputCancelledException;
import com.f454.gui.input.circle.InputCircleDialog;
import com.f454.gui.input.data.InputDataDialog;
import com.f454.gui.input.function.InputSimpleFunctionDialog;
import com.f454.gui.input.parametric.InputParametricEquationDialog;
import com.f454.gui.mainwindow.MainWindow;
import com.f454.gui.setting.SetResolutionDialog;

public class MainWindowBottomPanel extends JPanel {
	
	public static ActionListener addPointListener = new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			DataSet d;
			
			try {
				d = InputDataDialog.getData();
				MainWindow m = MainWindow.getInstance();
				m.getGraphingPanel().getGraphingEngine().addMathObject(d);
			} catch(InputCancelledException err) {}
		}
		
	};
	
	public static ActionListener addSimpleFunctionListener = new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			SimpleFunction f;
			
			try {
				f = InputSimpleFunctionDialog.getFunction();
				MainWindow m = MainWindow.getInstance();
				m.getGraphingPanel().getGraphingEngine().addMathObject(f);
			} catch (InputCancelledException err) {}
			
		}
		
	};
	
	public static ActionListener addParametricListener = new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			ParametricEquation parametric;
			
			try {
				parametric = InputParametricEquationDialog.getEquation();
				MainWindow m = MainWindow.getInstance();
				m.getGraphingPanel().getGraphingEngine().addMathObject(parametric);
			} catch (InputCancelledException err) {}
			
		}
		
	};
	
	public static ActionListener addCircleListener = new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			Circle circle;
			try {
				circle = InputCircleDialog.getCircle();
				MainWindow m = MainWindow.getInstance();
				m.getGraphingPanel().getGraphingEngine().addMathObject(circle);
			} catch (InputCancelledException err) {}
		}
		
	};
	
	public static ActionListener homeListener = new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			MainWindow m = MainWindow.getInstance();
			m.getGraphingPanel().getGraphingEngine().getScalingManager().reset();
		}
		
	};
	
	public static ActionListener settingsListener = new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			new SetResolutionDialog();
		}
		
	};
	
	private JButton addPoint;
	private JButton addSimpleFunction;
	private JButton addParametric;
	private JButton addCircle;
	
	private JButton home;
	private JButton raiseSettings;
	
	public MainWindowBottomPanel() {
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		addPoint = new JButton("(x, y)");
		addPoint.addActionListener(addPointListener);
		
		addSimpleFunction = new JButton("f(x)");
		addSimpleFunction.addActionListener(addSimpleFunctionListener);
		
		addParametric = new JButton("x(t), y(t)");
		addParametric.addActionListener(addParametricListener);
		
		addCircle = new JButton("Circle");
		addCircle.addActionListener(addCircleListener);
		
		home = new JButton("Home");
		home.addActionListener(homeListener);
		
		raiseSettings = new JButton("Settings");
		raiseSettings.addActionListener(settingsListener);
		
		add(addPoint);
		add(addSimpleFunction);
		add(addParametric);
		add(addCircle);
		
		add(new JSeparator(JSeparator.VERTICAL));
		
		add(home);
		add(raiseSettings);
	}
	
}
