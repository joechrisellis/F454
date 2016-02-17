package com.f454.gui.input.parametric;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class InputParametricEquationDomainPanel extends JPanel {
	
	private JSpinner tMax;
	
	public InputParametricEquationDomainPanel() {
		super();
		
		tMax = new JSpinner(
			new SpinnerNumberModel(25, 1, 1000, 1)
		);
		
		// increase the length of the spinner
		JComponent editor = tMax.getEditor();
		JFormattedTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
		tf.setColumns(4);
		
		add(new JLabel("Upper bound for t:"));
		add(tMax);
	}
	
	public int getMax() {
		return (int) (tMax.getValue());
	}
	
}
