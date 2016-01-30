package com.f454.gui.mainwindow.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

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
		data.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));
		
		function = new JMenuItem("Add Simple Function");
		function.addActionListener(MainWindowBottomPanel.addSimpleFunctionListener);
		function.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, KeyEvent.CTRL_DOWN_MASK));
		
		parametric = new JMenuItem("Add Parametric Equation");
		parametric.addActionListener(MainWindowBottomPanel.addParametricListener);
		parametric.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
		
		circle = new JMenuItem("Add Circle");
		circle.addActionListener(MainWindowBottomPanel.addCircleListener);
		circle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
		
		add(data);
		add(function);
		add(parametric);
		add(circle);
	}
	
}
