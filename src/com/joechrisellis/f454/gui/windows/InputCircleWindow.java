package com.joechrisellis.f454.gui.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.joechrisellis.f454.graphing.mathobjects.Circle;

public class InputCircleWindow extends JDialog {
	
	public static final String TITLE = "Input Circle";
	public static final String CARTESIAN_EQUATION = 
			"<html><i>(x %+d)<sup>2</sup> + (y %+d)<sup>2</sup> = %d<sup>2</sup></i></html>";
	
	private JLabel label;
	private JSpinner a, b, r;
	
	private SpinnerChange change;
	
	public InputCircleWindow() {
		super();
		
		setTitle(TITLE);
		setSize(600, 300);
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		label = new JLabel(String.format(CARTESIAN_EQUATION, 0, 0, 1), JLabel.CENTER);
		label.setFont(new Font("Sans", 0, 40));
		
		JPanel panel = new JPanel();
		a = new JSpinner();
		b = new JSpinner();
		r = new JSpinner();
		r.setValue(1);
		
		change = new SpinnerChange();
		
		a.addChangeListener(change);
		b.addChangeListener(change);
		r.addChangeListener(change);
		panel.add(new JLabel("a: "));
		panel.add(a);
		panel.add(new JLabel("b: "));
		panel.add(b);
		panel.add(new JLabel("r: "));
		panel.add(r);
		
		JButton ok= new JButton("OK");
		ok.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		
		panel.add(ok);
		
		add(label);
		add(panel, BorderLayout.SOUTH);
		
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * Raises a dialogue allowing the user to input the circle and creates
	 * a circle object from the information that they have inputed. 
	 * @return Circle A circle object created using the inputed information.
	 */
	public static Circle getCircle() {
		InputCircleWindow window = new InputCircleWindow();
		int a = (int) (window.a.getValue());
		int b = (int) (window.b.getValue());
		int r = (int) (window.r.getValue());
		
		MainWindow m = MainWindow.getInstance();
		Circle circle = new Circle("test", a, b, r,
				Color.RED, m.getGraphingPanel().getGraphingEngine().getScalingManager());
		
		return circle;
	}
	
	private class SpinnerChange implements ChangeListener {

		public void stateChanged(ChangeEvent e) {
			label.setText(String.format(CARTESIAN_EQUATION, a.getValue(), b.getValue(), r.getValue()));
		}
		
	}
	
}
