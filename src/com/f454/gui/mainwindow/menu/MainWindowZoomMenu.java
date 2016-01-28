package com.f454.gui.mainwindow.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.f454.gui.mainwindow.panel.MainWindowBottomPanel;

public class MainWindowZoomMenu extends JMenu {
	
	private JMenuItem home;
	
	public MainWindowZoomMenu() {
		super("Zoom");
		setMnemonic(KeyEvent.VK_Z);
		
		home = new JMenuItem("Home");
		home.addActionListener(MainWindowBottomPanel.homeListener);
		
		add(home);
	}
	
}
