package com.f454.gui.input;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NameAndColorPanel extends JPanel {
	
	private JTextField label;
	private JButton changeColor;
	private Color color = randomColor();
	
	public NameAndColorPanel(String initialText) {
		super();
		setLayout(new GridLayout(2, 2));
		
		label = new JTextField(initialText);
		changeColor = new JButton("Change Colour");
		changeColor.setForeground(color);
		
		changeColor.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				color = JColorChooser.showDialog(null, "Circle Color", Color.RED);
				changeColor.setForeground(color);
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
	
	private static Random r = new Random();
	private static Color randomColor() {
		float hue = r.nextFloat();
		// Saturation between 0.1 and 0.3
		float saturation = (r.nextInt(2000) + 1000) / 10000f;
		float luminance = 0.9f;
		Color color = Color.getHSBColor(hue, saturation, luminance);
		
		return color;
	}
	
}
