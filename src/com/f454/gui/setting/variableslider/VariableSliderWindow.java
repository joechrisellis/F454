package com.f454.gui.setting.variableslider;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.f454.gui.mainwindow.MainWindow;
import com.fathzer.soft.javaluator.StaticVariableSet;

public class VariableSliderWindow extends JFrame {
	
	public static final String TITLE = "Variable Sliders";
	public static final int HEIGHT = 300;
	public static final int WIDTH = (int) (MainWindow.GOLDEN_RATIO * HEIGHT);
	
	public static final String[] VARIABLES = {"a", "b", "c", "d", "f", "g"};
	private static final int MIN = -10;
	private static final int MAX = 10;
	private static final double STEP = 0.1;
		
	public JSlider sliders[];
	
	public VariableSliderWindow() {
		super(TITLE);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(VARIABLES.length, 2));
		setLocationRelativeTo(null);
		
		ChangeListener cl = new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				MainWindow m = MainWindow.getInstance();
				m.getGraphingPanel().getGraphingEngine().refresh();
			}
			
		};
		
		sliders = new JSlider[VARIABLES.length];
		
		final int min = (int) (MIN * Math.pow(STEP, -1));
		final int max = (int) (MAX * Math.pow(STEP, -1));
		final int value = (int) (Math.pow(STEP, -1));
		for(int i = 0; i < VARIABLES.length; i++) {
			
			sliders[i] = new JSlider(JSlider.HORIZONTAL, min, max, value);
			sliders[i].addChangeListener(cl);
			
			add(new JLabel(VARIABLES[i], JLabel.CENTER));
			add(sliders[i]);
		}
		
		setResizable(false);
		setVisible(false);
		
	}
	
	public void showWindow() {
		setVisible(true);
	}
	
	public StaticVariableSet<Double> getUserVariables() {
		StaticVariableSet<Double> userVariables = new StaticVariableSet<Double>();
		for(int i = 0; i < VARIABLES.length; i++) {
			userVariables.set(VARIABLES[i], (double) (sliders[i].getValue()) * STEP);
		}
		
		return userVariables;
	}
	
}
