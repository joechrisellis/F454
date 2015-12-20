package com.joechrisellis.f454.graphing.mathobjects;

import java.awt.Color;
import java.awt.Graphics2D;

import com.joechrisellis.f454.graphing.ScalingManager;

public class Circle extends MathematicalObject {
	
	private double x, y, r;
	
	public Circle(String name, double x, double y, double r, Color color,
					ScalingManager sm) {
		super(name, color, sm);
		this.x = x;
		this.y = y;
		this.r = r;
	}

	public void render(Graphics2D g) {
		g.setColor(color);
		
		// we do y + r / 2 here because as far as java's painting is concerned,
		// y = 0 is the top of the screen and y increases as we go down.
		// we have to do the opposite to account for this.
		double[] xy = sm.getCentredXandY(x - r / 2, y + r / 2);
		double scaledRadius = (int) (sm.getScaledX(r));
		
		g.drawOval((int) (xy[0]), (int) (xy[1]), (int) (scaledRadius), (int) (scaledRadius));
	}

}
