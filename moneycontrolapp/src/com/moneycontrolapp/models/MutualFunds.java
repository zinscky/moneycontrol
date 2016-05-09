/**
 * 
 */
package com.moneycontrolapp.models;

import java.io.Serializable;
import java.util.ArrayList;



/**
 * @author Z.
 *
 */
public class MutualFunds implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5582114291210260269L;
	private String mfName;
	private String mfCode;
	private ArrayList<Sectors> sectors;
	
	public MutualFunds() {
		sectors = new ArrayList<Sectors>();
	}
	
	public String getMFName() {
		return mfName;
	}
	
	public void setMFName(String mfName) {
		this.mfName = mfName;
	}
	
	public String getMFCode() {
		return mfCode;
	}
	
	public void setMFCode(String mfCode) {
		this.mfCode = mfCode;
	}
	
	public ArrayList<Sectors> getSectors() {
		return sectors;
	}
	
	public void setSectors(Sectors sector) {
		sectors.add(sector);
	}
	
	public void setSectors(ArrayList<Sectors> sectors) {
		this.sectors = sectors;
	}

}
