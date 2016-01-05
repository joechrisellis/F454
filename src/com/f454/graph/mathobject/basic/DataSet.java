package com.f454.graph.mathobject.basic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.ListIterator;

import com.f454.graph.ScalingManager;

public class DataSet extends BasicMathematicalObject {
	
	private static final String TOOLTIP = "%d points";
	
	private ArrayList<Point> points;
	private static final int RADIUS_NORMAL = 7, RADIUS_BOLD = (int) (RADIUS_NORMAL * 1.5);
	
	public DataSet(String name, ArrayList<Point> points, Color color, ScalingManager sm) {
		super(name, String.format(TOOLTIP, points.size()), color, sm);
		this.points = points;
		
		menu.add(removeMenu);
	}
	
	public void render(Graphics2D g) {
		g.setColor(color);
		
		int r = hovered ? RADIUS_BOLD : RADIUS_NORMAL;
		
		ListIterator<Point> itr = points.listIterator();
		while(itr.hasNext()) {
			Point p = itr.next();
			double[] xy = sm.getCentredXandY(p.getX(), p.getY());
			
			g.fillOval((int) (xy[0] - r / 2), (int) (xy[1] - r / 2), r, r);
			g.drawString(String.format("(%.2f, %.2f)", p.getX(), p.getY()), (int) (xy[0]), (int) (xy[1]));
		}
	}
	
	public void addPoint(Point p) {
		points.add(p);
	}
	
}
