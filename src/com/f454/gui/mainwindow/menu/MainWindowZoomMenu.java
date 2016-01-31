package com.f454.gui.mainwindow.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.f454.graph.mathobject.special.Axes;
import com.f454.gui.mainwindow.panel.MainWindowBottomPanel;

public class MainWindowZoomMenu extends JMenu {
	
	private JMenuItem home;
	private JMenuItem axesScaling;
	
	public MainWindowZoomMenu() {
		super("Zoom");
		setMnemonic(KeyEvent.VK_Z);
		
		home = new JMenuItem("Home");
		home.addActionListener(MainWindowBottomPanel.homeListener);
		home.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
		
		axesScaling = new JMenuItem("Show Scaling Settings");
		axesScaling.addActionListener(Axes.showScalingListener);
		
		add(home);
		add(axesScaling);
	}
	
}
