package com.f454.graph.mathobject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import com.f454.graph.ScalingManager;
import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;

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
		
		// Evaluator object and variable set for evaluating the user
		// inputed expression.
		DoubleEvaluator evaluator = new DoubleEvaluator();
		StaticVariableSet<Double> variables = new StaticVariableSet<Double>();
		
		double prevX = 0;
		for(double y = lower; y < upper; y += sm.getResolution()) {
			
			variables.set("y", y);
			double newX = evaluator.evaluate(expression, variables);
			
			// Create two arrays:
			// * p1 representing the previous point.
			// * p2 representing the current point.
			double[] p1 = sm.getCentredXandY(prevX, y - sm.getResolution());
			double[] p2 = sm.getCentredXandY(newX, y);
			
			g.draw(new Line2D.Float((int) (p1[0]), (int) (p1[1]), (int) (p2[0]), (int) (p2[1])));
			prevX = newX;
		}
	}

}
