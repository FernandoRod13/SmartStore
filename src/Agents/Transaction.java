package Agents;

import java.util.ArrayList;
import java.util.Date;

import Inventory.ListItem;

public class Transaction {
	private String userId, transactionId;
	private Date date;
	private double totalPrice;
	private ArrayList<ListItem> items;
	
	
	public Transaction(String userId, String transactionId, Date date, double totalPrice, ArrayList<ListItem> items) {
		super();
		this.userId = userId;
		this.transactionId = transactionId;
		this.date = date;
		this.totalPrice =0;
		this.items = new ArrayList<>();
		
		for(ListItem i: items){
			this.items.add(i);
		}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTransactionDd() {
		return transactionId;
	}

	public void setTransactionDd(String transactionDd) {
		this.transactionId = transactionDd;
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
