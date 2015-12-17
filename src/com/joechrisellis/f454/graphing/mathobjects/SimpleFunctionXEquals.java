package com.joechrisellis.f454.graphing.mathobjects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import com.joechrisellis.f454.graphing.GraphingEngine;
import com.joechrisellis.f454.graphing.ScalingManager;

public class SimpleFunctionXEquals extends SimpleFunctionYEquals {
	
	public SimpleFunctionXEquals(String name, String expression, Color color, GraphingEngine graphingEngine) {
		super(name, expression, color, graphingEngine);
	}
	
	public void render(Graphics2D g) {
		g.setColor(color);
		g.setStroke(new BasicStroke(2));
		
		ScalingManager sm = graphingEngine.getScalingManager();
		
		int w = sm.getCentre()[0];
		double lower = hasDomain ? domainLBound : -w;
		double upper = hasDomain ? domainUBound : w;
		
		double prevY = 0;
		for(double x = lower; x < upper; x += graphingEngine.getResolution()) {
			double newY = Math.sin(x);
			
			double[] p1 = sm.getCentredXandY(prevY, x - graphingEngine.getResolution());
			double[] p2 = sm.getCentredXandY(newY, x);
			
			g.draw(new Line2D.Float((int) (p1[0]), (int) (p1[1]), (int) (p2[0]), (int) (p2[1])));
			prevY = newY;
		}
	}

}
