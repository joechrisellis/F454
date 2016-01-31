package com.f454.gui.about;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.f454.gui.mainwindow.MainWindow;

public class AboutDialog extends JDialog {
	
	public static final String TITLE = "About";
	public static final int HEIGHT = 500;
	public static final int WIDTH = (int) (MainWindow.GOLDEN_RATIO * HEIGHT);
	
	// The string to be shown in the JTextField for the about menu.
	public static final String ABOUT = "Graphing Calculator is a piece of 2D graph plotting software developed by Joe Ellis "
									+  "for the F454 Computing Project 2016. It was originally developed for use by the mathematics "
									+  "department of the South Wolds Academy and Sixth Form.\n\nIt is capable of plotting functions "
									+  "of the form y = f(x) and x = f(y), parametric equations, circles and 2D data.";
	
	private JButton close;
	
	public AboutDialog() {
		super();
		
		setTitle(TITLE);
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		
		close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		
		JTextArea about = new JTextArea(ABOUT);
		
		// enable word wrapping and increase the font size.
		about.setEditable(false);
		about.setLineWrap(true);
		about.setWrapStyleWord(true);
		about.setFont(new Font(null, Font.PLAIN, 20));
		
		JScrollPane scrollPane = new JScrollPane(about);
		
		add(scrollPane);
		add(close, BorderLayout.SOUTH);
		
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}
