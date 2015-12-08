package com.joechrisellis.f454.graphing.mathobjects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import com.joechrisellis.f454.graphing.GraphingEngine;
import com.joechrisellis.f454.graphing.ScalingManager;

public class SimpleFunctionYEquals extends MathematicalObject {
	
	private boolean hasDomain, hasRange;
	private double domainLBound, domainUBound;
	private double rangeLBound, rangeUBound;
	
	public SimpleFunctionYEquals(String name, Color color, GraphingEngine graphingEngine) {
		super(name, color, graphingEngine);
	}

	public void render(Graphics2D g) {
		if(!visible) return;
		
		g.setColor(color);
		g.setStroke(new BasicStroke(2));
		
		ScalingManager sm = graphingEngine.getScalingManager();
		int w = graphingEngine.getGraphingPanel().getWidth();
		
		double lower = hasDomain ? domainLBound : -w / 2;
		double upper = hasDomain ? domainUBound : w / 2;
		
		double prevY = 0;
		for(double x = lower; x < upper; x += graphingEngine.getResolution()) {
			double newY = 3 * x;
			
			double[] p1 = sm.getScaledXandY(x - graphingEngine.getResolution(), prevY);
			double[] p2 = sm.getScaledXandY(x, newY);
			
			g.draw(new Line2D.Float((int) (p1[0]), (int) (p1[1]), (int) (p2[0]), (int) (p2[1])));
			prevY = newY;
		}
	}
	
}
