package com.f454.gui.mainwindow.panel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

import com.f454.graph.mathobject.MathematicalObject;

public class MathematicalObjectCheckbox extends JCheckBox implements ItemListener {
	
	private MathematicalObject mathObject;
	
	public MathematicalObjectCheckbox(String text, MathematicalObject mathObject) {
		super(text, mathObject.isVisible());
		this.mathObject = mathObject;
		
		setToolTipText(mathObject.getExpression());
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
	
}
