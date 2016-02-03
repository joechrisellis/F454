package com.f454.graph.mathobject.special;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.f454.graph.ScalingManager;
import com.f454.graph.mathobject.MathematicalObject;
import com.f454.gui.setting.SetScalingDialog;

/**
 * The special mathematical object for the axes.
 * @author Joe Ellis
 *
 */
public class Axes extends MathematicalObject {
	
	// The number of labels to place either side of the origin for both the 
	// x and y-axes.
	public static final int NUMBERS = 500;
	private static final Font FONT = new Font(null, Font.PLAIN, 10);
	
	public static final ActionListener showScalingListener = new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			new SetScalingDialog();
		}
		
	};
	
	protected JMenuItem raiseScalingDialog;
	protected JCheckBoxMenuItem showGridMenu;
	protected JCheckBoxMenuItem showXNumberingMenu, showYNumberingMenu;
	protected JCheckBoxMenuItem showPiMenu;
	
	public Axes(ScalingManager sm) {
		super("Axes", null, Color.BLACK, sm);
		this.removable = false;
		
		raiseScalingDialog = new JMenuItem("Show Scaling Settings");
		raiseScalingDialog.addActionListener(showScalingListener);
		
		showGridMenu = new JCheckBoxMenuItem("Show Gridlines", true);
		
		JMenu numberingMenu = new JMenu("Axes Numbering");
		showXNumberingMenu = new JCheckBoxMenuItem("Show numbering on x-axis", true);
		showYNumberingMenu = new JCheckBoxMenuItem("Show numbering on y-axis", true);
		numberingMenu.add(showXNumberingMenu);
		numberingMenu.add(showYNumberingMenu);
		
		showPiMenu = new JCheckBoxMenuItem("Show Axes in terms of Pi");
		
		menu = new JPopupMenu();
		menu.add(raiseScalingDialog);
		menu.add(showGridMenu);
		menu.add(numberingMenu);
		menu.add(showPiMenu);
	}

	public void render(Graphics2D g) {
		
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(FONT);
		
		// If the user has opted to show the grid, render it.
		if(showGridMenu.isSelected()) {
			renderGrid(g);
		}
		
		// If the user has opted to show axes number on the x-axis, render it.
		if(showXNumberingMenu.isSelected()) {
			renderXNumbers(g);
		}
		
		// If the user has opted to show axes number on the y-axis, render it.
		if(showYNumberingMenu.isSelected()) {
			renderYNumbers(g);
		}
		
		// If the user has opted to show axes in terms of pi, render it.
		if(showPiMenu.isSelected()) {
			renderPi(g);
		}
		
		int width = sm.getWidth();
		int height = sm.getHeight();
		
		
		// draw the actual axes themselves.
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
			double[] xy = sm.getFinalisedXandY(k, 0);
			
			g.drawLine((int) (xy[0]), 0, (int) (xy[0]), height);
		}
		
		for(int y = -NUMBERS; y <= NUMBERS; y++) {
			
			double k = y / Math.floor(sm.getyScale() / ScalingManager.GRIDLINE_CONSTANT_Y);
			double[] xy = sm.getFinalisedXandY(0, k);
			
			g.drawLine(0, (int) (xy[1]), width, (int) (xy[1]));
		}
		
	}
	
	private void renderXNumbers(Graphics2D g) {
		g.setColor(Color.BLACK);
		
		for(int x = -NUMBERS; x <= NUMBERS; x++) {
			
			double k = x / Math.floor(sm.getxScale() / ScalingManager.GRIDLINE_CONSTANT_X);
			double[] xy = sm.getFinalisedXandY(k, 0);
			
			String label = String.format("%.2f", k);
			g.drawString(label, (int) (xy[0]), (int) (xy[1]) + 12); // + 12 so that the numbers are under the axes
		}
				
	}
	
	private void renderYNumbers(Graphics2D g) {
		g.setColor(Color.BLACK);
		
		for(int y = -NUMBERS; y <= NUMBERS; y++) {
			
			// rendering of the centre (0, 0) done by above for loop
			if(y == 0) continue;
			
			double k = y / Math.floor(sm.getyScale() / ScalingManager.GRIDLINE_CONSTANT_Y);
			double[] xy = sm.getFinalisedXandY(0, k);
			
			String label = String.format("%.2f", k);
			g.drawString(label, (int) (xy[0]), (int) (xy[1]));
		}
		
	}
	
	private void renderPi(Graphics2D g) {
		g.setColor(Color.BLACK);
		
		for(int x = -NUMBERS; x <= NUMBERS; x++) {
			
			// rendering of the centre (0, 0) done by x-axis numbering
			if(x == 0) continue;
			
			double k = (x / Math.floor(sm.getxScale() / ScalingManager.GRIDLINE_CONSTANT_X)) * Math.PI;
			double[] xy = sm.getFinalisedXandY(k, 0);
			
			String label = String.format("%.2f\u03C0", (x / Math.floor(sm.getxScale() / ScalingManager.GRIDLINE_CONSTANT_X)));
			
			// draw a line to mark where each pi is.
			g.drawLine((int) (xy[0]), (int) (xy[1]) - 4, (int) (xy[0]), (int) (xy[1]) + 4);
			g.drawString(label, (int) (xy[0]), (int) (xy[1]) - 5); // -5 so that the numbers are above the axes
		}
		
	}
	
}