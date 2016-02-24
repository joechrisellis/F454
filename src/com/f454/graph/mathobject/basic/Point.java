package com.f454.graph.mathobject.basic;

import java.awt.Color;
import java.awt.Graphics2D;

import com.f454.graph.ScalingManager;

public class Point {
	
	public double x, y;
	
	// This boolean is used to determine whether a line should be drawn from
	// the previous point to this point when working with constructed mathematical objects.
	// If this is true, do NOT draw a line from this point to the next in the list.
	// If this is false, continue as usual.
	// It is required for applications such as applying a range to a function. :)
	public boolean outOfRange;
	
	public Point(double x, double y) {
		this(x, y, false);
	}
	
	public Point(double x, double y, boolean outOfRange) {
		this.x = x;
		this.y = y;
		this.outOfRange = outOfRange;
	}
	
	public static void renderPoint(Graphics2D g, ScalingManager sm, Point p, int r, boolean labels, Color color) {
		g.setColor(color);
		double[] xy = sm.getFinalisedXandY(p.x, p.y);
		
		g.fillOval((int) (xy[0] - r / 2), (int) (xy[1] - r / 2), r, r);

		if(labels) {
			g.drawString(String.format("(%.2f, %.2f)", p.x, p.y), (int) (xy[0] + 3), (int) (xy[1] - 3));
		}
	}
	
}
