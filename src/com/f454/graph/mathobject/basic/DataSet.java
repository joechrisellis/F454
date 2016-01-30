package com.f454.graph.mathobject.basic;

import java.awt.Color;
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

public class DataSet extends BasicMathematicalObject {
	
	private static final String TOOLTIP = "%d points";
	public static final String ERR_INSUFFICIENT_POINTS = "At least two points need to be present in a "
													   + "data set to plot a line of best fit.";
	
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
		
		int r = hovered ? RADIUS_BOLD : RADIUS_NORMAL;
		
		ListIterator<Point> itr = points.listIterator();
		while(itr.hasNext()) {
			Point p = itr.next();
			double[] xy = sm.getCentredXandY(p.x, p.y);
			
			g.fillOval((int) (xy[0] - r / 2), (int) (xy[1] - r / 2), r, r);
			
			if(showLabels.isSelected()) {
				g.drawString(String.format("(%.2f, %.2f)", p.x, p.y), (int) (xy[0]), (int) (xy[1]));
			}
			
		}
	}
	
	public void plotLineOfBestFit() {
		
		if(points.size() <= 1) {
			JOptionPane.showMessageDialog(MainWindow.getInstance(), ERR_INSUFFICIENT_POINTS, "Error",
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
		
		SimpleFunction f = new SimpleFunction(title, true, expression, color, sm);
		f.reinit();
		
		m.getGraphingPanel().getGraphingEngine().addMathObject(f);
	}
	
	public void addPoint(Point p) {
		points.add(p);
	}
	
	private static double mean(double[] n) {
		double sum = 0;
		for(double i : n) {
			sum += i;
		}
		
		return sum / n.length;
	}
	
	private static double deviation(double[] n) {
        double mean = mean(n);
        double v = 0;
        for(double i : n) {
            v += (mean - i) * (mean - i);
        }
        
        return Math.sqrt(v / n.length);
	}
	
	  public static double correlation(double[] xs, double[] ys) {
		    //TODO: check here that arrays are not null, of the same length etc

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
