package com.f454.graph.mathobject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.ListIterator;

import com.f454.graph.ScalingManager;

/**
 * A mathematical object that has to be constructed through
 * defining points.
 * @author Joe Ellis
 */
public abstract class ConstructedMathematicalObject extends MathematicalObject {
	
	protected ArrayList<Point> points;
	
	public ConstructedMathematicalObject(String name, String tooltip, Color color, ScalingManager sm) {
		super(name, tooltip, color, sm);
		points = new ArrayList<Point>();
	}
	
	public void render(Graphics2D g) {
		g.setColor(color);
		
		// Set the line width to 2 pixels wide; looks prettier.
		g.setStroke(new BasicStroke(2));
		
		ListIterator<Point> itr = points.listIterator();
		Point prev = itr.next();
		
		while(itr.hasNext()) {
			Point p = itr.next();
			
			double[] p1 = sm.getCentredXandY(prev.x, prev.y);
			double[] p2 = sm.getCentredXandY(p.x, p.y);
			
			g.draw(new Line2D.Float((int) (p1[0]), (int) (p1[1]), (int) (p2[0]), (int) (p2[1])));
			prev = p;
		}
	}
	
	public void reinit() {
		points.clear();
	}

}
