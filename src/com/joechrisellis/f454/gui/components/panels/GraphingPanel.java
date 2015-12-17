package com.joechrisellis.f454.gui.components.panels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.joechrisellis.f454.graphing.GraphingEngine;
import com.joechrisellis.f454.graphing.ScalingManager;
import com.joechrisellis.f454.gui.misc.Mouse;

public class GraphingPanel extends JPanel {
	
	private GraphingEngine graphingEngine;
	
	private Mouse mouse;
	private int mousePrevX, mousePrevY;
	
	private Timer timer;
	private final double FPS = 60D;
	
	public GraphingPanel() {
		setFocusable(true);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		requestFocus();
		
		mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		addMouseWheelListener(mouse);
		
		graphingEngine = new GraphingEngine(this);
	}

	public void start() {
		int delay = (int) (1000 / FPS);
		timer = new Timer(delay, new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				repaint();
				update();
			}
			
		});
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		graphingEngine.render(g2d);
		
		g.dispose();
	}
	
	private void update() {
		ScalingManager sm = graphingEngine.getScalingManager();
		if(mouse.isLeftHeld()) {
			sm.setxTranslation((mouse.getX() + sm.getxTranslation()) - mousePrevX);
			sm.setyTranslation((mouse.getY() + sm.getyTranslation()) - mousePrevY);
		}
		
		int scroll = -mouse.getScroll();
		if(sm.getxScale() + scroll > 0) {
			sm.setxScale(sm.getxScale() + scroll);

		}
		
		if(sm.getyScale() + scroll > 0) {
			sm.setyScale(sm.getyScale() + scroll);
		}
		
		mousePrevX = mouse.getX();
		mousePrevY = mouse.getY();
	}
	
	public GraphingEngine getGraphingEngine() {
		return graphingEngine;
	}

	public double getFPS() {
		return FPS;
	}

}
