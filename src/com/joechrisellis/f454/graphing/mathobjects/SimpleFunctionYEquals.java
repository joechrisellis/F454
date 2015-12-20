package com.joechrisellis.f454.graphing.mathobjects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import com.joechrisellis.f454.graphing.ScalingManager;

public class SimpleFunctionYEquals extends MathematicalObject {
	
	protected String expression;
	
	protected boolean hasDomain, hasRange;
	protected double domainLBound, domainUBound;
	protected double rangeLBound, rangeUBound;
	
	public SimpleFunctionYEquals(String name, String expression, Color color,
									ScalingManager sm) {
		super(name, color, sm);
		this.expression = expression;
	}

	public void render(Graphics2D g) {
		g.setColor(color);
		
		// Set the line width to 2 pixels wide; looks prettier.
		g.setStroke(new BasicStroke(2));
		
		int w = sm.getCentre()[0];
		double lower = hasDomain ? domainLBound : -w;
		double upper = hasDomain ? domainUBound : w;
		
		double prevY = 0;
		
		// Iterate through all of the values of x.
		for(double x = lower; x < upper; x += sm.getResolution()) {
			
			// TODO: Replace sin(x) with the actual user inputted expression.
			double newY = Math.sin(x);
			
			// Create two arrays:
			// * p1 representing the previous point.
			// * p2 representing the current point.
			double[] p1 = sm.getCentredXandY(x - sm.getResolution(), prevY);
			double[] p2 = sm.getCentredXandY(x, newY);
			
			// Draw a line between p1 and p2.
			g.draw(new Line2D.Float((int) (p1[0]), (int) (p1[1]), (int) (p2[0]), (int) (p2[1])));
			prevY = newY;
		}
	}
	
}
