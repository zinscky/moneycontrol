/**
 * 
 */
package com.moneycontrolapp.models;

/**
 * @author Z.
 *
 */
public class Stocks {

	private String stockName;
	private double stockValue;
	private double stockQty;
	private double stockPercentage;
	
	public Stocks() { }
	
	
	/**
	 * <b>Constructor</b>
	 * <p>Builds the stock with given params.
	 * @param stockName
	 * @param stockValue
	 * @param stockQty
	 * @param stockPercentage
	 */
	public Stocks(String stockName, double stockValue, 
			double stockQty, double stockPercentage) {
		this.stockName = stockName;
		this.stockValue = stockValue;
		this.stockQty = stockQty;
		this.stockPercentage = stockPercentage;
	}
	
	/**
	 * <b>getStockName</b>
	 * <pre><code>public String getStockName()</code></pre>
	 * <p> Returns the name of the stocks.
	 * @return Name of the stock.
	 */
	public String getStockName() {
		return stockName;
	}
	
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	
	public double getStockValue() {
		return stockValue;
	}
	
	public void setStockValue(double stockValue) {
		this.stockValue = stockValue;
	}
	
	public double getStockQty() {
		return stockQty;
	}
	
	public void setStockQty(double stockQty) {
		this.stockQty= stockQty;
	}
	
	public double getStockPercentage() {
		return stockPercentage;
	}
	
	public void setStocksPercentage(double stockPercentage) {
		this.stockPercentage = stockPercentage;
	}
	
}
