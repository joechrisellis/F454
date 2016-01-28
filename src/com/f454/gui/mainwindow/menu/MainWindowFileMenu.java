package com.f454.gui.mainwindow.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.f454.gui.mainwindow.MainWindow;

public class MainWindowFileMenu extends JMenu {

	private JMenuItem export;
	private JMenuItem quit;
	
	public MainWindowFileMenu() {
		super("File");
		setMnemonic(KeyEvent.VK_F);
		
		export = new JMenuItem("Export");
		
		quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				MainWindow m = MainWindow.getInstance();
				m.stop();
			}
			
		});
		
		add(export);
		add(quit);
	}
	
}
