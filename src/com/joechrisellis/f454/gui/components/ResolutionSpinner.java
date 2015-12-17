package com.joechrisellis.f454.gui.components;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ResolutionSpinner extends JSpinner {
	
	public ResolutionSpinner() {
		super(new SpinnerNumberModel(0.01, 0.01, 5, 0.01));
	}
	
}
