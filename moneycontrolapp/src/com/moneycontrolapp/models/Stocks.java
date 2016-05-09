/**
 * 
 */
package com.moneycontrolapp.models;

import java.io.Serializable;

/**
 * @author Z.
 *
 */
public class Stocks implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2367826489020558809L;
	private String stockName;
	private double stockValue;
	private double stockQty;
	private double stockPercentage;
	
	public Stocks() { }
	
	
	/**
	 * <b>Constructor</b>
	 * <pre><code>public Stocks(String stockName, double stockValue, 
			double stockQty, double stockPercentage)</code></pre>
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
	
	/**
	 * <b>setStockName</b>
	 * <pre><code>public void setStockName(String stockName)</code></pre>
	 * <p> Sets the name of the stocks.
	 * @param stockName - Name of the stock.
	 */
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	
	/**
	 * <b>getStockValue</b>
	 * <pre><code>public String getStockValue()</code></pre>
	 * <p> Returns the value of the stocks in Rs.
	 * @return Value of the stock in Rs.
	 */
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
