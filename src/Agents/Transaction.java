package Agents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Inventory.ListItem;

public class Transaction {
	private String userid, date, id;
	private double totalPrice;
	private Map<String, Integer> items;
	
	
	public Transaction(String id,String userID, String date, ArrayList<ListItem> vCart){
		this(id,userID, date);
		for(ListItem i: vCart){
			items.put(i.getItem().getId(), i.getAmount());
			totalPrice+=i.getItem().getRetailPrice();
		}
	}
	
	public Transaction(String id,String userID, String date){
		this.setId(id);
		this.setUserid(userID);
		this.setDate(date);
		totalPrice = 0;
		items = new HashMap<>();
	}

	
	public Transaction(ArrayList<ListItem> vCart) {
		// TODO Auto-generated constructor stub
		this("","","");
		for(ListItem i: vCart){
			items.put(i.getItem().getId(), i.getAmount());
			totalPrice+=i.getItem().getRetailPrice();
		}
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Map<String, Integer> getItems() {
		return items;
	}

	public void setItems(Map<String, Integer> items) {
		this.items = items;
	}
	
	

}
