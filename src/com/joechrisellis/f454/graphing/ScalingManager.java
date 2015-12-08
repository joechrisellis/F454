package com.joechrisellis.f454.graphing;

public class ScalingManager {
	
	private GraphingEngine graphingEngine;
	private double xScale, yScale;
	private double xTranslation, yTranslation;
	
	public ScalingManager(GraphingEngine graphingEngine) {
		this.graphingEngine = graphingEngine;
		xScale = -1;
		yScale = 1;
		xTranslation = graphingEngine.getGraphingPanel().getWidth() / 2;
		yTranslation = graphingEngine.getGraphingPanel().getHeight() / 2;
	}
	
	public double[] getScaledXandY(double x, double y) {
		return new double[]{getScaledX(x), getScaledY(y)};
	}
	
	public double[] getTranslatedXandY(double x, double y) {
		return new double[]{getTranslatedX(x), getTranslatedY(y)};
	}
	
	public double getScaledX(double x) {
		return (x + xTranslation) * xScale;
	}
	
	public double getScaledY(double y) {
		return (y + yTranslation) * yScale;
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
	
}
