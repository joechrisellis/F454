package com.joechrisellis.f454.graphing.mathobjects;

import java.awt.Color;
import java.awt.Graphics2D;

import com.joechrisellis.f454.graphing.GraphingEngine;
import com.joechrisellis.f454.graphing.ScalingManager;

public class Circle extends MathematicalObject {
	
	private double x, y, r;
	
	public Circle(String name, double x, double y, double r, Color color, GraphingEngine graphingEngine) {
		super(name, color, graphingEngine);
		this.x = x;
		this.y = y;
		this.r = r;
	}

	public void render(Graphics2D g) {
		g.setColor(color);
		
		ScalingManager sm = graphingEngine.getScalingManager();
		
		// we do y + r / 2 here because as far as java's painting is concerned,
		// y = 0 is the top of the screen and y increases as we go down.
		// we have to do the opposite to account for this.
		double[] xy = sm.getCentredXandY(x - r / 2, y + r / 2);
		g.drawOval((int) (xy[0]), (int) (xy[1]), (int) (sm.getScaledX(r)), (int) (sm.getScaledX(r)));
	}

}
