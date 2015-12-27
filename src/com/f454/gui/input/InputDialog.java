package com.f454.gui.input;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.f454.gui.mainwindow.MainWindow;

public class InputDialog extends JDialog {
	
	protected JTabbedPane tabs;
	
	public InputDialog(String title, int w) {
		super();
		setModal(true);
		
		setTitle(title);
		setSize(w, (int) (w * Math.pow(MainWindow.GOLDEN_RATIO, -1)));
		setResizable(false);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		tabs = new JTabbedPane();
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		
		add(ok, BorderLayout.SOUTH);
	}
	
}
