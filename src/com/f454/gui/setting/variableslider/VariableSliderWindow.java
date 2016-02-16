package com.f454.gui.setting.variableslider;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	
	private static final int DEFAULT_VAL = (int) (Math.pow(STEP, -1));
	
	private JSlider sliders[];
	private JButton reset;
	
	public VariableSliderWindow() {
		super(TITLE);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		sliders = new JSlider[VARIABLES.length];
		JPanel sliderPanel = new JPanel();
		sliderPanel.setLayout(new GridLayout(VARIABLES.length, 2));
		
		//
		final int min = (int) (MIN * Math.pow(STEP, -1));
		final int max = (int) (MAX * Math.pow(STEP, -1));
		
		for(int i = 0; i < VARIABLES.length; i++) {
			
			sliders[i] = new JSlider(JSlider.HORIZONTAL);
			JLabel label = new JLabel("", JLabel.CENTER);
			
			sliders[i].addChangeListener(new SliderListener(VARIABLES[i], label));
			
			sliders[i].setMinimum(min);
			sliders[i].setMaximum(max);
			
			// EXPLICITLY set the value of the JSlider so that the change listener
			// is called. This sets the label to the correct text. :)
			sliders[i].setValue(DEFAULT_VAL);
			
			sliderPanel.add(label);
			sliderPanel.add(sliders[i]);
		}
		
		reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				for(JSlider slider : sliders) {
					slider.setValue(DEFAULT_VAL);
				}
			}
			
		});
		
		add(sliderPanel);
		add(reset, BorderLayout.SOUTH);
		
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
	
	private class SliderListener implements ChangeListener {
		
		private String variableName;
		private JLabel label;
		
		public SliderListener(String variableName, JLabel label) {
			this.variableName = variableName;
			this.label = label;
		}
		
		public void stateChanged(ChangeEvent e) {
			// refresh the mathematical objects in accordance to the
			// change in variables...
			MainWindow m = MainWindow.getInstance();
			m.getGraphingPanel().getGraphingEngine().refresh();
			
			JSlider slider = (JSlider) (e.getSource());
			label.setText(String.format("%s = %.2f", variableName, slider.getValue() * STEP));
		}
		
	};
	
}
