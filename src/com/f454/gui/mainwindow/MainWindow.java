package com.f454.gui.mainwindow;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import com.f454.gui.mainwindow.menu.MainWindowMenuBar;
import com.f454.gui.mainwindow.panel.GraphingPanel;
import com.f454.gui.mainwindow.panel.MainWindowBottomPanel;
import com.f454.gui.mainwindow.panel.MainWindowMathObjectsPanel;
import com.f454.gui.setting.variableslider.VariableSliderWindow;
import com.f454.gui.tip.TipOfTheDayDialog;

/**
 * The main window for the graphing calculator. Where the magic happens.
 * @author Joe Ellis
 *
 */
public class MainWindow extends JFrame {
	
	public static final String TITLE = "F454 Graphing Calculator";
	
	// The golden ratio is used to achieve an aesthetically pleasing rectangle.
	public static double GOLDEN_RATIO = 1.618D;
	public static final int HEIGHT = 920;
	public static final int WIDTH = (int) (GOLDEN_RATIO * HEIGHT);
	
	// The SINGLE STATIC INSTANCE of the main window. This is accessed through
	// the method getInstance() (singleton design).
	public static MainWindow instance = null;
	
	// GUI elements:
	private MainWindowMenuBar menuBar;
	private JSplitPane splitPane;
	private MainWindowMathObjectsPanel mathPanel;
	private GraphingPanel graphingPanel;
	private MainWindowBottomPanel bottomPanel;
	
	// slider window
	private VariableSliderWindow sliderWindow;
	
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
	
	/**
	 * Gets the single main window instance.
	 * @return The single main window instance.
	 */
	public static MainWindow getInstance() {
		if(instance == null) {
			instance = new MainWindow();
		}
		
		return instance;
	}
	
	/**
	 * Starts the graphing calculator.
	 */
	private void start() {
		// this function is necessary due to the fact that we have a static instance
		// of the main window. we want the static instance to FULLY INITIALISE (through
		// the constructor) before we start to do anything. this is because this static
		// instance is accessed through other objects. we don't want nullpointers.
		
		
		// create the gui
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
		
		sliderWindow = new VariableSliderWindow();
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		// If the file ~/.nototd doesn't exist, then raise the tip of the day dialog.
		TipOfTheDayDialog.raiseIfNecessary();
	}
	
	/**
	 * Closes the graphing calculator cleanly.
	 */
	public void stop() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
	public GraphingPanel getGraphingPanel() {
		return graphingPanel;
	}
	
	public MainWindowMathObjectsPanel getMathPanel() {
		return mathPanel;
	}
	
	public VariableSliderWindow getSliderWindow() {
		return sliderWindow;
	}
	
}
