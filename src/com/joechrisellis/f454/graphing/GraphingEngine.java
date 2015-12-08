package com.joechrisellis.f454.graphing;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.ListIterator;

import com.joechrisellis.f454.graphing.mathobjects.Axes;
import com.joechrisellis.f454.graphing.mathobjects.MathematicalObject;
import com.joechrisellis.f454.graphing.mathobjects.SimpleFunctionYEquals;
import com.joechrisellis.f454.gui.panels.GraphingPanel;

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
		resolution = 0.1D;
		
		mathObjects = new ArrayList<MathematicalObject>();
		Axes axes = new Axes(this);
		mathObjects.add(axes);
		
		mathObjects.add(new SimpleFunctionYEquals("test", this));
	}
	
	public void render(Graphics2D g) {
		// render all of the mathematical objects in the arraylist.
		ListIterator<MathematicalObject> itr = mathObjects.listIterator();
		while(itr.hasNext()) {
			MathematicalObject o = itr.next();
			o.render(g);
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
