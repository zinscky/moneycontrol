/**
 * 
 */
package com.moneycontrolapp;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.moneycontrolapp.gui.MainWindow;

/**
 * @author Z.
 *
 */
public class AppLauncher {
public static void main(String[] args) {
		setSystemLookAndFeel();
		MainWindow mainWindow = new MainWindow();
		mainWindow.buildMainWindow();
	}
	
	private  static void setSystemLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(null, "Error", e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
	}
}
