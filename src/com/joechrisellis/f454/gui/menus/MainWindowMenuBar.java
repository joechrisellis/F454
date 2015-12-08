package com.joechrisellis.f454.gui.menus;

import javax.swing.JMenuBar;

public class MainWindowMenuBar extends JMenuBar {
	
	private MainWindowFileMenu file;
	private MainWindowEditMenu edit;
	private MainWindowFunctionMenu function;
	private MainWindowZoomMenu zoom;
	private MainWindowHelpMenu help;
	
	public MainWindowMenuBar() {
		file = new MainWindowFileMenu();
		edit = new MainWindowEditMenu();
		function = new MainWindowFunctionMenu();
		zoom = new MainWindowZoomMenu();
		help = new MainWindowHelpMenu();
		
		add(file);
		add(edit);
		add(function);
		add(zoom);
		add(help);
	}
	
}
