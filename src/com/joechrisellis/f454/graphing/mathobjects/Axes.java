package com.joechrisellis.f454.graphing.mathobjects;

import java.awt.Color;
import java.awt.Graphics2D;

import com.joechrisellis.f454.graphing.ScalingManager;

public class Axes extends MathematicalObject {
	
	public Axes(ScalingManager sm) {
		super("Axes", Color.BLACK, sm);
	}

	public void render(Graphics2D g) {
		g.setColor(color);
		
		int width = sm.getWidth();
		int height = sm.getHeight();
		
		double[] xy = sm.getTranslatedXandY(width / 2, height / 2);
		g.drawLine((int) (xy[0]), 0, (int) (xy[0]), height);
		g.drawLine(0, (int) (xy[1]), width, (int) (xy[1]));
	}
	
}
