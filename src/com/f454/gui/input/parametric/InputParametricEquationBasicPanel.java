package com.f454.gui.input.parametric;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.f454.gui.input.InputDialog;
import com.fathzer.soft.javaluator.StaticVariableSet;

public class InputParametricEquationBasicPanel extends JPanel {
	
	private JTextField inputExpression1, inputExpression2;
	private JButton ok;
	
	public InputParametricEquationBasicPanel(JButton ok) {
		super();
		
		inputExpression1 = new JTextField(38);
		inputExpression2 = new JTextField(38);
		this.ok = ok;
		ok.setEnabled(false);
		
		DocumentListener l = new DocumentListener() {
			
			public void removeUpdate(DocumentEvent e) {
				checkSyntaxAndUpdateButton();
			}
			
			public void insertUpdate(DocumentEvent e) {
				checkSyntaxAndUpdateButton();
			}
			
			public void changedUpdate(DocumentEvent e) {
				checkSyntaxAndUpdateButton();
			}
			
		};
		
		inputExpression1.getDocument().addDocumentListener(l);
		inputExpression2.getDocument().addDocumentListener(l);
		
		add(new JLabel("x(t) = "));
		add(inputExpression1);
		add(new JLabel("y(t) = "));
		add(inputExpression2);
		
		InputDialog.requestFocus(inputExpression1);
	}
	
	private void checkSyntaxAndUpdateButton() {
		StaticVariableSet<Double> variables = new StaticVariableSet<Double>();
		
		// Acknowledge that a variable 't' exists.
		variables.set("t", 1D);
		ok.setEnabled(InputDialog.checkSyntax(inputExpression1.getText(), variables)
				   && InputDialog.checkSyntax(inputExpression2.getText(), variables));
	}
	
	public String getExpression1() {
		return inputExpression1.getText();
	}
	
	public String getExpression2() {
		return inputExpression2.getText();
	}

}
