package Agents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Inventory.ListItem;
import java.util.Date;

public class Transaction {
	private String userId, transactionDd;
	private Date date;
	private double totalPrice;
	private ArrayList<ListItem> items;
	
	
	public Transaction(String userId, String transactionDd, Date date, double totalPrice, ArrayList<ListItem> items) {
		super();
		this.userId = userId;
		this.transactionDd = transactionDd;
		this.date = date;
		this.totalPrice = totalPrice;
		this.items = items;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTransactionDd() {
		return transactionDd;
	}

	public void setTransactionDd(String transactionDd) {
		this.transactionDd = transactionDd;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public ArrayList<ListItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<ListItem> items) {
		this.items = items;
	}
	
	
	
	

}
