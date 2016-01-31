package com.f454.gui.input;

/**
 * This exception is thrown whenever the user hits cancel on an input dialog.
 * @author Joe Ellis
 *
 */
public class InputCancelledException extends Exception {
	
	public InputCancelledException(String message) {
		super(message);
	}

}
