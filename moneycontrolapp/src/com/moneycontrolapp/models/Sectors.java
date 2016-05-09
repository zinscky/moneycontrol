/**
 * 
 */
package com.moneycontrolapp.models;

import java.io.Serializable;
import java.util.ArrayList;

import com.moneycontrolapp.utils.Console;


/**
 * @author Z.
 *
 */
public class Sectors implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4561685745402203632L;
	private String sectorName;
	private ArrayList<Stocks> stocks;
	private double value;
	private double percentage;

	public Sectors() {
		stocks = new ArrayList<Stocks>();
	}
	
	
	public void showStocks() {
		for(int i = 0; i < stocks.size(); i++) {
			String msg = String.format("%-20s %-10.2f %-10.2f %-10.2f", stocks.get(i).getStockName(), 
					stocks.get(i).getStockValue(), stocks.get(i).getStockQty(), 
					stocks.get(i).getStockPercentage());
			
			Console.print(msg);
		}
	}
	
	
	public ArrayList<Stocks> getStocks() {
		return stocks;
	}
	
	public void setStocks(Stocks stock) {
		stocks.add(stock);
	}
	
	public void setStocks(ArrayList<Stocks> stocks) {
		this.stocks = stocks;
	}
	
	public String getSectorName() {
		return sectorName;
	}
	
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public double getPercentage() {
		return percentage;
	}
	
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
}
