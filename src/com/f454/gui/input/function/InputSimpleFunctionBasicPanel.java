package com.f454.gui.input.function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.f454.gui.input.InputDialog;
import com.f454.gui.mainwindow.MainWindow;
import com.fathzer.soft.javaluator.StaticVariableSet;

public class InputSimpleFunctionBasicPanel extends JPanel {
	
	protected JRadioButton yEquals;
	protected JRadioButton xEquals;
	
	protected JTextField input;
	protected JButton ok;
	
	// Constructor accepts a JButton 'ok' as a parameter.
	// This is so that we can enable it and disable it according
	// to the syntax of the inputted expression.
	public InputSimpleFunctionBasicPanel(JButton ok) {
		super();
		
		this.ok = ok;
		ok.setEnabled(false);
		
		input = new JTextField(30);
		
		// set the action listener for the input field to the
		// action listener for the 'ok' button.
		input.addActionListener(ok.getActionListeners()[0]);
		
		ButtonGroup g = new ButtonGroup();
		yEquals = new JRadioButton("y = ", true);
		xEquals = new JRadioButton("x = ", false);
		
		ActionListener l = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				checkSyntaxAndUpdateButton();
			}
			
		};
		
		yEquals.addActionListener(l);
		xEquals.addActionListener(l);
		
		g.add(yEquals);
		g.add(xEquals);
		
		input.getDocument().addDocumentListener(new DocumentListener() {
			
			public void changedUpdate(DocumentEvent e) {
				checkSyntaxAndUpdateButton();
			}
			
			public void removeUpdate(DocumentEvent e) {
				checkSyntaxAndUpdateButton();
			}
			
			public void insertUpdate(DocumentEvent e) {
				checkSyntaxAndUpdateButton();
			}
			
		});
		
		add(yEquals);
		add(xEquals);
		add(input);
		
		InputDialog.requestFocus(input);
	}
	
	private void checkSyntaxAndUpdateButton() {
		MainWindow m = MainWindow.getInstance();
		StaticVariableSet<Double> variables = m.getSliderWindow().getUserVariables();
		
		// If yEquals is selected, acknowledge that a variable 'x' may exist.
		// Likewise, if xEquals is selected, acknowledge that a variable 'y'
		// may exist.
		if(yEquals.isSelected()) {
			variables.set("x", 1D);
		} else {
			variables.set("y", 1D);
		}
		
		ok.setEnabled(InputDialog.checkSyntax(input.getText(), variables));
	}
	
	public String getExpression() {
		return input.getText();
	}
	
	public boolean isyEquals() {
		return yEquals.isSelected();
	}
	
}
