package com.joechrisellis.f454.graphing.mathobjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.ListIterator;

import com.joechrisellis.f454.graphing.GraphingEngine;
import com.joechrisellis.f454.graphing.ScalingManager;

public class DataSet extends MathematicalObject {
	
	private ArrayList<Point> points;
	private static final int RADIUS = 7;
	
	public DataSet(String name, ArrayList<Point> points, Color color, GraphingEngine graphingEngine) {
		super(name, color, graphingEngine);
		this.points = points;
	}
	
	public void render(Graphics2D g) {
		g.setColor(color);
		ScalingManager sm = graphingEngine.getScalingManager();
		
		ListIterator<Point> itr = points.listIterator();
		while(itr.hasNext()) {
			Point p = itr.next();
			double[] xy = sm.getCentredXandY(p.getX(), p.getY());
			g.fillOval((int) (xy[0] - RADIUS / 2), (int) (xy[1] - RADIUS / 2), RADIUS, RADIUS);
			g.drawString(String.format("(%.2f, %.2f)", p.getX(), p.getY()), (int) (xy[0]), (int) (xy[1]));
		}
	}
	
	public void addPoint(Point p) {
		points.add(p);
	}
	
}
