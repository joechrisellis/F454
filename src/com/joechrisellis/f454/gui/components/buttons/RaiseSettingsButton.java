package com.joechrisellis.f454.gui.components.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.joechrisellis.f454.gui.windows.SetResolutionDialogue;

public class RaiseSettingsButton extends JButton implements ActionListener {

	private final String TEXT = "Resolution Settings";
	
	public RaiseSettingsButton() {
		setText(TEXT);
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		new SetResolutionDialogue(SetResolutionDialogue.TITLE,
				SetResolutionDialogue.WIDTH, SetResolutionDialogue.HEIGHT);
	}

	
}
