package com.joechrisellis.f454.gui.components.panels;

import java.awt.Dimension;
import java.util.ListIterator;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.joechrisellis.f454.graphing.mathobjects.MathematicalObject;
import com.joechrisellis.f454.gui.components.MathematicalObjectCheckbox;
import com.joechrisellis.f454.gui.windows.MainWindow;

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
		ListIterator<MathematicalObject> itr =
			MainWindow.main.getGraphingPanel().getGraphingEngine().getMathObjects().listIterator();
		while(itr.hasNext()) {
			MathematicalObject o = itr.next();
			MathematicalObjectCheckbox c = new MathematicalObjectCheckbox(o.getName(), o);
			panel.add(c);
		}
	}
	
}
