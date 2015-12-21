package com.joechrisellis.f454.gui.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import com.joechrisellis.f454.gui.components.panels.GraphingPanel;
import com.joechrisellis.f454.gui.components.panels.MainWindowBottomPanel;
import com.joechrisellis.f454.gui.components.panels.MainWindowMathObjectsPanel;
import com.joechrisellis.f454.gui.menus.MainWindowMenuBar;

public class MainWindow extends JFrame {
	
	public static final String TITLE = "F454 Graphing Calculator";
	
	// The golden ratio is used to achieve an aesthetically pleasing rectangle.
	public static double GOLDEN_RATIO = 1.618D;
	public static final int HEIGHT = 920;
	public static final int WIDTH = (int) (GOLDEN_RATIO * HEIGHT);
	
	public static MainWindow main;
	private MainWindowMenuBar menuBar;
	
	private JSplitPane splitPane;
	private MainWindowMathObjectsPanel mathPanel;
	private GraphingPanel graphingPanel;
	
	private MainWindowBottomPanel bottomPanel;
	
	public MainWindow(String title, int width, int height) {
		super(title);
		setPreferredSize(new Dimension(width, height));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());				
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		main = new MainWindow(MainWindow.TITLE, MainWindow.WIDTH, MainWindow.HEIGHT);
		main.start();
	}
	
	public void updateUI() {
		mathPanel.initListItems();
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
	
}
