package com.f454.gui.mainwindow.panel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBox;

import com.f454.graph.mathobject.MathematicalObject;

/**
 * A checkbox appearing in the MainWindowMathObjectPanel. Toggling it
 * toggles the visibility of the mathematical object in question, hovering over
 * it highlights the mathematical object, and right clicking it raises a context
 * menu.
 * @author joe
 *
 */
public class MathematicalObjectCheckbox extends JCheckBox implements MouseListener, ItemListener {
	
	// Keep a reference to the mathematical object that this checkbox is related to.
	private MathematicalObject mathObject;
	
	public MathematicalObjectCheckbox(String text, MathematicalObject mathObject) {
		super(text, mathObject.isVisible());
		this.mathObject = mathObject;
		
		// Set the context menu to the menu provided by the mathematical object.
		setComponentPopupMenu(mathObject.getMenu());
		
		// Set the tool tip to the 'expression' of the mathematical object.
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

	/**
	 * When the state of the checkbox changes, determine whether or not
	 * it is checked or unchecked and update the visibility of the mathematical
	 * object accordingly.
	 */
	public void itemStateChanged(ItemEvent e) {
		boolean visible = e.getStateChange() == ItemEvent.SELECTED;
		mathObject.setVisible(visible);
	}
	
	/**
	 * When the checkbox is hovered over, set the 'hovered' flag in the
	 * mathematical object to true.
	 */
	public void mouseEntered(MouseEvent e) {
		mathObject.setHovered(true);
	}
	
	/**
	 * When the checkbox is no longer hovered over, set the 'hovered' flag
	 * in the mathematical object to false.
	 */
	public void mouseExited(MouseEvent e) {
		mathObject.setHovered(false);
	}
	
	// all unused.
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
}
