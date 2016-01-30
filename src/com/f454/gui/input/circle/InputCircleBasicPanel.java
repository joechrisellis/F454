package com.f454.gui.input.circle;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class InputCircleBasicPanel extends JPanel {
	
	public static final String CARTESIAN_EQUATION = 
			"<html>(x %+d)<sup>2</sup> + (y %+d)<sup>2</sup> = %d<sup>2</sup></html>";
	
	protected JLabel label;
	protected JSpinner a, b, r;
	
	public InputCircleBasicPanel() {
		super();
		setLayout(new BorderLayout());
		
		label = new JLabel(String.format(CARTESIAN_EQUATION, 0, 0, 1), JLabel.CENTER);
		label.setFont(new Font("Sans", 0, 40));
		
		a = new JSpinner();
		b = new JSpinner();
		r = new JSpinner();
		
		// increase the size of each spinner
		for(JSpinner s : new JSpinner[] {a, b, r}) {
			JComponent editor = s.getEditor();
			JFormattedTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
			tf.setColumns(4);
		}
		
		r.setValue(1);
		
		SpinnerChange change = new SpinnerChange();
		a.addChangeListener(change);
		b.addChangeListener(change);
		r.addChangeListener(change);
		
		JPanel panel = new JPanel();
		panel.add(new JLabel("a: "));
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
	
	public int getA() {
		return (int) a.getValue();
	}

	public int getB() {
		return (int) b.getValue();
	}

	public int getR() {
		return (int) r.getValue();
	}
	
}
