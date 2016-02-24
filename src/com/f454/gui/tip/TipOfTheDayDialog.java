package com.f454.gui.tip;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.f454.gui.mainwindow.MainWindow;

/**
 * This dialog allows the user to scan through a collection of useful tips.
 * @author Joe Ellis
 *
 */
public class TipOfTheDayDialog extends JDialog {
	
	public static final String TITLE = "Tip of the Day";
	public static final int HEIGHT = 400;
	public static final int WIDTH = (int) (MainWindow.GOLDEN_RATIO * HEIGHT);
	
	// The path to the file whose existence determines whether this dialog is raised.
	public static final String PATH = System.getProperty("user.home") + File.separator + ".nototd";
	
	// All of the tips that can be displayed by the tip of the day dialog.
	private static final String TIPS[] = {
		"Press <ctrl+enter> in the graphing calculator to enter a simple function.",
		"Press <ctrl + c> in the graphing calculator to input a circle.",
		"Press <ctrl + p> in the graphing calculator to input a parametric equation.",
		"Press <ctrl + d> in the graphing calculator to input a data set.",
		"Uncheck the checkbox below if you don't want to see these at startup!",
		"Adjust the resolution using the settings dialog to fit your needs.",
		"You can toggle the visibility of an object using the checkboxes in the left hand panel.",
		"See additional options by right-clicking objects in the left hand panel",
		"Right clicking the axes entry allows you to enable/disable gridlines, as well as plot the axes in terms of pi.",
		"When inputting simple functions, try using functions such as sin, cos, tan and abs.",
		"You can use variables such as 'pi' and 'e' when inputting simple functions and parametric equations.",
		"Try plotting the parametric equation x(t) = t * sin(t), y(t) = t * cos(t)!",
		"You can change the colour of any mathematical object using the advanced tab in its input menu.",
		"Hover over a mathematical object in the left-hand panel to highlight it on the graphing panel for easy recognition.",
		"Hover over a mathematical object in the left-hand panel to see important information about it.",
		"If you want to save a graph, don't screenshot it! Try the File->Export command.",
		"You can change the range and domain for both parametric equations and functions using their input dialogs.",
		"If you're unsure what a button or menu does, hover over it! Most menus have a tool tip attached.",
		"Exporting an image? Be sure to enable the key in the left-hand panel to attach information on mathematical objects into the image.",
		"It is possible to rename mathematical objects; just right click them in the left-hand panel and select 'Change Label'.",
		"The graphing calculator tries to generate aesthetically pleasing colours. You can change the colour of a mathematical object by right clicking it in the left-hand panel and selecting 'Change Colour'.",
		"Pressing the home button in the bottom panel returns the graphing calculator to its default view.",
		"Variable sliders can be used in the program. Use any of the six variables in your expressions and modify their values in the variable sliders window. You can use this to animate graphs, making them move and change shape.",
		"When you have plotted a line of best fit for a data set, you can return its equation by hovering over the item in the list of mathematical objects.",
		"To find the equation of a straight line connecting two points, create a data set consisting of the points, then plot a line of best fit. Find the equation by hovering over it in the list of mathematical objects.",
		"If you don't want to see labels for the points in a data set, you can make them invisible. Try right clicking the data set in the list of mathematical objects.",
		"Pressing the reset button in the variable sliders menu resets all of the variable sliders back to their default value.",
		"By enabling sticky axes, you ensure that the axes are visible at all times, no matter how far out you scroll. To enable stick axes, right click the axes in the list and select 'Sticky Axes'.",
		"If you want to see where a function intersects y-axis, right click it in the list and tick 'Show y-intercept'.",
	};
	
	private int currentTip;
	
	private JTextArea tipDisplay;
	
	private JCheckBox showAtStartup;
	
	private JButton nextTip;
	private JButton previousTip;
	private JButton close;
	
	public TipOfTheDayDialog() {
		this(false);
	}
	
	public TipOfTheDayDialog(boolean raisedExplicitly) {
		super();
		
		setTitle(TITLE);
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		
		currentTip = new Random().nextInt(TIPS.length);
		tipDisplay = new JTextArea(TIPS[currentTip]);
		tipDisplay.setEditable(false);
		tipDisplay.setLineWrap(true);
		tipDisplay.setWrapStyleWord(true);
		tipDisplay.setFont(new Font("Verdana", Font.BOLD, 16));
		
		close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		
		boolean enabled = raisedExplicitly ? !new File(TipOfTheDayDialog.PATH).exists() : true;
		showAtStartup = new JCheckBox("Show Tip of the Day at Startup", enabled);
		
		nextTip = new JButton("Next Tip");
		nextTip.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				currentTip = (currentTip + 1) % TIPS.length;
				updateTip();
			}
			
		});
		
		previousTip = new JButton("Previous Tip");
		previousTip.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				currentTip = currentTip == 0 ? TIPS.length - 1 : currentTip - 1;
				updateTip();
			}
			
		});
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(3, 1, 4, 4));
		
		buttonsPanel.add(nextTip);
		buttonsPanel.add(previousTip);
		buttonsPanel.add(close);
		
		add(tipDisplay, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.EAST);
		add(showAtStartup, BorderLayout.SOUTH);
		
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void updateTip() {
		tipDisplay.setText(TIPS[currentTip]);
	}
	
	public void dispose() {
		super.dispose();
		
		File f = new File(PATH);
		if(!showAtStartup.isSelected()) {
			try {
				f.createNewFile();
			} catch (IOException err) {
				err.printStackTrace();
			}
		} else {
			f.delete();
		}
	}
	
	/**
	 * Raises the tip of the day dialog if the file ~/.nototd does NOT exist.
	 */
	public static void raiseIfNecessary() {
		if(!new File(TipOfTheDayDialog.PATH).exists()) {
			new TipOfTheDayDialog();
		}
	}

}
