package com.f454.gui.circle;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.f454.graph.mathobject.Circle;
import com.f454.gui.mainwindow.MainWindow;

public class InputCircleDialogue extends JDialog {
	
	public static final String TITLE = "Input Circle";
	
	private JTabbedPane tabs;
	private InputCircleBasicPanel basicPanel;
		
	public InputCircleDialogue() {
		super();
		
		setTitle(TITLE);
		setSize(600, 300);
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		
		tabs = new JTabbedPane();
		tabs.addTab("Basic", new InputCircleBasicPanel());
		
		add(tabs);
		
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
		InputCircleDialogue window = new InputCircleDialogue();
		double a = window.basicPanel.getA();
		double b = window.basicPanel.getB();
		double r = window.basicPanel.getR();
		
		MainWindow m = MainWindow.getInstance();
		Circle circle = new Circle("test", a, b, r,
				Color.RED, m.getGraphingPanel().getGraphingEngine().getScalingManager());
		
		return circle;
	}
		
}
