package com.f454.gui.mainwindow.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.f454.gui.mainwindow.panel.MainWindowBottomPanel;

public class MainWindowFunctionMenu extends JMenu {
	
	private JMenuItem data;
	private JMenuItem function;
	private JMenuItem parametric;
	private JMenuItem circle;
	
	public MainWindowFunctionMenu() {
		super("Function");
		setMnemonic(KeyEvent.VK_N);
		
		data = new JMenuItem("Add Data Set");
		data.addActionListener(MainWindowBottomPanel.addPointListener);
		
		function = new JMenuItem("Add Simple Function");
		function.addActionListener(MainWindowBottomPanel.addSimpleFunctionListener);
		
		parametric = new JMenuItem("Add Parametric Equation");
		parametric.addActionListener(MainWindowBottomPanel.addParametricListener);
		
		circle = new JMenuItem("Add Circle");
		circle.addActionListener(MainWindowBottomPanel.addCircleListener);
		
		add(data);
		add(function);
		add(parametric);
		add(circle);
	}
	
}
