package com.joechrisellis.f454.gui.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.joechrisellis.f454.gui.buttons.AddCircleButton;
import com.joechrisellis.f454.gui.buttons.AddPointButton;
import com.joechrisellis.f454.gui.buttons.AddSimpleFunctionXEqualsButton;
import com.joechrisellis.f454.gui.buttons.AddSimpleFunctionYEqualsButton;

public class MainWindowBottomPanel extends JPanel {
	
	private AddPointButton addPoint;
	private AddSimpleFunctionYEqualsButton addSimpleFunction1;
	private AddSimpleFunctionXEqualsButton addSimpleFunction2;
	private AddCircleButton addCircle;
	
	public MainWindowBottomPanel() {
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		addPoint = new AddPointButton();
		addSimpleFunction1 = new AddSimpleFunctionYEqualsButton();
		addSimpleFunction2 = new AddSimpleFunctionXEqualsButton();
		addCircle = new AddCircleButton();
		
		add(addPoint);
		add(addSimpleFunction1);
		add(addSimpleFunction2);
		add(addCircle);
		
		add(new JSeparator(JSeparator.VERTICAL));
		add(new JButton("test"));
	}
	
}
