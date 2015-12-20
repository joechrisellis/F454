package com.joechrisellis.f454.graphing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.ListIterator;

import com.joechrisellis.f454.graphing.mathobjects.Axes;
import com.joechrisellis.f454.graphing.mathobjects.Circle;
import com.joechrisellis.f454.graphing.mathobjects.DataSet;
import com.joechrisellis.f454.graphing.mathobjects.MathematicalObject;
import com.joechrisellis.f454.graphing.mathobjects.SimpleFunctionXEquals;
import com.joechrisellis.f454.graphing.mathobjects.SimpleFunctionYEquals;
import com.joechrisellis.f454.gui.components.panels.GraphingPanel;

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
	
	// A resizing array of all of the mathematical objects, visible or invisible.
	private ArrayList<MathematicalObject> mathObjects;
	
	public GraphingEngine(GraphingPanel graphingPanel) {
		this.graphingPanel = graphingPanel;
		sm = new ScalingManager(this);
		
		mathObjects = new ArrayList<MathematicalObject>();
		Axes axes = new Axes(sm);
		mathObjects.add(axes);
		
		// Create a few test objects.
		mathObjects.add(new SimpleFunctionYEquals("test", "x^2", Color.RED, sm));
		mathObjects.add(new SimpleFunctionXEquals("test 2", "cos(x)", Color.BLUE, sm));
		
		ArrayList<Point> p = new ArrayList<Point>();
		p.add(new Point(10, 20));
		p.add(new Point(3, 6));
		p.add(new Point(5, 2));
		p.add(new Point(7, 7));
		
		DataSet ds = new DataSet("Data Set", p, Color.GREEN, sm);
		mathObjects.add(ds);
		
		mathObjects.add(new Circle("Circle", 10, 20, 5, Color.PINK, sm));
	}
	

	/**
	 * Renders all of the visible mathematical objects using the parameter g.
	 * @param g The Graphics2D object used for rendering.
	 */
	public void render(Graphics2D g) {
		ListIterator<MathematicalObject> itr = mathObjects.listIterator();
		while(itr.hasNext()) {
			
			MathematicalObject o = itr.next();
			if(o.isVisible()) {
				o.render(g);
			}
			
		}
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
	
}
