package com.f454.graph;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.ListIterator;

import com.f454.graph.mathobject.MathematicalObject;
import com.f454.graph.mathobject.special.Axes;
import com.f454.graph.mathobject.special.Key;
import com.f454.gui.mainwindow.MainWindow;
import com.f454.gui.mainwindow.panel.GraphingPanel;
import com.f454.gui.setting.SetResolutionDialog;

/**
 * The engine powering the rendering of mathematical objects.
 * @author Joe Ellis
 *
 */
public class GraphingEngine {
	
	// The graphingPanel object is a reference to the container so we can do
	// things like get the width and height of it, etc.
	private final GraphingPanel graphingPanel;
	private ScalingManager sm;
	
	private double resolution;
	
	// A resizing array of all of the mathematical objects, visible or invisible.
	private ArrayList<MathematicalObject> mathObjects;
	private Key key;
	
	public GraphingEngine(GraphingPanel graphingPanel) {
		this.graphingPanel = graphingPanel;
		sm = new ScalingManager(this);
		resolution = SetResolutionDialog.getOptimumResolution();
		
		// create the list of mathematical objects and add the axes.
		mathObjects = new ArrayList<MathematicalObject>();
		Axes axes = new Axes(sm);
		mathObjects.add(axes);
		
		key = new Key(mathObjects);
		mathObjects.add(key);
		
	}
	

	/**
	 * Renders all of the visible mathematical objects using the parameter g.
	 * @param g The Graphics2D object used for rendering.
	 */
	public void render(Graphics2D g) {
		
		// iterate through all of the mathematical objects...
		ListIterator<MathematicalObject> itr = mathObjects.listIterator();
		while(itr.hasNext()) {
			
			MathematicalObject o = itr.next();
			
			// if the mathematical object o has been flagged as removed, delete
			// it from the list of mathematical objects.
			if(o.isRemoved()) {
				itr.remove();
				continue;
			}
			
			// if o is visible, render it.
			if(o.isVisible()) {
				o.render(g);
			}
			
		}
		
	}
	
	/**
	 * Clears the arraylist of mathematical objects (except for the axes).
	 */
	public void clear() {
		ListIterator<MathematicalObject> itr = mathObjects.listIterator();
		while(itr.hasNext()) {
			
			// if the object isn't the axes, delete it.
			if(!itr.next().isRemovable()) continue;
			else itr.remove();
		}
		
		// refresh the mathematical objects panel GUI element.
		MainWindow m = MainWindow.getInstance();
		m.getMathPanel().refreshAll();
	}
	
	/**
	 * Adds a mathematical object to the arraylist.
	 * @param o The mathematical object to be added.
	 */
	public void addMathObject(MathematicalObject o) {
		mathObjects.add(o);
		MainWindow m = MainWindow.getInstance();
		m.getMathPanel().addMathObject(o);
	}
	
	public GraphingPanel getGraphingPanel() {
		return graphingPanel;
	}
	
	public ScalingManager getScalingManager() {
		return sm;
	}

	public ArrayList<MathematicalObject> getMathObjects() {
		return mathObjects;
	}

	public void setMathObjects(ArrayList<MathematicalObject> mathObjects) {
		this.mathObjects = mathObjects;
	}
	
	public Key getKey() {
		return key;
	}
	
	public double getResolution() {
		return resolution;
	}
	
	/**
	 * Sets a new value for the resolution and updates all of the mathematical
	 * objects to account for the change.
	 * @param resolution
	 */
	public void setResolution(double resolution) {
		this.resolution = resolution;
		
		// iterate through all of the mathematical objects and ask them
		// to reinitialise themselves to account for the change in resolution.
		ListIterator<MathematicalObject> itr = mathObjects.listIterator();
		while(itr.hasNext()) {
			MathematicalObject m = itr.next();
			m.reinit();
		}
	}
	
}
