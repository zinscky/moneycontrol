/**
 * 
 */
package com.moneycontrolapp.gui;

import java.awt.Dimension;

import javax.swing.JLabel;

/**
 * @author Z.
 *
 */
public class StatusBar extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public StatusBar(Dimension d) {
		super();
		this.setPreferredSize(d);
	}
	
	public void setMessage(String msg) {
		this.setText(" " + msg);
	}
	
	
}
