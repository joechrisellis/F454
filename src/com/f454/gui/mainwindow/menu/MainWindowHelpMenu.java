package com.f454.gui.mainwindow.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.f454.gui.about.AboutDialog;
import com.f454.gui.tip.TipOfTheDayDialog;

public class MainWindowHelpMenu extends JMenu {
	
	private JMenuItem tipOfTheDay;
	private JMenuItem about;
	
	public MainWindowHelpMenu() {
		super("Help");
		setMnemonic(KeyEvent.VK_H);
		
		tipOfTheDay = new JMenuItem("Show Tip of the Day");
		tipOfTheDay.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// True passed as an argument here because we're explicitly raising
				// the dialog.
				new TipOfTheDayDialog(true);
			}
			
		});
		
		about = new JMenuItem("About");
		about.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new AboutDialog();
			}
			
		});
		
		add(tipOfTheDay);
		add(about);
	}
	
}
