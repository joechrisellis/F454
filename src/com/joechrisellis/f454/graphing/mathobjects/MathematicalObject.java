package com.joechrisellis.f454.graphing.mathobjects;

import java.awt.Color;
import java.awt.Graphics2D;

import com.joechrisellis.f454.graphing.GraphingEngine;

public abstract class MathematicalObject {
		
	protected GraphingEngine graphingEngine;
	protected String name;
	protected Color color;
	protected boolean visible;
	
	public MathematicalObject(String name, Color color, GraphingEngine graphingEngine) {
		this.graphingEngine = graphingEngine;
		this.name = name;
		this.color = color;
		visible = true;
	}
	
	public abstract void render(Graphics2D g);
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
}
