package com.f454.gui.input;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.f454.gui.mainwindow.MainWindow;

public class InputDialog extends JDialog {
	
	protected JTabbedPane tabs;
	private boolean cancelled;
	
	protected InputDialog(String title, int w) {
		super();
		setModal(true);
		
		setTitle(title);
		setSize(w, (int) (w * Math.pow(MainWindow.GOLDEN_RATIO, -1)));
		setResizable(false);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		tabs = new JTabbedPane();
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				cancelled = true;
				dispose();
			}
			
		});
		
		panel.add(ok);
		panel.add(cancel);
		
		add(panel, BorderLayout.SOUTH);
	}
	
	public boolean wasCancelled() {
		return cancelled;
	}
	
}
