package com.joechrisellis.f454.graphing;

public class ScalingManager {
	
	private GraphingEngine graphingEngine;
	private double xScale, yScale;
	private double xTranslation, yTranslation;
	private double resolution = 0.5;
	
	public ScalingManager(GraphingEngine graphingEngine) {
		this.graphingEngine = graphingEngine;
		xScale = 1;
		yScale = 1;
		xTranslation = graphingEngine.getGraphingPanel().getWidth() / 2;
		yTranslation = graphingEngine.getGraphingPanel().getHeight() / 2;
	}
	
	public double[] getCentredXandY(double x, double y) {
		double[] xy = getTransformedXandY(x, y);
		int w = graphingEngine.getGraphingPanel().getWidth() / 2;
		int h = graphingEngine.getGraphingPanel().getHeight() / 2;
		return new double[]{xy[0] + w, xy[1] + h};
	}
	
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
		return resolution;
	}
	
	public void setResolution(double resolution) {
		this.resolution = resolution;
	}
	
	public int getWidth() {
		return graphingEngine.getGraphingPanel().getWidth();
	}
	
	public int getHeight() {
		return graphingEngine.getGraphingPanel().getHeight();
	}
	
}
