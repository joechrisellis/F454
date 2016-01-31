package com.f454.graph.mathobject.basic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import com.f454.graph.ScalingManager;
import com.f454.graph.mathobject.MathematicalObject;
import com.f454.gui.input.InputDialog;
import com.f454.gui.mainwindow.MainWindow;

public abstract class BasicMathematicalObject extends MathematicalObject {
	
	protected static final int NORMAL_WIDTH = 2, BOLD_WIDTH = NORMAL_WIDTH * 2;
	
	protected JMenuItem removeMenu;
	protected JMenuItem editMenu;
	
	public BasicMathematicalObject(String name, String tooltip, Color color, ScalingManager sm) {
		super(name, tooltip, color, sm);
		
		editMenu = new JMenuItem("Edit");
		editMenu.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				edit();
			}
			
		});
		
		removeMenu = new JMenuItem("Remove");
		removeMenu.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				remove();
				MainWindow m = MainWindow.getInstance();
				m.getMathPanel().refreshAll();
			}
			
		});
		
		menu.add(editMenu);
		menu.add(removeMenu);
	}
	
	private void edit() {
		InputDialog.edit(this);
	}
	
}
