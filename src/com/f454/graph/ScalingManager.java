package com.f454.graph;

import com.f454.graph.mathobject.basic.Point;

/**
 * Used to manage the scaling for the graphing engine. Provides helpful
 * functions to make everything a bit easier.
 * @author Joe Ellis
 *
 */
public class ScalingManager {
		
	// Constant variables require for the rendering of gridlines.
	public static final int GRIDLINE_CONSTANT_X = 47;
	public static final int GRIDLINE_CONSTANT_Y = GRIDLINE_CONSTANT_X;
	
	// The initial zooming for the graphing calculator.
	public static final int INITIAL_XSCALE = GRIDLINE_CONSTANT_X;
	public static final int INITIAL_YSCALE = INITIAL_XSCALE;
	
	// The minimum zooming for the graphing calculator.
	public static final int MINIMUM_ZOOM_X = INITIAL_XSCALE;
	public static final int MINIMUM_ZOOM_Y = INITIAL_YSCALE;
	
	// A reference back to the graphing engine which is using this.
	private GraphingEngine graphingEngine;
	
	private double xScale, yScale;
	private double xTranslation, yTranslation;
	
	public ScalingManager(GraphingEngine graphingEngine) {
		this.graphingEngine = graphingEngine;
		reset();
	}
	
	/**
	 * Resets the scaling and translation to default.
	 */
	public void reset() {
		xScale = INITIAL_XSCALE;
		yScale = INITIAL_YSCALE;
		xTranslation = 0;
		yTranslation = 0;
	}
	
	/**
	 * Transforms a point according to the current scaling.
	 * @param p The point to be transformed.
	 * @return The transformed point.
	 */
	public Point transformedPoint(Point p) {
		double[] p1 = getFinalisedXandY(p.x, p.y);
		return new Point(p1[0], p1[1]);
	}
	
	/**
	 * Gets the transformed, centred x and y position.
	 * @param x The x-position.
	 * @param y The y-position.
	 * @return A 2-element double array representing the new point.
	 */
	public double[] getFinalisedXandY(double x, double y) {
		double[] xy = getTransformedXandY(x, y);
		int w = graphingEngine.getGraphingPanel().getWidth() / 2;
		int h = graphingEngine.getGraphingPanel().getHeight() / 2;
		return new double[]{xy[0] + w, xy[1] + h};
	}
	
	/**
	 * Gets the centre of the graphing panel.
	 * @return The centre of the graphing panel.
	 */
	public int[] getCentre() {
		int w = graphingEngine.getGraphingPanel().getWidth();
		int h = graphingEngine.getGraphingPanel().getHeight();
		return new int[]{w / 2, h / 2};
	}
	
	public double[] getTransformedXandY(double x, double y) {
		return new double[]{getTranslatedX(getScaledX(x)), getTranslatedY(getScaledY(y))};
	}
	
	public double[] getScaledXandY(double x, double y) {
		return new double[]{getScaledX(x), getScaledY(y)};
	}
	
	public double[] getTranslatedXandY(double x, double y) {
		return new double[]{getTranslatedX(x), getTranslatedY(y)};
	}
	
	public double getScaledX(double x) {
		return x * xScale;
	}
	
	public double getScaledY(double y) {
		return -y * yScale;
	}
	
	public double getTranslatedX(double x) {
		return x + xTranslation;
	}
	
	public double getTranslatedY(double y) {
		return y + yTranslation;
	}
	
	public double getxScale() {
		return xScale;
	}
	
	public void setxScale(double xScale) {
		this.xScale = xScale;
	}
	
	public double getyScale() {
		return yScale;
	}
	
	public void setyScale(double yScale) {
		this.yScale = yScale;
	}
	
	public double getxTranslation() {
		return xTranslation;
	}
	
	public void setxTranslation(double xTranslation) {
		this.xTranslation = xTranslation;
	}
	
	public double getyTranslation() {
		return yTranslation;
	}
	
	public void setyTranslation(double yTranslation) {
		this.yTranslation = yTranslation;
	}
	
	public double getResolution() {
		return graphingEngine.getResolution();
	}
	
	public int getWidth() {
		return graphingEngine.getGraphingPanel().getWidth();
	}
	
	public int getHeight() {
		return graphingEngine.getGraphingPanel().getHeight();
	}
	
}
