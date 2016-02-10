package com.f454.gui.input.parametric;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.f454.gui.input.InputDialog;
import com.f454.gui.mainwindow.MainWindow;
import com.fathzer.soft.javaluator.StaticVariableSet;

public class InputParametricEquationBasicPanel extends JPanel {
	
	private JTextField inputExpression1, inputExpression2;
	private JButton ok;
	
	public InputParametricEquationBasicPanel(JButton ok) {
		super();
		
		this.ok = ok;
		ok.setEnabled(false);
		
		inputExpression1 = new JTextField(38);
		inputExpression2 = new JTextField(38);
		
		// set the action listener for the input field to the
		// action listener for the 'ok' button.
		inputExpression1.addActionListener(ok.getActionListeners()[0]);
		inputExpression2.addActionListener(ok.getActionListeners()[0]);
		
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
		MainWindow m = MainWindow.getInstance();
		StaticVariableSet<Double> variables = m.getGraphingPanel().getGraphingEngine().getVariables();
		
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
