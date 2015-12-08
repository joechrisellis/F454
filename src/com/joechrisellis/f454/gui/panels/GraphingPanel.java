package com.joechrisellis.f454.gui.panels;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import com.joechrisellis.f454.graphing.GraphingEngine;
import com.joechrisellis.f454.graphing.ScalingManager;
import com.joechrisellis.f454.gui.misc.Mouse;

public class GraphingPanel extends JPanel implements Runnable {
	
	private GraphingEngine graphingEngine;
	
	private Mouse mouse;
	private int mousePrevX, mousePrevY;
	
	private volatile boolean running;
	private Thread thread;
	private final double UPDATES_PER_SECOND = 60D;
	
	public GraphingPanel() {
		setFocusable(true);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		requestFocus();
		
		mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
		graphingEngine = new GraphingEngine(this);
	}

	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		running = false;
		try {
			thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		// manages timekeeping and rendering for the program
		long lastTime = System.nanoTime();
		double delta = 0D;
		double ns = 1000000000D / UPDATES_PER_SECOND;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				update();
				delta -= 1;
			}
			
			repaint();
		}
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.clearRect(0, 0, getWidth(), getHeight());
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphingEngine.render(g2d);
		g.dispose();
	}
	
	private void update() {
		if(mouse.isLeftHeld()) {
			ScalingManager sm = graphingEngine.getScalingManager();
			sm.setxTranslation((mouse.getX() + sm.getxTranslation()) - mousePrevX);
			sm.setyTranslation((mouse.getY() + sm.getyTranslation()) - mousePrevY);
		}
		mousePrevX = mouse.getX();
		mousePrevY = mouse.getY();
	}
	
	public GraphingEngine getGraphingEngine() {
		return graphingEngine;
	}

	public boolean isRunning() {
		return running;
	}

	public double getUPDATES_PER_SECOND() {
		return UPDATES_PER_SECOND;
	}

}
