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
		"Press <enter> in the graphing calculator to enter a simple function.",
		"Press <shift + enter> in the graphing calculator to enter a simple function.",
		"Press <shift + c> in the graphing calculator to input a circle.",
		"Check the checkbox below if you don't want to see these at startup!",
		"Adjust the resolution using the settings dialog to fit your needs.",
		"You can toggle the visibility of an object using the checkboxes in the left hand panel.",
		"See additional options by right-clicking objects in the left hand panel",
		"Right clicking the axes entry allows you to enable/disable gridlines, as well as plot the axes in terms of pi.",
		"When inputting simple functions, try using functions such as sin, cos, tan and abs.",
	};
	
	private int currentTip;
	
	private JTextArea tipDisplay;
	
	private JCheckBox showAtStartup;
	
	private JButton nextTip;
	private JButton previousTip;
	private JButton close;
	
	public TipOfTheDayDialog() {
		super();
		
		setTitle(TITLE);
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		
		currentTip = new Random().nextInt(TIPS.length);
		tipDisplay = new JTextArea(TIPS[currentTip]);
		tipDisplay.setLineWrap(true);
		tipDisplay.setWrapStyleWord(true);
		tipDisplay.setFont(new Font("Verdana", Font.BOLD, 20));
		
		close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		
		showAtStartup = new JCheckBox("Show Tip of the Day at Startup", true);
		
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
		
		if(!showAtStartup.isSelected()) {
			File f = new File(PATH);
			try {
				f.createNewFile();
			} catch (IOException err) {
				err.printStackTrace();
			}
		}
	}

}
