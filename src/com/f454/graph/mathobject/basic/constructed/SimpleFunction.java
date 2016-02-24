package com.f454.graph.mathobject.basic.constructed;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JCheckBoxMenuItem;

import com.f454.graph.GraphingEngine;
import com.f454.graph.ScalingManager;
import com.f454.graph.mathobject.basic.Point;
import com.f454.graph.mathobject.special.Axes;
import com.f454.gui.mainwindow.MainWindow;
import com.fathzer.soft.javaluator.StaticVariableSet;

/**
 * The mathematical object that represents a function of the form y=f(x) or x=f(y).
 * @author Joe Ellis
 *
 */
public class SimpleFunction extends ConstructedMathematicalObject {
	
	// This tooltip will be used in conjunction with String.format().
	private static final String TOOLTIP = "%s = %s";
	
	private JCheckBoxMenuItem showyIntercept;
	
	protected String expression;
	protected Point yIntercept;
	
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
		
		showyIntercept = new JCheckBoxMenuItem("Show y-intercept");
		menu.add(showyIntercept);
	}
	
	public void reinit() {
		super.reinit();
		
		double lower = hasDomain ? domainLBound : -Axes.NUMBERS;
		double upper = hasDomain ? domainUBound : Axes.NUMBERS;
		
		MainWindow m = MainWindow.getInstance();
		StaticVariableSet<Double> variables = m.getSliderWindow().getUserVariables();
		
		// first, set the variables so we can find the y-intercept...
		if(yEquals) variables.set("x", 0D);
		else        variables.set("y", 0D);
		
		// TRY to find a y-intercept...
		try {
			
			// mark the point in the right place
			if(yEquals) {
				yIntercept = new Point(0, ConstructedMathematicalObject.evaluate(expression, variables));
			} else {
				yIntercept = new Point(ConstructedMathematicalObject.evaluate(expression, variables), 0);
			}
			
		} catch(IllegalArgumentException err) {
			// do nothing
		}
		
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
	
	public void render(Graphics2D g) {
		super.render(g);
		
		if(showyIntercept.isSelected() && yIntercept != null) {
			int r = hovered ? Point.RADIUS_BOLD : Point.RADIUS_NORMAL;
			Point.renderPoint(g, sm, yIntercept, r, true, color);
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
