package com.joechrisellis.f454.gui.windows;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.joechrisellis.f454.gui.components.ResolutionSpinner;

public class SetResolutionDialogue extends JFrame {
	
	public static final String TITLE = "Settings";
	
	private ResolutionSpinner resolutionSpinner;
	private JButton ok;
	private JButton autoSetResolution;
	private JButton setHighQualResolution;
	private JButton setPerformanceResolution;
	
	public SetResolutionDialogue(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		
		add(new JLabel("Resolution Settings"));
		
		resolutionSpinner = new ResolutionSpinner();
		resolutionSpinner.setValue(MainWindow.main.getGraphingPanel().getGraphingEngine().getResolution());
		setupButtons();
		
		add(resolutionSpinner);
		
		add(autoSetResolution);
		add(setHighQualResolution);
		add(setPerformanceResolution);
		add(ok);

		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void setupButtons() {
		ok = new JButton("OK");
		
		autoSetResolution = new JButton("Auto Set Resolution");
		setHighQualResolution = new JButton("Set High Quality Resolution");
		setPerformanceResolution = new JButton("Set Performance Resolution");
		
		autoSetResolution.setToolTipText(
				"Automatically set resolution based on your computer's power.");
		setHighQualResolution.setToolTipText(
				"Set the resolution to 0.01 for high quality rendering.");
		setPerformanceResolution.setToolTipText(
				"Set the resolution to 1.25 for performance rendering.");
		
		autoSetResolution.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				double n = getProcessingPower();
				resolutionSpinner.setValue(0.02 * n);
			}
			
		});
		
		setHighQualResolution.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				resolutionSpinner.setValue(0.01);
			}
			
		});
		
		setPerformanceResolution.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				resolutionSpinner.setValue(1.25);
			}
			
		});
		
		ok.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				double resolution = (double) (resolutionSpinner.getValue());
				MainWindow.main.getGraphingPanel().getGraphingEngine().setResolution(resolution);
				dispose();
			}
			
		});
	}
	
	public static double getProcessingPower() {
		double startTime = System.currentTimeMillis();
		int sum = 0;
		for(int i = 0; i < 1000000; i++) {
			sum += i;
		}
		double endTime = System.currentTimeMillis();
		
		return endTime - startTime;
	}
	
}
