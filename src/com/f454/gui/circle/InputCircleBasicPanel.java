package com.f454.gui.circle;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class InputCircleBasicPanel extends JPanel {
	
	public static final String CARTESIAN_EQUATION = 
			"<html><i>(x %+d)<sup>2</sup> + (y %+d)<sup>2</sup> = %d<sup>2</sup></i></html>";
	
	private JLabel label;
	private JSpinner a, b, r;
	
	public InputCircleBasicPanel() {
		super();
		setLayout(new BorderLayout());
		
		label = new JLabel(String.format(CARTESIAN_EQUATION, 0, 0, 1), JLabel.CENTER);
		label.setFont(new Font("Sans", 0, 40));
		
		a = new JSpinner();
		b = new JSpinner();
		r = new JSpinner();
		r.setValue(1);
		
		SpinnerChange change = new SpinnerChange();
		a.addChangeListener(change);
		b.addChangeListener(change);
		r.addChangeListener(change);
		
		JPanel panel = new JPanel();
		panel .add(new JLabel("a: "));
		panel.add(a);
		panel.add(new JLabel("b: "));
		panel.add(b);
		panel.add(new JLabel("r: "));
		panel.add(r);
		
		add(label);
		add(panel, BorderLayout.SOUTH);
	}
	
	private class SpinnerChange implements ChangeListener {

		public void stateChanged(ChangeEvent e) {
			label.setText(String.format(CARTESIAN_EQUATION, a.getValue(), b.getValue(), r.getValue()));
		}
		
	}
	
	public double getA() {
		return (double) (a.getValue());
	}

	public double getB() {
		return (double) (b.getValue());
	}

	public double getR() {
		return (double) (r.getValue());
	}
	
}
