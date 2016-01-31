package com.f454.graph.mathobject.basic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import com.f454.graph.ScalingManager;

/**
 * A circle mathematical object.
 * @author Joe Ellis
 *
 */
public class Circle extends BasicMathematicalObject {
	
	// This tooltip will be used in conjunction with String.format().
	private static final String TOOLTIP = "(x - %.1f)^2 + (y - %.1f)^2 = %.1f^2";
	
	// (x, y) centre, radius r.
	private double x, y, r;
	
	public Circle(String name, double x, double y, double r, Color color,
					ScalingManager sm) {
		super(name, String.format(TOOLTIP, x, y, r), color, sm);
		this.x = x;
		this.y = y;
		
		// we use abs(r) here because it is possible (and valid) for the
		// user to input a negative number for the radius.
		this.r = Math.abs(r);
	}
	
	public void render(Graphics2D g) {
		g.setColor(color);
		
		// If the object is hovered over in the mathematical objects panel, draw it thicker.
		g.setStroke(new BasicStroke(hovered ? BasicMathematicalObject.BOLD_WIDTH : BasicMathematicalObject.NORMAL_WIDTH));
		
		// we do y + r / 2 here because as far as java's painting is concerned,
		// y = 0 is the top of the screen and y increases as we go down.
		// we have to do the opposite to account for this.
		double[] xy = sm.getFinalisedXandY(-x - r, -y + r);
		
		// we have to use -r in sm.getScaledY(-r) for the same reason as above;
		// the getScaledY() method automatically takes into account java's painting
		// coordinate system for us, so we have to 'trick' it using the negative of 'r'.
		double[] sr = {sm.getScaledX(2 * r), sm.getScaledY(2 * -r)};
		
		g.drawOval((int) (xy[0]), (int) (xy[1]), (int) (sr[0]), (int) (sr[1]));
	}

}
