package com.joechrisellis.f454.gui.components;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

import com.joechrisellis.f454.graphing.mathobjects.MathematicalObject;

public class MathematicalObjectCheckbox extends JCheckBox implements ItemListener {
	
	private MathematicalObject mathObject;
	
	public MathematicalObjectCheckbox(String text, MathematicalObject mathObject) {
		super(text, mathObject.isVisible());
		this.mathObject = mathObject;
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
