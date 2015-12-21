package com.f454.gui.mainwindow;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import com.f454.gui.mainwindow.menu.MainWindowMenuBar;
import com.f454.gui.mainwindow.panel.MainWindowBottomPanel;
import com.f454.gui.mainwindow.panel.MainWindowMathObjectsPanel;

public class MainWindow extends JFrame {
	
	public static final String TITLE = "F454 Graphing Calculator";
	
	// The golden ratio is used to achieve an aesthetically pleasing rectangle.
	public static double GOLDEN_RATIO = 1.618D;
	public static final int HEIGHT = 920;
	public static final int WIDTH = (int) (GOLDEN_RATIO * HEIGHT);
	
	public static MainWindow instance = null;
	
	private MainWindowMenuBar menuBar;
	private JSplitPane splitPane;
	private MainWindowMathObjectsPanel mathPanel;
	private GraphingPanel graphingPanel;
	
	private MainWindowBottomPanel bottomPanel;
	
	// private so that you can't instantiate outside of this class.
	private MainWindow() {
		super(TITLE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());				
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		MainWindow.getInstance().start();
	}
	
	public static MainWindow getInstance() {
		if(instance == null) {
			instance = new MainWindow();
		}
		
		return instance;
	}
	
	private void start() {
		// this function is necessary due to the fact that we have a static instance
		// of the main window. we want the static instance to FULLY INITIALISE (through
		// the constructor) before we start to do anything. this is because this static
		// instance is accessed through other objects. we don't want nullpointers.
		
		menuBar = new MainWindowMenuBar();
		setJMenuBar(menuBar);
		
		graphingPanel = new GraphingPanel();
		graphingPanel.start();
		mathPanel = new MainWindowMathObjectsPanel();
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setRightComponent(graphingPanel);
		splitPane.setLeftComponent(mathPanel);
		add(splitPane);
		
		bottomPanel = new MainWindowBottomPanel();
		add(bottomPanel, BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public GraphingPanel getGraphingPanel() {
		return graphingPanel;
	}
	
	public MainWindowMathObjectsPanel getMathPanel() {
		return mathPanel;
	}
	
}
