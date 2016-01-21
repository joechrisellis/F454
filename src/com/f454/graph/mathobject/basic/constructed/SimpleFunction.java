package com.f454.graph.mathobject.basic.constructed;

import java.awt.Color;

import com.f454.graph.ScalingManager;
import com.f454.graph.mathobject.basic.Point;
import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;

public class SimpleFunction extends ConstructedMathematicalObject {
	
	protected String expression;
	protected boolean yEquals;
	
	protected boolean hasDomain, hasRange;
	protected double domainLBound, domainUBound;
	protected double rangeLBound, rangeUBound;
	
	public SimpleFunction(String name, boolean yEquals, String expression, Color color,
									ScalingManager sm) {
		super(name, expression, color, sm);
		this.expression = expression;
		this.yEquals = yEquals;
		
		reinit();
	}
	
	public void reinit() {
		super.reinit();
		
		int w = sm.getCentre()[0];
		double lower = hasDomain ? domainLBound : -w;
		double upper = hasDomain ? domainUBound : w;
		
		// Evaluator object and variable set for evaluating the user
		// inputed expression.
		DoubleEvaluator evaluator = new DoubleEvaluator();
		StaticVariableSet<Double> variables = new StaticVariableSet<Double>();
		
		// Iterate through all of the values of x.
		for(double x = lower; x < upper; x += sm.getResolution()) {
			variables.set("x", x);
			
			if(yEquals) {
				points.add(new Point(x, evaluator.evaluate(expression, variables)));
			} else {
				points.add(new Point(evaluator.evaluate(expression, variables), x));
			}
			
		}
	}
	
}
