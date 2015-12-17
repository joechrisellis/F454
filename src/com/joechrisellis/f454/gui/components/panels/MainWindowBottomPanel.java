package com.joechrisellis.f454.gui.components.panels;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.joechrisellis.f454.gui.components.buttons.AddCircleButton;
import com.joechrisellis.f454.gui.components.buttons.AddPointButton;
import com.joechrisellis.f454.gui.components.buttons.AddSimpleFunctionXEqualsButton;
import com.joechrisellis.f454.gui.components.buttons.AddSimpleFunctionYEqualsButton;
import com.joechrisellis.f454.gui.components.buttons.RaiseSettingsButton;

public class MainWindowBottomPanel extends JPanel {
	
	private AddPointButton addPoint;
	private AddSimpleFunctionYEqualsButton addSimpleFunction1;
	private AddSimpleFunctionXEqualsButton addSimpleFunction2;
	private AddCircleButton addCircle;
	private RaiseSettingsButton raiseSettings;
	
	public MainWindowBottomPanel() {
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		addPoint = new AddPointButton();
		addSimpleFunction1 = new AddSimpleFunctionYEqualsButton();
		addSimpleFunction2 = new AddSimpleFunctionXEqualsButton();
		addCircle = new AddCircleButton();
		raiseSettings = new RaiseSettingsButton();
		
		add(addPoint);
		add(addSimpleFunction1);
		add(addSimpleFunction2);
		add(addCircle);
		
		add(new JSeparator(JSeparator.VERTICAL));
		add(raiseSettings);
	}
	
}
