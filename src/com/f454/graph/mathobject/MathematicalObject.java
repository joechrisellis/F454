package com.f454.graph.mathobject;

import java.awt.Color;
import java.awt.Graphics2D;

import com.f454.graph.ScalingManager;

/**
 * Abstract class which all mathematical objects inherit from.
 * @author Joe Ellis
 *
 */
public abstract class MathematicalObject {
	
	// A reference to the scaling manager so that we can see
	// how scaling will affect rendering.
	protected ScalingManager sm;
	
	protected String name;
	protected String tooltip;
	protected Color color;
	protected boolean visible;
	
	public MathematicalObject(String name, String tooltip, Color color, ScalingManager sm) {
		this.sm = sm;
		this.name = name;
		this.tooltip = tooltip;
		this.color = color;
		visible = true;
	}
	
	/**
	 * Renders the mathematical object using the parameter 'g'.
	 * @param g The Graphics2D object used for rendering.
	 */
	public abstract void render(Graphics2D g);
	
	/**
	 * Reinitialises the mathematical object. For example, for simple
	 * functions, this will involve creating all of the points for future
	 * rendering.
	 */
	public void reinit() {}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getExpression() {
		return tooltip;
	}

	public void setExpression(String expression) {
		this.tooltip = expression;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
}
