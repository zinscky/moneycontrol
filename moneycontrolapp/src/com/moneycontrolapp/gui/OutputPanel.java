/**
 * 
 */
package com.moneycontrolapp.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Z.
 *
 */
public class OutputPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JTextArea console;
	private JScrollPane scrollPane;
	
	public OutputPanel() {
		super();
		buildOutputPanel();
	}
	
	private void buildOutputPanel() {
		console = new JTextArea();
		console.setForeground(Color.WHITE);
		console.setBackground(Color.BLACK);
		
		scrollPane = new JScrollPane(console);
		
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder("Console"));
		this.add(scrollPane);
	}

	
}
