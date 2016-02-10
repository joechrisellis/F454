package com.f454.graph.mathobject.basic.constructed;

import java.awt.Color;

import com.f454.graph.GraphingEngine;
import com.f454.graph.ScalingManager;
import com.f454.graph.mathobject.basic.Point;
import com.f454.graph.mathobject.special.Axes;
import com.fathzer.soft.javaluator.StaticVariableSet;

/**
 * The mathematical object that represents a function of the form y=f(x) or x=f(y).
 * @author Joe Ellis
 *
 */
public class SimpleFunction extends ConstructedMathematicalObject {
	
	// This tooltip will be used in conjunction with String.format().
	private static final String TOOLTIP = "%s = %s";
	
	protected String expression;
	
	// If true, y=f(x)
	// If false, x=f(y)
	protected boolean yEquals;
	
	// Domain and range...
	protected boolean hasDomain, hasRange;
	protected double domainLBound, domainUBound;
	protected double rangeLBound, rangeUBound;
		
	public SimpleFunction(String name, boolean yEquals, String expression,
							Color color, GraphingEngine ge, ScalingManager sm) {
		super(name, String.format(TOOLTIP, (yEquals ? "y" : "x"), expression), color, ge, sm);
		this.expression = expression;
		this.yEquals = yEquals;
	}
	
	public void reinit() {
		super.reinit();
		
		double lower = hasDomain ? domainLBound : -Axes.NUMBERS;
		double upper = hasDomain ? domainUBound : Axes.NUMBERS;
		
		StaticVariableSet<Double> variables = ge.getVariables();
		
		// Iterate through all of the values of x.
		for(double x = lower; x < upper; x += sm.getResolution()) {
			if(yEquals) variables.set("x", x);
			else        variables.set("y", x);
			
			double e;
			
			// try to evaluate the user's expression
			try {
				e = ConstructedMathematicalObject.evaluate(expression, variables);
			} catch(IllegalArgumentException err) {
				// if we can't, skip this point.
				continue;
			}
			
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
