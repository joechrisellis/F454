package com.joechrisellis.f454.graphing.mathobjects;

import java.awt.Graphics2D;

import javax.swing.JCheckBox;

import com.joechrisellis.f454.graphing.GraphingEngine;

public abstract class MathematicalObject {
		
	protected GraphingEngine graphingEngine;
	protected String name;
	protected boolean visible;
	
	public MathematicalObject(String name, GraphingEngine graphingEngine) {
		this.graphingEngine = graphingEngine;
		this.name = name;
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
