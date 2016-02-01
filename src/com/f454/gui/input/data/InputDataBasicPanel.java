package com.f454.gui.input.data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.f454.graph.mathobject.basic.Point;

public class InputDataBasicPanel extends JScrollPane {
	
	protected ArrayList<SinglePointInputPanel> points;
	
	private JPanel panel;
	private JButton add;
	protected JButton ok;
	
	public InputDataBasicPanel(JButton ok) {
		super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		this.ok = ok;
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		points = new ArrayList<SinglePointInputPanel>();
		points.add(new SinglePointInputPanel(this));
		
		add = new JButton("+");
		add.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				addNewPoint();
			}
			
		});
		
		refresh();
		setViewportView(panel);
		
	}
	
	public ArrayList<Point> getPoints() {
		ArrayList<Point> realPoints = new ArrayList<Point>();
		for(SinglePointInputPanel point : points) {
			String x = point.getXVal(), y = point.getYVal();
			realPoints.add(new Point(Double.parseDouble(x), Double.parseDouble(y)));
		}
		
		return realPoints;
	}
	
	private void addNewPoint() {
		points.add(new SinglePointInputPanel(this));
		refresh();
	}
	
	public void refresh() {
		panel.removeAll();
		
		ListIterator<SinglePointInputPanel> itr = points.listIterator();
		while(itr.hasNext()) {
			SinglePointInputPanel p = itr.next();
			if(p.isRemoved()) {
				itr.remove();
			} else {
				panel.add(p);
			}
		}
		
		for(SinglePointInputPanel point : points) {
			if(!point.isRemoved()) {
				panel.add(point);
			}
		}
		
		panel.add(add);
		
		panel.revalidate();
		panel.repaint();		
		revalidate();
		repaint();
	}
	
	public void updateOkButton() {
		for(SinglePointInputPanel point : points) {
			if(!point.hasValidSyntax()) {
				ok.setEnabled(false);
				return;
			}
		}
		ok.setEnabled(true);
	}
		
}
