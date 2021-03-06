package com.f454.gui.input.data;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SinglePointInputPanel extends JPanel {
	
	private InputDataBasicPanel panel;
	private FocusField x, y;
	private JButton remove;
	
	private boolean validSyntax = true;
	private boolean removed;
	
	public SinglePointInputPanel(final InputDataBasicPanel panel) {
		super();
		this.panel = panel;
		
		setLayout(new FlowLayout());
		x = new FocusField("0", 10);
		y = new FocusField("0", 10);
		
		DocumentListener l = new DocumentListener() {
			
			public void changedUpdate(DocumentEvent e) {
				checkSyntax();
			}
			
			public void removeUpdate(DocumentEvent e) {
				checkSyntax();
			}
			
			public void insertUpdate(DocumentEvent e) {
				checkSyntax();
			}
			
		};
		
		x.getDocument().addDocumentListener(l);
		y.getDocument().addDocumentListener(l);
		
		remove = new JButton("-");
		
		add(new JLabel("x = "));
		add(x);
		add(new JLabel("y = "));
		add(y);
		
		remove.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(panel.points.size() > 1) {
					removed = true;
					panel.refresh();
				}
			}
			
		});
		
		remove.setFocusable(false);
		add(remove);
	}
	
	private void checkSyntax() {
		validSyntax = isNumeric(x.getText()) && isNumeric(y.getText());
		panel.updateOkButton();
	}
	
	public boolean hasValidSyntax() {
		return validSyntax;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public String getXVal() {
		return x.getText();
	}
	
	public String getYVal() {
		return y.getText();
	}
	
	/**
	 * Returns a boolean representing whether or not a string can be parsed
	 * as a double.
	 * @param str The string to check.
	 */
	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	private static class FocusField extends JTextField {
		
		{
			addFocusListener(new FocusListener() {
				
				public void focusLost(FocusEvent e) {
					FocusField.this.select(0, 0);
				}
				
				public void focusGained(FocusEvent e) {
					FocusField.this.select(0, getText().length());
				}
				
			});
			
		}
		
		public FocusField(String label, int size) {
			super(label, size);
		}
				
	}
	
}
