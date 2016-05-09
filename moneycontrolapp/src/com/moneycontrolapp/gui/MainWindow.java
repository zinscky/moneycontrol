/**
 * 
 */
package com.moneycontrolapp.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.moneycontrolapp.constants.Constants;


/**
 * @author Z.
 * 
 */
public class MainWindow {
	
	private JPanel mainPanel;
	private StatusBar statusBar;
	
	
	public void buildMainWindow() {
		JFrame mainFrame = new JFrame(Constants.APP_NAME + " v" + Constants.APP_VERSION);
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		buildMainPanel();
		
		// create the status bar panel and shove it down the bottom of the frame
		statusBar = new StatusBar(new Dimension(mainFrame.getWidth(), 16));
		mainFrame.getContentPane().add(statusBar, BorderLayout.SOUTH);
		statusBar.setMessage("ready");
		
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
	}
	
	private void buildMainPanel() {
		mainPanel = new JPanel(new GridBagLayout());
		
	}
}
