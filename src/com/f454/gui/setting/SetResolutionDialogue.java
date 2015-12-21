package com.f454.gui.setting;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.f454.gui.mainwindow.MainWindow;

public class SetResolutionDialogue extends JDialog {
	
	public static final String TITLE = "Settings";
	
	private JSpinner resolutionSpinner;
	private JButton ok;
	private JButton autoSetResolution;
	private JButton setHighQualResolution;
	private JButton setPerformanceResolution;
	
	public SetResolutionDialogue() {
		super();
		
		setTitle(TITLE);
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		
		add(new JLabel("Resolution Settings"));
		
		resolutionSpinner = new JSpinner(
			new SpinnerNumberModel(0.01, 0.01, 5, 0.01)
		);
		
		MainWindow m = MainWindow.getInstance();
		double res = m.getGraphingPanel().getGraphingEngine().getScalingManager().getResolution();
		resolutionSpinner.setValue(res);
		
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
				MainWindow m = MainWindow.getInstance();
				m.getGraphingPanel().getGraphingEngine().getScalingManager().setResolution(resolution);
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
