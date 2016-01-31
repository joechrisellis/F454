package com.f454.graph.mathobject.basic.constructed;

import java.awt.Color;

import com.f454.graph.ScalingManager;
import com.f454.graph.mathobject.basic.Point;
import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;

/**
 * The mathematical object that represents a function of the form y=f(x) or x=f(y).
 * @author Joe Ellis
 *
 */
public class SimpleFunction extends ConstructedMathematicalObject {
	
	protected String expression;
	
	// If true, y=f(x)
	// If false, x=f(y)
	protected boolean yEquals;
	
	// Domain and range...
	protected boolean hasDomain, hasRange;
	protected double domainLBound, domainUBound;
	protected double rangeLBound, rangeUBound;
	
	public SimpleFunction(String name, boolean yEquals, String expression,
							Color color, ScalingManager sm) {
		super(name, expression, color, sm);
		this.expression = expression;
		this.yEquals = yEquals;
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
			if(yEquals) variables.set("x", x);
			else        variables.set("y", x);
			
			double e = evaluator.evaluate(expression, variables);
			
			// if outside of the user defined range...
			boolean outOfRange = hasRange && (e < rangeLBound || e > rangeUBound);
			
			if(yEquals) {
				points.add(new Point(x, e, outOfRange));
			} else {
				points.add(new Point(e, x, outOfRange));
			}
			
		}
	}
	
	public boolean isyEquals() {
		return yEquals;
	}
	
	public boolean hasDomain() {
		return hasDomain;
	}
	
	public boolean hasRange() {
		return hasRange;
	}
	
	public void setDomain(double l, double u) {
		this.hasDomain = true;
		this.domainLBound = l;
		this.domainUBound = u;
	}
	
	public void setRange(double l, double u) {
		this.hasRange = true;
		this.rangeLBound = l;
		this.rangeUBound = u;
	}
	
}
