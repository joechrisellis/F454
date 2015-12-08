package com.joechrisellis.f454.graphing.mathobjects;

import java.awt.Color;
import java.awt.Graphics2D;

import com.joechrisellis.f454.graphing.GraphingEngine;
import com.joechrisellis.f454.graphing.ScalingManager;

public class Axes extends MathematicalObject {
	
	public Axes(GraphingEngine graphingEngine) {
		super("Axes", Color.BLACK, graphingEngine);
	}

	public void render(Graphics2D g) {
		if(!visible) return;
		
		g.setColor(color);
		
		ScalingManager sm = graphingEngine.getScalingManager();
		int width = graphingEngine.getGraphingPanel().getWidth();
		int height = graphingEngine.getGraphingPanel().getHeight();
		
		double[] xy = sm.getTranslatedXandY(width / 2, height / 2);
		g.drawLine((int) (xy[0]), 0, (int) (xy[0]), height);
		g.drawLine(0, (int) (xy[1]), width, (int) (xy[1]));
	}
	
}
