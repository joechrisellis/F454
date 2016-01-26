package com.f454.gui.input.data;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputDataAdvancedPanel extends JPanel {

	private JTextField label;
	private JButton changeColor;
	private Color color = Color.RED;
	
	public InputDataAdvancedPanel() {
		super();
		setLayout(new GridLayout(2, 2));
		
		label = new JTextField("Data Set");
		changeColor = new JButton("Change Colour");
		changeColor.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				color = JColorChooser.showDialog(null, "Circle Color", Color.RED);
			}
			
		});
		
		add(new JLabel("Label: "));
		add(label);
		
		add(new JLabel("Color: "));
		add(changeColor);
		
	}
	
	public String getLabel() {
		return label.getText();
	}
	
	public Color getChosenColor() {
		return color;
	}
	
}
