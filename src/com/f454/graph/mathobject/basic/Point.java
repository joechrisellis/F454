package com.f454.graph.mathobject.basic;

public class Point {
	
	public double x, y;
	
	// This boolean is used to determine whether a line should be drawn from
	// the previous point to this point when working with constructed mathematical objects.
	// If this is true, do NOT draw a line from this point to the next in the list.
	// If this is false, continue as usual.
	// It is required for applications such as applying a range to a function. :)
	public boolean outOfRange;
	
	public Point(double x, double y) {
		this(x, y, false);
	}
	
	public Point(double x, double y, boolean outOfRange) {
		this.x = x;
		this.y = y;
		this.outOfRange = outOfRange;
	}
	
}
