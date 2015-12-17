package com.joechrisellis.f454.graphing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.ListIterator;

import com.joechrisellis.f454.graphing.mathobjects.Axes;
import com.joechrisellis.f454.graphing.mathobjects.DataSet;
import com.joechrisellis.f454.graphing.mathobjects.MathematicalObject;
import com.joechrisellis.f454.graphing.mathobjects.SimpleFunctionXEquals;
import com.joechrisellis.f454.graphing.mathobjects.SimpleFunctionYEquals;
import com.joechrisellis.f454.gui.components.panels.GraphingPanel;

public class GraphingEngine {
	
	// The graphingPanel object is a reference to the container so we can do
	// things like get the width and height of it, etc.
	private final GraphingPanel graphingPanel;
	private ScalingManager scalingManager;
	private double resolution;
	
	private ArrayList<MathematicalObject> mathObjects;
	
	public GraphingEngine(GraphingPanel graphingPanel) {
		this.graphingPanel = graphingPanel;
		scalingManager = new ScalingManager(this);
		resolution = 0.5D;
		
		mathObjects = new ArrayList<MathematicalObject>();
		Axes axes = new Axes(this);
		mathObjects.add(axes);
		
		mathObjects.add(new SimpleFunctionYEquals("test", "x^2", Color.RED, this));
		mathObjects.add(new SimpleFunctionXEquals("test 2", "cos(x)", Color.BLUE, this));
		
		ArrayList<Point> p = new ArrayList<Point>();
		p.add(new Point(10, 20));
		p.add(new Point(3, 6));
		p.add(new Point(5, 2));
		p.add(new Point(7, 7));
		
		DataSet ds = new DataSet("Data Set", p, Color.GREEN, this);
		mathObjects.add(ds);
	}
	
	public void render(Graphics2D g) {
		// render all of the mathematical objects in the arraylist.
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
		return scalingManager;
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
	}

}
