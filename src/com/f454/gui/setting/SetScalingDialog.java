package com.f454.gui.setting;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.f454.graph.ScalingManager;
import com.f454.gui.mainwindow.MainWindow;

/**
 * The dialog that the user can use to change the scaling of the axes relative
 * to one another.
 * @author Joe Ellis
 *
 */
public class SetScalingDialog extends JDialog {
	
	public static final String TITLE = "Axes Scaling Settings";
	
	private JSpinner x, y;
	private JButton ok, cancel;
	
	public SetScalingDialog() {
		super();
		
		setTitle(TITLE);
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		JPanel panel1 = new JPanel();
		
		x = new JSpinner(
			new SpinnerNumberModel(1, 1, 100, 1)
		);
		
		y = new JSpinner(
			new SpinnerNumberModel(1, 1, 100, 1)
		);
		
		// increase the size of the spinners
		for(JSpinner s : new JSpinner[] {x, y}) {
			JComponent editor = s.getEditor();
			JFormattedTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
			tf.setColumns(8);
		}
		
		panel1.add(new JLabel("x"));
		panel1.add(x);
		panel1.add(new JLabel(":", JLabel.CENTER));
		panel1.add(y);
		panel1.add(new JLabel("y"));
		
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				MainWindow m = MainWindow.getInstance();
				ScalingManager sm = m.getGraphingPanel().getGraphingEngine().getScalingManager();
				
				int xv = (int) (x.getValue());
				int yv = (int) (y.getValue());

				double xToY = (int) (Math.pow(xv / yv, xv > yv ? 1 : -1));
				sm.setxScale(ScalingManager.INITIAL_XSCALE * xv);
				sm.setyScale(ScalingManager.INITIAL_YSCALE * yv);
				
				dispose();
			} 
			
		});
		
		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		
		panel2.add(ok);
		panel2.add(cancel);
		
		add(new JLabel("Use the spinner below to set the ratio of scaling for the axes."));
		add(panel1);
		add(panel2);

		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}
