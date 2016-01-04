package com.f454.graph;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.ListIterator;

import com.f454.graph.mathobject.MathematicalObject;
import com.f454.graph.mathobject.basic.Axes;
import com.f454.graph.mathobject.basic.DataSet;
import com.f454.gui.mainwindow.GraphingPanel;
import com.f454.gui.mainwindow.MainWindow;

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
	
	private double resolution = 0.5;
	
	// A resizing array of all of the mathematical objects, visible or invisible.
	private ArrayList<MathematicalObject> mathObjects;
	
	public GraphingEngine(GraphingPanel graphingPanel) {
		this.graphingPanel = graphingPanel;
		sm = new ScalingManager(this);
		
		mathObjects = new ArrayList<MathematicalObject>();
		Axes axes = new Axes(sm);
		mathObjects.add(axes);
		
		ArrayList<Point> p = new ArrayList<Point>();
		p.add(new Point(0, 20));
		p.add(new Point(3, 6));
		p.add(new Point(5, 2));
		p.add(new Point(7, 7));
		
		DataSet ds = new DataSet("Data Set", p, Color.GREEN, sm);
		mathObjects.add(ds);
	}
	

	/**
	 * Renders all of the visible mathematical objects using the parameter g.
	 * @param g The Graphics2D object used for rendering.
	 */
	public void render(Graphics2D g) {
		ListIterator<MathematicalObject> itr = mathObjects.listIterator();
		while(itr.hasNext()) {
			
			MathematicalObject o = itr.next();
			
			if(o.isRemoved()) {
				itr.remove();
				continue;
			}
			
			if(o.isVisible()) {
				o.render(g);
			}
			
		}
	}
	
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
	
	public double getResolution() {
		return resolution;
	}

	public void setResolution(double resolution) {
		this.resolution = resolution;
		
		ListIterator<MathematicalObject> itr = mathObjects.listIterator();
		while(itr.hasNext()) {
			MathematicalObject m = itr.next();
			m.reinit();
		}
	}
	
}
