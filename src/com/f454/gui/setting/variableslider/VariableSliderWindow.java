package com.f454.gui.setting.variableslider;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.f454.graph.GraphingEngine;
import com.f454.gui.mainwindow.MainWindow;

public class VariableSliderWindow extends JFrame {
	
	public static final String TITLE = "Variable Sliders";
	public static final int HEIGHT = 300;
	public static final int WIDTH = (int) (MainWindow.GOLDEN_RATIO * HEIGHT);
	
	public static final String[] VARIABLES = {"a", "b", "c", "d"};
	private static final int MIN = -10;
	private static final int MAX = 10;
		
	public JSlider sliders[];
	
	public VariableSliderWindow() {
		super(TITLE);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(4, 2));
		setLocationRelativeTo(null);
		
		ChangeListener l = new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				GraphingEngine ge = MainWindow.getInstance().getGraphingPanel().getGraphingEngine();
				
				for(int i = 0; i < VARIABLES.length; i++) {
					ge.setVariable(VARIABLES[i], sliders[i].getValue());
				}
				
				ge.refresh();
			}
			
		};
		
		sliders = new JSlider[VARIABLES.length];
		GraphingEngine ge = MainWindow.getInstance().getGraphingPanel().getGraphingEngine();
		for(int i = 0; i < VARIABLES.length; i++) {
			int value = ge.getVariables().get(VARIABLES[i]).intValue();
			sliders[i] = new JSlider(JSlider.HORIZONTAL, MIN, MAX, value);
			sliders[i].setMinorTickSpacing(1);
			sliders[i].setMajorTickSpacing(5);
			sliders[i].setPaintLabels(true);
			sliders[i].setPaintTicks(true);
			
			sliders[i].addChangeListener(l);
			
			add(new JLabel(VARIABLES[i], JLabel.CENTER));
			add(sliders[i]);
		}
				
		setVisible(true);
		
	}
	
}
