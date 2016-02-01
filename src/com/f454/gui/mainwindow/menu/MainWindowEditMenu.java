package com.f454.gui.mainwindow.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.f454.gui.mainwindow.MainWindow;

public class MainWindowEditMenu extends JMenu {
	
	private JMenuItem clear;
	
	public MainWindowEditMenu() {
		super("Edit");
		setMnemonic(KeyEvent.VK_E);
		
		clear = new JMenuItem("Clear");
		clear.setToolTipText("Delete all of the mathematical objects.");
		
		clear.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				MainWindow m = MainWindow.getInstance();
				m.getGraphingPanel().getGraphingEngine().clear();
			}
			
		});
		
		add(clear);
	}
	
}
