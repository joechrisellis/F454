package com.f454.gui.input.data;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class InputDataBasicPanel extends JPanel {
	
	private JTable table;
	
	public InputDataBasicPanel(JButton ok) {
		super();
		
		String[] columnNames = {"x", "y"};
		Object[][] data = {
				{new Integer(0), new Integer(0)}
		};
		
		table = new JTable(data, columnNames);
		table.getModel().addTableModelListener(new TableModelListener() {
			
			public void tableChanged(TableModelEvent e) {
				System.out.println("test");
			}
			
		});

		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		add(scrollPane);
		add(new JButton("test"), BorderLayout.SOUTH);
	}
	
}
