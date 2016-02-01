package com.f454.graph.mathobject.special;

import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.f454.graph.mathobject.MathematicalObject;
import com.f454.graph.mathobject.basic.BasicMathematicalObject;

/**
 * When exporting the mathematical object, it is desirable to show a 'key' in
 * the top left of the image mapping the colours of mathematical objects to their
 * corresponding equations. This class creates and renders a key.
 * @author Joe Ellis
 *
 */
public class Key extends MathematicalObject {
	
	private static final Font FONT = new Font(null, Font.BOLD, 12);
	
	private ArrayList<MathematicalObject> mathObjects;
	
	public Key(ArrayList<MathematicalObject> mathObjects) {
		// Passing in null to super because colour and scaling manager not required.
		super("Key", "The key on the graphing panel", null, null);
		this.mathObjects = mathObjects;
		this.visible = false; // invisible by default
		this.removable = false;
	}
	
	private static final int X = 30;
	private static final int Y = -17;
	private static final int Y_STEP = 17;
	
	private static final int SQUARE_SIZE = 8;
	private static final int SQUARE_X = X - SQUARE_SIZE - 5;
	private static final int SQUARE_Y = 7;
	
	public void render(Graphics2D g) {
		g.setFont(FONT);
		
		for(int i = 0; i < mathObjects.size(); i++) {
			
			MathematicalObject o = mathObjects.get(i);
			if(!(o instanceof BasicMathematicalObject)) continue;
			
			g.setColor(o.getColor());
			g.fillRect(SQUARE_X, Y + Y_STEP * i - SQUARE_Y, SQUARE_SIZE, SQUARE_SIZE);
			
			g.drawString(o.getExpression(), X, Y + Y_STEP * i);
		}
		
	}
	
}
