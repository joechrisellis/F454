package com.f454.graph.mathobject.basic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.f454.graph.ScalingManager;
import com.f454.graph.mathobject.basic.constructed.SimpleFunction;
import com.f454.gui.mainwindow.MainWindow;

/**
 * The data set mathematical objects. In the simplest terms possible, this is
 * a collection of points.
 * @author Joe Ellis
 *
 */
public class DataSet extends BasicMathematicalObject {
	
	// Used in conjunction with String.format().
	private static final String TOOLTIP = "%d points";
	private static final Font FONT = new Font(null, Font.PLAIN, 10);
	
	// Errors that might be raised.
	public static final String ERR_INSUFFICIENT_POINTS = "At least two points need to be present in a "
													   + "data set to plot a line of best fit.";
	public static final String ERR_ALL_SAME = "The points form a horizontal/vertical line of best fit.";
	
	// The arraylist of all of the points.
	private ArrayList<Point> points;
	private static final int RADIUS_NORMAL = 7, RADIUS_BOLD = (int) (RADIUS_NORMAL * 1.5);
	
	protected JMenuItem plotLineOfBestFit;
	protected JCheckBoxMenuItem showLabels;
	
	public DataSet(String name, ArrayList<Point> points, Color color, ScalingManager sm) {
		super(name, String.format(TOOLTIP, points.size()), color, sm);
		this.points = points;
		
		plotLineOfBestFit = new JMenuItem("Plot line of best fit");
		plotLineOfBestFit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				plotLineOfBestFit();
			}
			
		});
		
		showLabels = new JCheckBoxMenuItem("Show Labels", true);
		
		menu.add(plotLineOfBestFit);
		menu.add(showLabels);
	}
	
	public void render(Graphics2D g) {
		g.setColor(color);
		g.setFont(FONT);
		
		int r = hovered ? RADIUS_BOLD : RADIUS_NORMAL;
		
		ListIterator<Point> itr = points.listIterator();
		while(itr.hasNext()) {
			Point p = itr.next();
			double[] xy = sm.getFinalisedXandY(p.x, p.y);
			
			g.fillOval((int) (xy[0] - r / 2), (int) (xy[1] - r / 2), r, r);
			
			if(showLabels.isSelected()) {
				g.drawString(String.format("(%.2f, %.2f)", p.x, p.y), (int) (xy[0] + 3), (int) (xy[1] - 3));
			}
			
		}
	}
	
	/**
	 * Creates a function that is the line of best fit for this set of points and
	 * adds it to the graphing engine.
	 */
	public void plotLineOfBestFit() {
		
		// If there's only one point (or somehow less than one point), tell the user
		// that it's impossible to plot a line of best fit and abort.
		if(points.size() <= 1) {
			JOptionPane.showMessageDialog(MainWindow.getInstance(), ERR_INSUFFICIENT_POINTS, "Error",
											JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// Check whether the points form either a vertical or horizontal line.
		// If they do, raise an error.
		
		boolean allSameX = true, allSameY = true;
		ListIterator<Point> itr = points.listIterator();
		Point first = itr.next();
		double firstX = first.x;
		double firstY = first.y;
		
		while(itr.hasNext()) {
			Point p = itr.next();
			if(p.x != firstX) allSameX = false;
			if(p.y != firstY) allSameY = false;
		}
		
		if(allSameX || allSameY) {
			JOptionPane.showMessageDialog(MainWindow.getInstance(), ERR_ALL_SAME, "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		double[] xs = new double[points.size()], ys = new double[points.size()];
		for(int i = 0; i < points.size(); i++) {
			xs[i] = points.get(i).x;
			ys[i] = points.get(i).y;
		}
		
		double correlation = correlation(xs, ys);
		double a = correlation * (deviation(ys) / deviation(xs));
		double b = mean(ys) - a * mean(xs);
		
		MainWindow m = MainWindow.getInstance();
		
		String title = String.format("Line of best fit for \"%s\"", name);
		String expression = String.format("%.2f*x + %.2f", a, b);
		
		SimpleFunction f = new SimpleFunction(title, true, expression, color, 
				m.getGraphingPanel().getGraphingEngine(), sm);
		f.reinit();
		
		// Add the function that we have created to the graphing engine.
		m.getGraphingPanel().getGraphingEngine().addMathObject(f);
	}
	
	public void addPoint(Point p) {
		points.add(p);
	}
	
	/**
	 * Gives the arithmetic mean for an array of doubles.
	 * @param n The array of doubles.
	 * @return The arithmetic mean.
	 */
	private static double mean(double[] n) {
		double sum = 0;
		for(double i : n) {
			sum += i;
		}
		
		return sum / n.length;
	}
	
	/**
	 * Gives the standard deviation for an array of doubles.
	 * @param n The array of doubles.
	 * @return The standard deviation.
	 */
	private static double deviation(double[] n) {
		double mean = mean(n);
		double v = 0;
		for(double i : n) {
			v += (mean - i) * (mean - i);
		}
		
		return Math.sqrt(v / n.length);
	}
	
	/**
	 * Gives the product moment correlation coefficient (r) between two sets
	 * of data xs and ys.
	 * @param xs The x-set of data.
	 * @param ys The y-set of data.
	 * @return The product moment correlation coefficient.
	 */
	public static double correlation(double[] xs, double[] ys) {
		double sx = 0.0;
		double sy = 0.0;
		double sxx = 0.0;
		double syy = 0.0;
		double sxy = 0.0;

		int n = xs.length;

		for(int i = 0; i < n; ++i) {
			double x = xs[i];
			double y = ys[i];
			
			sx += x;
			sy += y;
			sxx += x * x;
			syy += y * y;
			sxy += x * y;
		}

		// covariation
		double cov = sxy / n - sx * sy / n / n;
		// standard error of x
		double sigmax = Math.sqrt(sxx / n -  sx * sx / n / n);
		// standard error of y
		double sigmay = Math.sqrt(syy / n -  sy * sy / n / n);
		
		// correlation is just a normalized covariation
		return cov / sigmax / sigmay;
		
	}
	
}
