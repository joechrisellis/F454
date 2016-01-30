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
	
	// Automatically assign a random colour if the user chooses not to select
	// one themselves.
	private Color color = randomColor(null);
	
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
	private static Color randomColor(Color mix) {
	    int red = r.nextInt(256);
	    int green = r.nextInt(256);
	    int blue = r.nextInt(256);

	    // mix the color
	    if (mix != null) {
	        red = (red + mix.getRed()) / 2;
	        green = (green + mix.getGreen()) / 2;
	        blue = (blue + mix.getBlue()) / 2;
	    }

	    Color color = new Color(red, green, blue);
	    return color;
	}
	
}
