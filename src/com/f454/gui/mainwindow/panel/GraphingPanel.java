package com.f454.gui.mainwindow.panel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.f454.graph.GraphingEngine;
import com.f454.graph.ScalingManager;
import com.f454.gui.mainwindow.MainWindow;
import com.f454.gui.misc.Mouse;

public class GraphingPanel extends JPanel {
	
	private GraphingEngine graphingEngine;
	
	// mousePrevX and mousePrevY are required for tracking the previous position
	// of the mouse so that we can monitor how it changes.
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
	
	/**
	 * Starts the thread for the graphing panel. This thread renders the mathematical
	 * objects and accepts user input through the mouse.
	 */
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
	
	/**
	 * Stops the thread for the graphing panel cleanly.
	 */
	public void stop() {
		timer.stop();
	}
	
	/**
	 * Renders all of the mathematical objects on the graphing panel using the
	 * graphing engine.
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		// enable antialiasing for smooth images
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		graphingEngine.render(g2d); // pass the g2d object to the graphing engine for rendering
		
		g.dispose();
	}
	
	/**
	 * Accept user input and change variables from the graphing engine's scaling
	 * manager accordingly.
	 */
	private void update() {
		
		ScalingManager sm = graphingEngine.getScalingManager();
		
		// If the left mouse button is held, add the change in mouse position
		// to the xTranslation and yTranslation variables.
		if(mouse.isLeftHeld()) {
			sm.setxTranslation((mouse.getX() + sm.getxTranslation()) - mousePrevX);
			sm.setyTranslation((mouse.getY() + sm.getyTranslation()) - mousePrevY);
		}
		
		int scroll = -mouse.getScroll();
		
		// If the scale + scroll is larger than the minimum zoom, allow zooming.
		if(sm.getxScale() + scroll > ScalingManager.MINIMUM_ZOOM_X) {
			sm.setxScale(sm.getxScale() + scroll);
		}
		
		if(sm.getyScale() + scroll > ScalingManager.MINIMUM_ZOOM_Y) {
			sm.setyScale(sm.getyScale() + scroll);
		}
		
		// Update the previous position of the mouse ready for the next tick.
		mousePrevX = mouse.getX();
		mousePrevY = mouse.getY();
	}
	
	/**
	 * Raise a file chooser dialog and export the image on the graphing panel
	 * to the chosen file.
	 */
	public void export() {
		BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bufferedImage.createGraphics();
		paintAll(g2d);
		
		try {
			final JFileChooser fc = new JFileChooser();
			
			// if the user didn't cancel...
			if(fc.showDialog(MainWindow.getInstance(), "Export") == JFileChooser.APPROVE_OPTION) {
				File f = fc.getSelectedFile();
				ImageIO.write(bufferedImage, "png", f);
			}
		} catch(IOException e) {
			// ignore.
		}
		
	}
	
	public GraphingEngine getGraphingEngine() {
		return graphingEngine;
	}

	public double getFPS() {
		return FPS;
	}

}
