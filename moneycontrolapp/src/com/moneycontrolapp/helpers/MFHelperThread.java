package com.moneycontrolapp.helpers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.moneycontrolapp.constants.Constants;
import com.moneycontrolapp.models.MutualFunds;
import com.moneycontrolapp.models.Sectors;
import com.moneycontrolapp.models.Stocks;
import com.moneycontrolapp.utils.Console;




public class MFHelperThread implements Runnable {
	private String url;
	private ArrayList<MutualFunds> mutualFunds;
	
	
	public MFHelperThread() {
		url = Constants.MF_URL;
		
	}

	@Override
	public void run() {
		//Console.print("Getting mutual funds names. Please wait...");
		getMFNameAndLink();
		try {
			serializeMutualFunds();
		} catch (IOException e) {
			Console.print(e.getMessage());
		}
		
	} 
	
	private void serializeMutualFunds() throws IOException {
		
		 FileOutputStream fileOut = new FileOutputStream("mutualfunds.ser");
		 ObjectOutputStream out = new ObjectOutputStream(fileOut);
		 out.writeObject(mutualFunds);
		 out.close();
		 fileOut.close();
		 Console.print("Serialized data is saved in ./mutualfunds.ser file");
	}
	
	/**
	 * Reads the list of all Mutual Funds and their corresponding links.
	 * Link will have to be parsed and reformatted.
	 */
	public void getMFNameAndLink() {
		Console.print("Getting mutual funds names and codes.");
		WebHelper helper = new WebHelper();
				
		helper.readWebPage(url);
		mutualFunds = helper.parseMutualFundsHTML();
		
		
		//For all mutual funds get the sectors and stocks data.
		for(int i = 0; i < mutualFunds.size(); i++) {
			
			Console.print(Constants.LINE_BREAK);
			Console.print("Getting Sector data for: " + mutualFunds.get(i).getMFName());
			Console.print(Constants.LINE_BREAK);
			Console.print(Integer.toString(mutualFunds.size() - i) + " mutualfunds left");
			
			//Create url for sectors data from mutual fund data.
			url = Constants.SECTOR_URL + mutualFunds.get(i).getMFCode();
			ArrayList<Sectors> sectorsList = getSectorData(url, mutualFunds.get(i).getMFName());
			
			
			
			if(sectorsList.size() == 0) { //if sector table is empty
				mutualFunds.remove(i);
			} else {
				mutualFunds.get(i).setSectors(sectorsList);
			}
		}
		
		//return mutualFunds;
	}
	
	/**
	 * Gets sector data. Then it fills sectors objects and stocks objects.
	 * Run this for every Mutual Funds to populate all mutual funds data.
	 * @param sectorUrl
	 * @param mfName
	 * @return Sectors arraylist. if empty then returns empty list.
	 */
	public ArrayList<Sectors> getSectorData(String sectorUrl, String mfName) {
		WebHelper helper = new WebHelper();
		ArrayList<String> results;
		helper.readWebPage(sectorUrl);
		results = helper.parseSectorsHTML();
		
		ArrayList<Sectors> sectors = new ArrayList<Sectors>();
		
		if(results.size() == 4) {
			Console.print("No data available.");
			return sectors;
		}
		
		int currentSectorNumber = 0;
		//loop to get all Sectors data
		for(int i = 4; i < results.size(); i += 4) {
			//these sectors have no stocks
			if(results.get(i+1).equals("--")) {
				//currentSectorNumber++;
				//Sectors sector = new Sectors();
				//sector.setSectorName(results.get(i));
				//sector.setValue(-1);
				//sector.setPercentage(Double.parseDouble(results.get(i+3)));
				//sectors.add(sector);
				continue;
			}
			//check if current record is a sector
			if(results.get(i+2).equals("--")) {
				if(results.get(i+1).equals("")) { //value field is empty
					currentSectorNumber++;
					Sectors sector = new Sectors();
					sector.setSectorName(results.get(i));
					sector.setValue(-1);
					sector.setPercentage(Double.parseDouble(results.get(i+3)));
					sectors.add(sector);
				} else {
					currentSectorNumber++;
					Sectors sector = new Sectors();
					sector.setSectorName(results.get(i));
					sector.setValue(Double.parseDouble(results.get(i+1)));
					sector.setPercentage(Double.parseDouble(results.get(i+3)));
					sectors.add(sector);
				}
			}
			else { //current record is a company stock
				
				String result = results.get(i) + " , " + results.get(i+1) + 
						" , " + results.get(i+2) + " , " + results.get(i+3);
				//Console.print(result);
				
				//value and quantity not specified
				if(results.get(i+1).equals("-") || results.get(i+2).equals("-")) {
					Stocks stock = new Stocks();
					stock.setStockName(results.get(i));
					stock.setStockValue(-1);
					stock.setStockQty(-1);
					stock.setStocksPercentage(Double.parseDouble(results.get(i+3)));
					sectors.get(currentSectorNumber-1).setStocks(stock);
				} else {
					Stocks stock = new Stocks();
					stock.setStockName(results.get(i));
					stock.setStockValue(Double.parseDouble(results.get(i+1)));
					
					String qty = convertToNumber(results.get(i+2));
					
					stock.setStockQty(Double.parseDouble(qty));
					stock.setStocksPercentage(Double.parseDouble(results.get(i+3)));
					sectors.get(currentSectorNumber-1).setStocks(stock);
				}
			}
		}
		
		return sectors;
	}

	/**
	 * Removes commas from big numbers.
	 * @param num
	 * @return
	 */
	private String convertToNumber(String num) {
		String temp = "";
		for(int i = 0; i < num.length(); i++) {
			if(num.charAt(i) == ',') {
			//DO Nothing. Can be implemented more efficiently.	
			}
			else {
				temp = temp + num.charAt(i);
			}
		}
		return  temp;
	}
}