/**
 * 
 */
package com.moneycontrolapp.helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.moneycontrolapp.models.MutualFunds;
import com.moneycontrolapp.utils.Console;



/**
 * @author Z.
 *
 */
public class WebHelper {
private String webContent;
	
	public String getWebContent() {
		return webContent;
	}
	
	public void readWebPage(String url) {
		try {
			URL u = new URL(url);
			
			URLConnection conn = u.openConnection();
			//Long length = conn.getContentLengthLong();
			//Console.print("Size of html = " + Long.toString(length));
			conn.connect();
			
			InputStreamReader isr = new InputStreamReader(conn.getInputStream());
			BufferedReader reader = new BufferedReader(isr);
			String str;
			while ((str = reader.readLine()) != null) {
				webContent += str;
			}
			reader.close();
		} catch (SocketTimeoutException e1) {
			Console.print("Webpage read error. timeout: " + e1.getMessage());
		} catch (IOException e2) {
			Console.print("IOEXception: " + e2.getMessage());
		}
		
	}
	
	public ArrayList<MutualFunds> parseMutualFundsHTML() {
		ArrayList<MutualFunds> mutualFunds = new ArrayList<MutualFunds>();
		Document doc = Jsoup.parse(webContent);
		
		//Elements elements = doc.getElementsByTag("table");
		
		//String htmlSource = elements.html();
		//doc = Jsoup.parse(htmlSource);
		//elements = doc.getElementsByClass("gry_t thdata");
		
		//Console.print("Getting by table class");
		
		//htmlSource = elements.html();
		Elements tbody = doc.select("tbody");
		
		Element mfTable = tbody.get(2);
		
		int i = 0; //using a counter to skip first 2 rows.
		for (Element row : mfTable.select("tr")) {
	    	Elements tds = row.select("td");
	    	
	    	if(i > 2 && !tds.get(0).text().equals("CATEGORY AVERAGE")) {
	    		Element link = tds.get(0).select("a").first();
	    		String absLink = "href";
	    		absLink = link.attr("href"); //get the link from href tag 
	    		//Console.print(tds.get(0).text() + " -- Code: " + getMFCodeFromLink(absLink));
	    		
	    		MutualFunds mutualFund = new MutualFunds();
	    		mutualFund.setMFName(tds.get(0).text());
	    		mutualFund.setMFCode(getMFCodeFromLink(absLink));
	    		mutualFunds.add(mutualFund);
	    	}
	    	i++;
	    }
		
		//Console.print("End of parsing");
		return mutualFunds;
	}
	
	public ArrayList<String> parseSectorsHTML() {
		Document doc = Jsoup.parse(webContent);
		String path = "webcontent.txt";
		PrintWriter out;
		ArrayList<String> results = new ArrayList<String>(); //array of all sectors and stocks 
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(path)));
			
			Elements elements = doc.getElementsByClass("MT10");
			String html = elements.html();
			doc = Jsoup.parse(html);
			
			elements = doc.getElementsByTag("table");
			
			html = elements.html();
			for (Element table : doc.select("table")) {
		        for (Element row : table.select("tr")) {
		            Elements tds = row.select("td");
		            for(int i = 0; i < 4; i++) {
		            	//Console.print(tds.get(i).text());
		            	results.add(tds.get(i).text());
		            	
		            }
		        }
		    }
			
			//Console.print(html, OutputPanel.console);
			out.write(html);
			out.close();
			return results;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Console.print(e.getMessage());
		}
		return results;
	}
	
	/**
	 * <pre><code>private String getMFCodeFromLink(String link)</code></pre>
	 * <p>Gets the last 6 characters of the link.
	 * @param link
	 * @return mutual funds 6 character code.
	 * 
	 */
	private String getMFCodeFromLink(String link) {
		String code;
		code = link.substring(link.length() - 6);
		return code;
	}
}
