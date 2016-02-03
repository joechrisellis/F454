package com.f454.gui.mainwindow.panel;

import java.awt.Dimension;
import java.util.ListIterator;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.f454.graph.mathobject.MathematicalObject;
import com.f454.gui.mainwindow.MainWindow;

/**
 * The left-hand side of the split pane in the main window. This GUI component
 * is a list of all of the mathematical objects on the graphing panel and is an
 * interface for the user to manipulate them. From this, the user can toggle the
 * visibility of a mathematical object as well as raise a right click menu, offering
 * even more control. 
 * @author Joe Ellis
 *
 */
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
	
	/**
	 * Initialises the list of mathematical objects. This method iterates through
	 * the graphing engine's arraylist of mathematical objects and creates a checkbox
	 * for each one. IF
	 */
	private void initListItems() {		
		MainWindow m = MainWindow.getInstance();
		ListIterator<MathematicalObject> itr =
			m.getGraphingPanel().getGraphingEngine().getMathObjects().listIterator();
		
		while(itr.hasNext()) {
			MathematicalObject o = itr.next();
			
			// if the object has been removed, don't re-add it to the list.
			// we have to include this guard clause since the rendering and
			// gui are not on the same thread; we must be especially careful.
			// the GraphingEngine will take care of removing the object from
			// the list in the future.
			if(o.isRemoved()) continue;
			
			MathematicalObjectCheckbox c = new MathematicalObjectCheckbox(o.getLabel(), o);
			panel.add(c);
		}
		
	}
	
	/**
	 * Adds a mathematical object to the list and refreshes it.
	 * @param o The mathematical object to add.
	 */
	public void addMathObject(MathematicalObject o) {
		MathematicalObjectCheckbox c = new MathematicalObjectCheckbox(o.getLabel(), o);
		panel.add(c);
		refresh();
	}
	
	/**
	 * Update the panel.
	 */
	public void refresh() {
		panel.revalidate();
		panel.repaint();
	}
	
	/**
	 * Reinitialises the entire list of mathematical objects. This is necessary
	 * for when we are doing more involved processes than just adding a mathematical
	 * object to the list. For instance, if we are to remove a mathematical object, an
	 * element in the GUI has to be removed. The easiest way to do this is to remove
	 * the contents of the panel, reiterate over all of the mathematical objects and
	 * add what is still remaining.
	 */
	public void refreshAll() {
		panel.removeAll();
		initListItems();
		refresh();
	}
	
}
