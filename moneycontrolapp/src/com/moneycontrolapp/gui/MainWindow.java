/**
 * 
 */
package com.moneycontrolapp.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.moneycontrolapp.constants.Constants;
import com.moneycontrolapp.helpers.MFHelperThread;
import com.moneycontrolapp.models.MutualFunds;
import com.moneycontrolapp.models.MutualFundsList;
import com.moneycontrolapp.utils.Console;


/**
 * @author Z.
 * 
 */
public class MainWindow {
	
	private JPanel mainPanel;
	private StatusBar statusBar;
	private InputPanel inputPanel;
	private OutputPanel outputPanel;
	
	
	
	public void buildMainWindow() {
		
		
		JFrame mainFrame = new JFrame(Constants.APP_NAME + " v" + Constants.APP_VERSION);
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		MutualFundsList.mutualFunds = deserializeMFData();
		
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
		inputPanel = new InputPanel();
		outputPanel = new OutputPanel();
		
		
		fillComboBoxes(MutualFundsList.mutualFunds);
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(inputPanel, c);
		
		c.weighty = 0.9;
		c.gridy = 1;
		mainPanel.add(outputPanel, c);
		
		Console.print("Ready...");
		
		//MFHelperThread mfHelper = new MFHelperThread();
		
		//Thread mfThread = new Thread(mfHelper);
		//mfThread.start();
		
		
		
		
		
	}
	
	private void fillComboBoxes(ArrayList<MutualFunds> mf) {
		inputPanel.fillMFNameComboBox(mf);
		
		inputPanel.fillSectorsComboBox(mf.get(0).getSectors());
		
		
		
	}
	
	private ArrayList<MutualFunds> deserializeMFData() {
		ArrayList<MutualFunds> mf = new ArrayList<MutualFunds>();;
		try {
			FileInputStream fileIn = new FileInputStream("mutualfunds.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			mf = (ArrayList<MutualFunds>) in.readObject();
			
			
			in.close();
			fileIn.close();
			return mf;
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mf;
	}
	
}
