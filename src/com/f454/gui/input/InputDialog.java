package com.f454.gui.input;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.f454.gui.mainwindow.MainWindow;
import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;

public class InputDialog extends JDialog {
	
	protected JTabbedPane tabs;
	private boolean okPressed;
	
	protected JButton ok;
	
	protected InputDialog(String title, int w) {
		super();
		setModal(true);
		
		setTitle(title);
		setSize(w, (int) (w * Math.pow(MainWindow.GOLDEN_RATIO, -1)));
		setResizable(false);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		tabs = new JTabbedPane();
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				okPressed = true;
				dispose();
			}
			
		});
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		
		panel.add(ok);
		panel.add(cancel);
		
		add(panel, BorderLayout.SOUTH);
	}
	
	public boolean wasCancelled() {
		return !okPressed;
	}
	
	private static DoubleEvaluator validator = new DoubleEvaluator();
	
	/**
	 * Checks the syntax of an expression.
	 * @param expression The expression to be checked.
	 * @param variables Any variables to be acknowledged.
	 * @return A boolean representing whether or not the syntax of expression is valid.
	 */
	public static boolean checkSyntax(String expression, StaticVariableSet<Double> variables) {
		try {
			validator.evaluate(expression, variables);
			return true;
		} catch(IllegalArgumentException e) {
			return false;
		}
	}
	
}
