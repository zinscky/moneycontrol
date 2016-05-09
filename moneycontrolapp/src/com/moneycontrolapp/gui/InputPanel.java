/**
 * 
 */
package com.moneycontrolapp.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.moneycontrolapp.models.MutualFunds;
import com.moneycontrolapp.models.MutualFundsList;
import com.moneycontrolapp.models.Sectors;

/**
 * @author Z.
 *
 */
public class InputPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> mfName;
	private JComboBox<String> sectorName;
	private JButton btnSubmit;
	
	
	
	public InputPanel() {
		super();
		buildInputPanel();
	}
	
	private void buildInputPanel() {
		mfName = new JComboBox<String>();
		sectorName = new JComboBox<String>();
		btnSubmit = new JButton("SUBMIT");
		
		mfName.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int mfIndex = mfName.getSelectedIndex();
				
				fillSectorsComboBox(MutualFundsList.mutualFunds.get(mfIndex).getSectors());
				
			}
			
		});
		
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSubmitClicked(e);
			}
		});
		
		this.add(mfName);
		this.add(sectorName);
		this.add(btnSubmit);
	}
	
	
	public void fillMFNameComboBox(ArrayList<MutualFunds> mutualFunds) {
		for(int i = 0; i <mutualFunds.size(); i++) {
			mfName.addItem(mutualFunds.get(i).getMFName());
		}
	}
	
	public void fillSectorsComboBox(ArrayList<Sectors> sectors) {
		sectorName.removeAllItems();
		for(int i = 0; i <sectors.size(); i++) {
			sectorName.addItem(sectors.get(i).getSectorName());
		}
	}
	
	private void btnSubmitClicked(ActionEvent e) {
		MyTable table = new MyTable();
		int mfIndex = mfName.getSelectedIndex();
		String title = MutualFundsList.mutualFunds.get(mfIndex).getMFName() 
				+ ": " + MutualFundsList.mutualFunds.get(mfIndex).getSectors().get(sectorName.getSelectedIndex()).getSectorName();
		
		table.buildTable(MutualFundsList.mutualFunds.get(mfIndex).getSectors().get(sectorName.getSelectedIndex()),
				title);
		
	}

}
