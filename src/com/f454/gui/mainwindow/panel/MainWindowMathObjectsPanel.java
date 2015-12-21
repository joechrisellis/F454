package com.f454.gui.mainwindow.panel;

import java.awt.Dimension;
import java.util.ListIterator;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.f454.graph.mathobject.MathematicalObject;
import com.f454.gui.mainwindow.MainWindow;

public class MainWindowMathObjectsPanel extends JScrollPane {
	
	private JPanel panel;
	
	public MainWindowMathObjectsPanel() {
		super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // boxlayout for list view
		
		initListItems();
		
		// Set the panel as part of the JScrollPane and set the size of pane
		// equal to 1/5 of the width of the main window.
		setViewportView(panel);
		setPreferredSize(new Dimension(MainWindow.WIDTH / 5, 0));
	}
	
	public void initListItems() {
		MainWindow m = MainWindow.getInstance();
		ListIterator<MathematicalObject> itr =
			m.getGraphingPanel().getGraphingEngine().getMathObjects().listIterator();
		while(itr.hasNext()) {
			MathematicalObject o = itr.next();
			MathematicalObjectCheckbox c = new MathematicalObjectCheckbox(o.getName(), o);
			panel.add(c);
		}
	}
	
	public void addMathObject(MathematicalObject o) {
		MathematicalObjectCheckbox c = new MathematicalObjectCheckbox(o.getName(), o);
		panel.add(c);
		revalidate();
		
		// not entirely sure if repaint() is necessary here but I suppose
		// it can't hurt.
		repaint();
	}
	
}
