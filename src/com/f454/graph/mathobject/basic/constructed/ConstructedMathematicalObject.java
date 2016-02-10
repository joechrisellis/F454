package com.f454.graph.mathobject.basic.constructed;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.ListIterator;

import com.f454.graph.GraphingEngine;
import com.f454.graph.ScalingManager;
import com.f454.graph.mathobject.basic.BasicMathematicalObject;
import com.f454.graph.mathobject.basic.Point;
import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;

/**
 * A mathematical object that has to be constructed through creating points
 * and drawing lines between them.
 * @author Joe Ellis
 */
public abstract class ConstructedMathematicalObject extends BasicMathematicalObject {
	
	protected ArrayList<Point> points;
	
	// At resolutions LESS THAN this value, cropping optimisations
	// will take place.
	protected static final double CROPPING_THRESHOLD = 0.1D;
	protected GraphingEngine ge;
	
	public ConstructedMathematicalObject(String name, String tooltip, Color color, GraphingEngine ge, ScalingManager sm) {
		super(name, tooltip, color, sm);
		this.ge = ge;
		points = new ArrayList<Point>();
	}
	
	public void render(Graphics2D g) {
		g.setColor(color);
		
		// Set the line width to 2 pixels wide; looks prettier.
		// If hovered over in the mathematical object panel, use bold width.
		g.setStroke(new BasicStroke(hovered ? BasicMathematicalObject.BOLD_WIDTH : BasicMathematicalObject.NORMAL_WIDTH));
		
		ListIterator<Point> itr = points.listIterator();
		Point prev = itr.next();
		
		// Iterate through all of the points given by the list iterator. 
		while(itr.hasNext()) {
			Point p = itr.next();
			
			// This boolean says 'draw a line between current and previous point (if it is
			// visible on the panel)'.
			// Only start filtering out what to render and what not to when the resolution
			// is low enough, else we will get over-cropped, buggy images.
			boolean onScreen = sm.getResolution() > CROPPING_THRESHOLD || (isOnScreen(p) || isOnScreen(prev));
			
			// Also, if the point is out of the user defined range, don't draw to it.
			if(onScreen && !p.outOfRange) {
				drawLine(g, p, prev);
			}
			
			prev = p;
			
		}
	}
	
	private static DoubleEvaluator evaluator = new DoubleEvaluator();
	public static Double evaluate(String expression, StaticVariableSet<Double> variables) throws IllegalArgumentException {
		return evaluator.evaluate(expression, variables);
	}
	
	/**
	 * Draws a line between two Point objects with the Graphics2D object g.
	 * @param g The Graphics2D object to do the drawing with.
	 * @param point1 Where to start the line.
	 * @param point2 Where to end the line.
	 */
	protected void drawLine(Graphics2D g, Point point1, Point point2) {
		Point p1 = sm.transformedPoint(point1);
		Point p2 = sm.transformedPoint(point2);
		g.draw(new Line2D.Float((int) (p1.x), (int) (p1.y), (int) (p2.x), (int) (p2.y)));
	}
	
	/**
	 * Returns a boolean indicating whether or not the point is visible on.
	 * the GraphingPanel.
	 * @param p The point to check.
	 * @return Boolean whether the point is visible on the screen.
	 */
	private boolean isOnScreen(Point p) {
		Point newPoint = sm.transformedPoint(p);
		return newPoint.x >= 0 && newPoint.x <= sm.getWidth()
			&& newPoint.y >= 0 && newPoint.y <= sm.getHeight();
	}
	
	/**
	 * Clears the arraylist of points.
	 */
	public void reinit() {
		points.clear();
	}

}
