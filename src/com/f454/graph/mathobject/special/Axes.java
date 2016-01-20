package com.f454.graph.mathobject.special;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPopupMenu;

import com.f454.graph.ScalingManager;
import com.f454.graph.mathobject.MathematicalObject;

public class Axes extends MathematicalObject {
	
	protected JCheckBoxMenuItem showGridMenu;
	protected JCheckBoxMenuItem showNumberingMenu;
	protected JCheckBoxMenuItem showPiMenu;
	
	public Axes(ScalingManager sm) {
		super("Axes", null, Color.BLACK, sm);
		
		showGridMenu = new JCheckBoxMenuItem("Show Gridlines", true);
		showNumberingMenu = new JCheckBoxMenuItem("Show Axes Numbering");
		showPiMenu = new JCheckBoxMenuItem("Show Axes in terms of Pi");
		
		menu = new JPopupMenu();
		menu.add(showGridMenu);
		menu.add(showNumberingMenu);
		menu.add(showPiMenu);
	}

	public void render(Graphics2D g) {
		
		g.setColor(Color.LIGHT_GRAY);
		
		// TODO
		if(false && showGridMenu.isSelected()) {
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
		int xVisible = (int) ((width / 2) * (1 / sm.getxScale()));
		int yVisible = (int) ((height / 2) * (1 / sm.getyScale()));
		
		for(int x = -xVisible; x <= xVisible; x++) {
			double[] xy = sm.getCentredXandY(x, 0);
			g.drawLine((int) (xy[0]) % (int) (width / xVisible), 0,
					   (int) (xy[0]) % (int) (width / xVisible), height);
		}
		
//		for(int y = -yVisible; y <= yVisible; y++) {
//			double[] xy = sm.getCentredXandY(0, y);
//			g.drawLine(0, (int) (xy[1]) % height, width, (int) (xy[1]) % height);
//		}
		
	}
	
	private void renderNumbers(Graphics2D g) {
		
		int width = sm.getWidth();
		int height = sm.getHeight();
		int xVisible = (int) ((width / 2) * (1 / sm.getxScale()));
		int yVisible = (int) ((height / 2) * (1 / sm.getyScale()));
		
		for(int x = -xVisible; x <= xVisible; x += width / xVisible) {
			double[] xy = sm.getCentredXandY(x, 0);
			g.drawString(String.format("%d", x), (int) (xy[0]), (int) (xy[1]));
		}
		
	}
	
	private void renderPi(Graphics2D g) {}
	
}