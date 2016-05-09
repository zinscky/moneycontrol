/**
 * 
 */
package com.moneycontrolapp.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.moneycontrolapp.models.Sectors;
import com.moneycontrolapp.models.Stocks;



/**
 * @author Z.
 *
 */
public class MyTable {
	private JTable tbl;
	private JPanel mainPanel;
	private JScrollPane scrollPane;
	
	
	
	
	
	public void buildTable(Sectors sector, String title) {
		JFrame frame = new JFrame(title);
		
		mainPanel = new JPanel(new BorderLayout());
		
		buildTableData(sector);
		
		
		scrollPane = new JScrollPane(tbl);
		
		mainPanel.add(scrollPane);
		frame.getContentPane().add(mainPanel);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setVisible(true);
		
		
		
	}
	
	private void buildTableData(Sectors sector) {
		
		String[] columnNames = {"Stock", "Value in RS Cr", "Qty", "percentage"};
		
		ArrayList<Stocks> stocks = sector.getStocks();
				
		
		DefaultTableModel tableModel= new DefaultTableModel();
		
		tableModel.addColumn(columnNames[0]);
		tableModel.addColumn(columnNames[1]);
		tableModel.addColumn(columnNames[2]);
		tableModel.addColumn(columnNames[3]);

		Object[] row1 = new Object[4];
		
		for(int i = 0; i < stocks.size(); i++) {
			
			row1[0] = stocks.get(i).getStockName();
			row1[1] = Double.toString(stocks.get(i).getStockValue());
			row1[2] = Double.toString(stocks.get(i).getStockQty());
			row1[3] = Double.toString(stocks.get(i).getStockPercentage());
					
			tableModel.addRow(row1);
			
			
		}
		
		tbl = new JTable(tableModel);
	}
}
