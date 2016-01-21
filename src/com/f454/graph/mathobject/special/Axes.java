package com.f454.graph.mathobject.special;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPopupMenu;

import com.f454.graph.ScalingManager;
import com.f454.graph.mathobject.MathematicalObject;

public class Axes extends MathematicalObject {
	
	private static final int NUMBERS = 30;
	
	protected JCheckBoxMenuItem showGridMenu;
	protected JCheckBoxMenuItem showNumberingMenu;
	protected JCheckBoxMenuItem showPiMenu;
	
	public Axes(ScalingManager sm) {
		super("Axes", null, Color.BLACK, sm);
		
		showGridMenu = new JCheckBoxMenuItem("Show Gridlines", true);
		showNumberingMenu = new JCheckBoxMenuItem("Show Axes Numbering", true);
		showPiMenu = new JCheckBoxMenuItem("Show Axes in terms of Pi");
		
		menu = new JPopupMenu();
		menu.add(showGridMenu);
		menu.add(showNumberingMenu);
		menu.add(showPiMenu);
	}

	public void render(Graphics2D g) {
		
		g.setColor(Color.LIGHT_GRAY);
		
		if(showGridMenu.isSelected()) {
			renderGrid(g);
		}
		
		if(showNumberingMenu.isSelected()) {
			renderNumbers(g);
		}
		
		int width = sm.getWidth();
		int height = sm.getHeight();
		
		g.setColor(color);
		double[] xy = sm.getTranslatedXandY(width / 2, height / 2);
		g.drawLine((int) (xy[0]), 0, (int) (xy[0]), height);
		g.drawLine(0, (int) (xy[1]), width, (int) (xy[1]));
	}
	
	private void renderGrid(Graphics2D g) {
		
		int width = sm.getWidth();
		int height = sm.getHeight();
		
		for(int x = -NUMBERS; x <= NUMBERS; x++) {
			
			double k = x / Math.floor(sm.getxScale() / ScalingManager.GRIDLINE_CONSTANT_X);
			double[] xy = sm.getCentredXandY(k, 0);
			
			g.drawLine((int) (xy[0]), 0, (int) (xy[0]), height);
		}
		
		for(int y = -NUMBERS; y <= NUMBERS; y++) {
			
			double k = y / Math.floor(sm.getxScale() / ScalingManager.GRIDLINE_CONSTANT_Y);
			double[] xy = sm.getCentredXandY(0, k);
			
			g.drawLine(0, (int) (xy[1]), width, (int) (xy[1]));
		}
		
	}
	
	private void renderNumbers(Graphics2D g) {
		g.setColor(Color.BLACK);
		
		for(int x = -NUMBERS; x <= NUMBERS; x++) {
			
			double k = x / Math.floor(sm.getxScale() / ScalingManager.GRIDLINE_CONSTANT_X);
			double[] xy = sm.getCentredXandY(k, 0);
			
			String label = String.format("%.2f", k);
			g.drawString(label, (int) (xy[0]), (int) (xy[1]) + 10); // + 10 so that the numbers are under the axes
		}
		
		for(int y = -NUMBERS; y <= NUMBERS; y++) {
			
			// rendering of the centre (0, 0) done by above for loop
			if(y == 0) continue;
			
			double k = y / Math.floor(sm.getxScale() / ScalingManager.GRIDLINE_CONSTANT_Y);
			double[] xy = sm.getCentredXandY(0, k);
			
			String label = String.format("%.2f", k);
			g.drawString(label, (int) (xy[0]), (int) (xy[1]));
		}
		
	}
	
	private void renderPi(Graphics2D g) {}
	
}