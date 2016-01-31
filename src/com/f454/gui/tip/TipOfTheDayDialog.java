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

public class TipOfTheDayDialog extends JDialog {
	
	public static final String TITLE = "Tip of the Day";
	public static final int HEIGHT = 400;
	public static final int WIDTH = (int) (MainWindow.GOLDEN_RATIO * HEIGHT);
	
	public static final String PATH = System.getProperty("user.home") + File.separator + ".nototd";
	
	private static final String TIPS[] = {
		"Press <ctrl+enter> in the graphing calculator to enter a simple function.",
		"Press <shift + enter> in the graphing calculator to enter a simple function.",
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
		"Try plotting the parametric equation x(t) = t * sin(t), y(t) = t * cos(t)",
		"You can change the colour of any mathematical object using the advanced tab in its input menu.",
		"Hover over a mathematical object in the left-hand panel to highlight it on the graphing panel for easy recognition.",
		"Hover over a mathematical object in the left-hand panel to see important information about it.",
		"If you want to save a graph, don't screenshot it! Try the File->Export command.",
		"You can change the range and domain for both parametric equations and functions using their input dialogs.",
		"If you're unsure what a button or menu does, hover over it! Most menus have a tool tip attached.",
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
		tipDisplay.setFont(new Font("Verdana", Font.BOLD, 20));
		
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
