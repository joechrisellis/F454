package com.f454.gui.input;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.f454.graph.mathobject.basic.BasicMathematicalObject;
import com.f454.graph.mathobject.basic.Circle;
import com.f454.graph.mathobject.basic.DataSet;
import com.f454.graph.mathobject.basic.constructed.ParametricEquation;
import com.f454.graph.mathobject.basic.constructed.SimpleFunction;
import com.f454.gui.input.function.InputSimpleFunctionDialog;
import com.f454.gui.mainwindow.MainWindow;
import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;

/**
 * Framework for the input dialogs used to input mathematical objects.
 * @author Joe Ellis
 *
 */
public abstract class InputDialog extends JDialog {
	
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
				
				// required because sometimes the user will press enter
				// to finalise their input, and the event will fire even
				// if the JButton is disabled because of invalid syntax.
				// to be safe, we check whether or not the button is enabled
				// (implying that the syntax is correct) before going ahead with
				// anything.
				if(!ok.isEnabled()) return;
				
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
	
	public static void edit(BasicMathematicalObject o) {
		if(o instanceof SimpleFunction) {
			try {
				InputSimpleFunctionDialog.editFunction((SimpleFunction) (o));
			} catch (InputCancelledException e) {}
		} else if(o instanceof DataSet) {
			
		} else if(o instanceof ParametricEquation) {
			
		} else if(o instanceof Circle) {
			
		}
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
			// If an error is raised, it means that the syntax was incorrect
			// so return false.
			return false;
		}
	}
	
	/**
	 * Requests focus to a particular component.
	 * @param component The component to focus.
	 */
	public static void requestFocus(final JComponent component) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				component.requestFocus();
			}
			
		});
	}
	
}
