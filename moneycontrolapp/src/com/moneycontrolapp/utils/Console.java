/**
 * 
 */
package com.moneycontrolapp.utils;

import com.moneycontrolapp.gui.OutputPanel;


/**
 * @author Z.
 *
 */
public class Console {

	public static void print(String msg) {
		OutputPanel.console.append(msg + "\n");
		OutputPanel.console.setCaretPosition(OutputPanel.console.getDocument().getLength());
	}
}
