package com.f454.graph.mathobject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import com.f454.graph.ScalingManager;

public class SimpleFunctionXEquals extends SimpleFunctionYEquals {
	
	
	public SimpleFunctionXEquals(String name, String expression, Color color,
									ScalingManager sm) {
		super(name, expression, color, sm);
	}
	
	public void render(Graphics2D g) {
		g.setColor(color);
		g.setStroke(new BasicStroke(2));
		
		int w = sm.getCentre()[0];
		double lower = hasDomain ? domainLBound : -w;
		double upper = hasDomain ? domainUBound : w;
		
		double prevX = 0;
		for(double y = lower; y < upper; y += sm.getResolution()) {
			double newX = Math.sin(y);
			
			double[] p1 = sm.getCentredXandY(prevX, y - sm.getResolution());
			double[] p2 = sm.getCentredXandY(newX, y);
			
			g.draw(new Line2D.Float((int) (p1[0]), (int) (p1[1]), (int) (p2[0]), (int) (p2[1])));
			prevX = newX;
		}
	}

}
