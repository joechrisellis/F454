package com.joechrisellis.f454.gui.components.panels;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.joechrisellis.f454.gui.windows.InputSimpleFunctionWindow1;
import com.joechrisellis.f454.gui.windows.InputSimpleFunctionWindow2;
import com.joechrisellis.f454.gui.windows.SetResolutionDialogue;

public class MainWindowBottomPanel extends JPanel {
	
	private JButton addPoint;
	private JButton addSimpleFunction1;
	private JButton addSimpleFunction2;
	private JButton addCircle;
	private JButton raiseSettings;
	
	public MainWindowBottomPanel() {
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		addPoint = new JButton("(x, y)");
		addPoint.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO: raise input points dialogue.
			}
			
		});
		
		addSimpleFunction1 = new JButton("y = f(x)");
		addSimpleFunction1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new InputSimpleFunctionWindow1();
			}
			
		});
		
		addSimpleFunction2 = new JButton("x = f(y)");
		addSimpleFunction2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new InputSimpleFunctionWindow2();
			}
		});
		
		addCircle = new JButton("Circle");
		addCircle.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO: raise circle dialogue.
			}
			
		});
		
		raiseSettings = new JButton("Settings");
		raiseSettings.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new SetResolutionDialogue();
			}
			
		});
		
		add(addPoint);
		add(addSimpleFunction1);
		add(addSimpleFunction2);
		add(addCircle);
		
		add(new JSeparator(JSeparator.VERTICAL));
		add(raiseSettings);
	}
	
}
