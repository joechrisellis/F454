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

/**
 * The dialog that the user can use to change the resolution used by the
 * graphing engine.
 * @author Joe Ellis
 *
 */
public class SetResolutionDialog extends JDialog {
	
	public static final String TITLE = "Settings";
	public static final double RES_HIGH = 0.01, RES_LOW = 1.25;
	
	private JSpinner resolutionSpinner;
	private JButton ok;
	private JButton autoSetResolution;
	private JButton setHighQualResolution;
	private JButton setPerformanceResolution;
	
	public SetResolutionDialog() {
		super();
		
		setTitle(TITLE);
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		
		add(new JLabel("Resolution Settings"));
		
		resolutionSpinner = new JSpinner(
			new SpinnerNumberModel(0.01, 0.001, 5, 0.001)
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
				resolutionSpinner.setValue(getOptimumResolution());
			}
			
		});
		
		setHighQualResolution.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				resolutionSpinner.setValue(RES_HIGH);
			}
			
		});
		
		setPerformanceResolution.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				resolutionSpinner.setValue(RES_LOW);
			}
			
		});
		
		ok.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				double resolution = (double) (resolutionSpinner.getValue());
				MainWindow m = MainWindow.getInstance();
				m.getGraphingPanel().getGraphingEngine().setResolution(resolution);
				dispose();
			}
			
		});
	}
	
	private static final double BENCHMARK_RESOLUTION_COEFFICIENT = 0.02;
	private static double processingPower = 0;
	
	public static double getOptimumResolution() {
		return getProcessingPower() * BENCHMARK_RESOLUTION_COEFFICIENT;
	}
	
	/**
	 * A quick benchmark that determines the speed of the computer that the
	 * program is running on. The method used to do this is quite simple: find
	 * how long it takes to get the sum of the first 1000000 integers.
	 * @return double The time, in milliseconds, taken to compute the first 1000000 integers.
	 */
	private static double getProcessingPower() {
		
		// Guard clause: if this method has been run, just return the last value
		// that it found. HotSpot will optimise this method if it detects that it
		// is being run more than once, so it is important to benchmark only once.
		if(processingPower > 0) {
			return processingPower;
		}
		
		double startTime = System.currentTimeMillis();
		
		// Find the sum of the first 1000000 integers. 
		int sum = 0;
		for(int i = 1; i < 1000000; i++) {
			sum += i;
		}
		
		double endTime = System.currentTimeMillis();
		
		// If endTime == startTime (i.e. the subject computer was capable of performing
		// the computation in under a millisecond) then set the value of processingPower
		// to 1. If not, just set the value of processingPower to endTime - startTime.
		// We CANNOT return zero, since this method is used to automatically determine
		// the resolution -- and that can't be zero otherwise we will have an unlimited loop.
		processingPower = endTime == startTime ? 1 : (endTime - startTime);
		
		return processingPower;
	}
	
}
