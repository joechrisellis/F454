package com.f454.gui.input.function;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class InputSimpleFunctionDomainPanel extends JPanel {
	
	private JCheckBox hasDomain;
	private JSpinner domainLBound, domainUBound;
	
	private JCheckBox hasRange;
	private JSpinner rangeLBound, rangeUBound;
	
	public InputSimpleFunctionDomainPanel() {
		super();
		
		domainLBound = new JSpinner();
		domainLBound.setValue(-10);
		domainLBound.setEnabled(false);
		
		domainUBound = new JSpinner();
		domainUBound.setValue(10);
		domainUBound.setEnabled(false);
		
		rangeLBound = new JSpinner();
		rangeLBound.setValue(-10);
		rangeLBound.setEnabled(false);
		
		rangeUBound = new JSpinner();
		rangeUBound.setValue(10);
		rangeUBound.setEnabled(false);
		
		// increase the length of each spinner
		for(JSpinner s : new JSpinner[] {domainLBound, domainUBound,
										rangeLBound, rangeUBound}) {
			JComponent editor = s.getEditor();
			JFormattedTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
			tf.setColumns(12);	
		}
		
		hasDomain = new JCheckBox("Domain", false);
		hasDomain.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				boolean b = hasDomain.isSelected();
				domainLBound.setEnabled(b);
				domainUBound.setEnabled(b);
			}
			
		});
		
		hasRange = new JCheckBox("Range", false);
		hasRange.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				boolean b = hasRange.isSelected();
				rangeLBound.setEnabled(b);
				rangeUBound.setEnabled(b);
			}
			
		});
		
		add(hasDomain);
		add(domainLBound);
		add(new JLabel(" < x < "));
		add(domainUBound);
		
		add(hasRange);
		add(rangeLBound);
		add(new JLabel(" < f < "));
		add(rangeUBound);
	}
	
	public int getDomainLBound() {
		return (int) (domainLBound.getValue());
	}
	
	public int getDomainUBound() {
		return (int) (domainUBound.getValue());
	}

	public int getRangeLBound() {
		return (int) (rangeLBound.getValue());
	}

	public int getRangeUBound() {
		return (int) (rangeUBound.getValue());
	}
	
	public boolean hasDomain() {
		return hasDomain.isSelected();
	}
	
	public boolean hasRange() {
		return hasRange.isSelected();
	}
	
}
