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
	
	public static ActionListener variableListener = new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			MainWindow.getInstance().getSliderWindow().showWindow();
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
	
	private JButton raiseVariableSliderWindow;
	private JButton home;
	private JButton raiseSettings;
	
	public MainWindowBottomPanel() {
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		addPoint = new JButton("(x, y)");
		addPoint.setToolTipText("Add a data set");
		addPoint.addActionListener(addPointListener);
		
		addSimpleFunction = new JButton("f(x)");
		addSimpleFunction.setToolTipText("Add a simple function");
		addSimpleFunction.addActionListener(addSimpleFunctionListener);
		
		addParametric = new JButton("x(t), y(t)");
		addParametric.setToolTipText("Add a parametric equation");
		addParametric.addActionListener(addParametricListener);
		
		addCircle = new JButton("Circle");
		addCircle.setToolTipText("Add a circle");
		addCircle.addActionListener(addCircleListener);
		
		raiseVariableSliderWindow = new JButton("Variables");
		raiseVariableSliderWindow.setToolTipText("Open the variable slider window");
		raiseVariableSliderWindow.addActionListener(variableListener);
		
		home = new JButton("Home");
		home.setToolTipText("Return to the default view");
		home.addActionListener(homeListener);
		
		raiseSettings = new JButton("Settings");
		raiseSettings.setToolTipText("Edit the resolution");
		raiseSettings.addActionListener(settingsListener);
		
		add(addPoint);
		add(addSimpleFunction);
		add(addParametric);
		add(addCircle);
		
		add(new JSeparator(JSeparator.VERTICAL));
		
		add(raiseVariableSliderWindow);
		add(home);
		add(raiseSettings);
	}
	
}
