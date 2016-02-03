package com.f454.graph.mathobject.basic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.f454.graph.ScalingManager;
import com.f454.graph.mathobject.MathematicalObject;
import com.f454.gui.mainwindow.MainWindow;

/**
 * An abstract class for a mathematical object not based on drawing lines
 * between a set of points.
 * Examples of such mathematical objects are circles and date sets.
 * @author Joe Ellis
 *
 */
public abstract class BasicMathematicalObject extends MathematicalObject {
	
	// When the object is hovered over in the mathematical objects panel, it will
	// be highlighted on the coordinate system. These variables define how thick the
	// stroke will be normally, and how thick the stroke will be when the mathematical
	// object is highlighted.
	protected static final int NORMAL_WIDTH = 3, BOLD_WIDTH = NORMAL_WIDTH * 2;
	
	protected JMenuItem changeLabelMenu;
	protected JMenuItem changeColorMenu;
	protected JMenuItem removeMenu;
	
	public BasicMathematicalObject(String name, String tooltip, Color color, ScalingManager sm) {
		super(name, tooltip, color, sm);
		
		changeLabelMenu = new JMenuItem("Change Label");
		changeLabelMenu.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				changeLabel();
			}
			
		});
		
		changeColorMenu = new JMenuItem("Change Colour");
		changeColorMenu.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				changeColor();
			}
		});
		
		removeMenu = new JMenuItem("Remove");
		removeMenu.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				// remove this mathematical object and refresh
				// the mathematical objects panel.
				remove();
				MainWindow m = MainWindow.getInstance();
				m.getMathPanel().refreshAll();
			}
			
		});
		
		menu.add(changeLabelMenu);
		menu.add(changeColorMenu);
		menu.add(removeMenu);
	}
	
	private void changeLabel() {
		String newLabel = JOptionPane.showInputDialog(null, "Enter a new label: ", "Change Label",
											JOptionPane.QUESTION_MESSAGE);
		newLabel = newLabel.trim();
		
		if(newLabel != null && newLabel.length() >= 1) {
			name = newLabel;
			MainWindow m = MainWindow.getInstance();
			m.getMathPanel().refreshAll();
		}
		
	}
	
	private void changeColor() {
		Color c = JColorChooser.showDialog(null, "Colour", Color.RED);
		if(c != null) {
			color = c;
		}
	}
	
}
