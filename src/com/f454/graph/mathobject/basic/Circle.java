package com.f454.graph.mathobject.basic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import com.f454.graph.ScalingManager;

public class Circle extends BasicMathematicalObject {
	
	private static final String TOOLTIP = "(x - %.1f)^2 + (y - %.1f)^2 = %.1f^2";
	
	private double x, y, r;
	
	public Circle(String name, double x, double y, double r, Color color,
					ScalingManager sm) {
		super(name, String.format(TOOLTIP, x, y, r), color, sm);
		this.x = x;
		this.y = y;
		this.r = r;
	}

	public void render(Graphics2D g) {
		g.setColor(color);
		g.setStroke(new BasicStroke(hovered ? BasicMathematicalObject.BOLD_WIDTH : BasicMathematicalObject.NORMAL_WIDTH));
		
		// we do y + r / 2 here because as far as java's painting is concerned,
		// y = 0 is the top of the screen and y increases as we go down.
		// we have to do the opposite to account for this.
		double[] xy = sm.getCentredXandY(-x - r, -y + r);
		
		// we have to use -r in sm.getScaledY(-r) for the same reason as above;
		// the getScaledY() method automatically takes into account java's painting
		// coordinate system for us, so we have to 'trick' it using the negative of 'r'.
		double[] sr = {sm.getScaledX(2 * r), sm.getScaledY(2 * -r)};
		
		g.drawOval((int) (xy[0]), (int) (xy[1]), (int) (sr[0]), (int) (sr[1]));
	}

}
