package com.f454.graph.mathobject.basic.constructed;

import java.awt.Color;

import com.f454.graph.ScalingManager;
import com.f454.graph.mathobject.basic.Point;
import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;

public class ParametricEquation extends ConstructedMathematicalObject {
	
	private static final String TOOLTIP = "x(t) = %s, y(t) = %s";
	private String expression1, expression2;
	private int tMax;
	
	public ParametricEquation(String name, String ex1, String ex2, int tMax, Color color, ScalingManager sm) {
		super(name, String.format(TOOLTIP, ex1, ex2), color, sm);
		this.expression1 = ex1;
		this.expression2 = ex2;
		this.tMax = tMax;
	}
	
	public void reinit() {
		super.reinit();
		
		double lower = 0;
		
		// Evaluator object and variable set for evaluating the user
		// inputed expression.
		DoubleEvaluator evaluator = new DoubleEvaluator();
		StaticVariableSet<Double> variables = new StaticVariableSet<Double>();
		
		// Iterate through all of the values of x.
		for(double t = lower; t < tMax; t += sm.getResolution()) {
			variables.set("t", t);
			
			double x = evaluator.evaluate(expression1, variables);
			double y = evaluator.evaluate(expression2, variables);
			super.points.add(new Point(x, y));
		}
	}
	
}
