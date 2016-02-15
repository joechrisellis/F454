package com.f454.graph.mathobject.basic.constructed;

import java.awt.Color;

import com.f454.graph.GraphingEngine;
import com.f454.graph.ScalingManager;
import com.f454.graph.mathobject.basic.Point;
import com.f454.gui.mainwindow.MainWindow;
import com.fathzer.soft.javaluator.StaticVariableSet;

/**
 * The mathematical object for a parametric equation.
 * @author Joe Ellis
 *
 */
public class ParametricEquation extends ConstructedMathematicalObject {
	
	private static final String TOOLTIP = "x(t) = %s, y(t) = %s";
	private String expression1, expression2;
	private int tMax;
	
	public ParametricEquation(String name, String ex1, String ex2, int tMax, Color color, GraphingEngine ge, 
									ScalingManager sm) {
		super(name, String.format(TOOLTIP, ex1, ex2), color, ge, sm);
		this.expression1 = ex1;
		this.expression2 = ex2;
		this.tMax = tMax;
	}
	
	public void reinit() {
		super.reinit();
		
		double lower = 0;
		
		// Evaluator object and variable set for evaluating the user
		// inputed expression.
		MainWindow m = MainWindow.getInstance();
		StaticVariableSet<Double> variables = m.getSliderWindow().getUserVariables();
		
		// Iterate through all of the values of x.
		for(double t = lower; t < tMax; t += sm.getResolution()) {
			variables.set("t", t);
			
			double x, y;
			
			// try to evaluate the user's expressions
			try {
				x = ConstructedMathematicalObject.evaluate(expression1, variables);
				y = ConstructedMathematicalObject.evaluate(expression2, variables);
			} catch(IllegalArgumentException err) {
				// if we can't, skip this value of t.
				continue;
			}
			
			points.add(new Point(x, y));
		}
	}
	
}
