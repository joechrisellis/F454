package com.f454.gui.mainwindow.panel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBox;

import com.f454.graph.mathobject.MathematicalObject;

public class MathematicalObjectCheckbox extends JCheckBox implements MouseListener, ItemListener {
	
	private MathematicalObject mathObject;
	
	public MathematicalObjectCheckbox(String text, MathematicalObject mathObject) {
		super(text, mathObject.isVisible());
		this.mathObject = mathObject;
		
		setComponentPopupMenu(mathObject.getMenu());
		
		setToolTipText(mathObject.getExpression());
		addMouseListener(this);
		addItemListener(this);
	}
	
	public MathematicalObject getMathObject() {
		return mathObject;
	}

	public void setMathObject(MathematicalObject mathObject) {
		this.mathObject = mathObject;
	}

	public void itemStateChanged(ItemEvent e) {
		boolean visible = e.getStateChange() == ItemEvent.SELECTED;
		mathObject.setVisible(visible);
	}
	
	
	public void mouseEntered(MouseEvent e) {
		mathObject.setHovered(true);
	}
	
	public void mouseExited(MouseEvent e) {
		mathObject.setHovered(false);
	}
	
	// all unused.
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
}
