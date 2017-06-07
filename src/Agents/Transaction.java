package Agents;

import java.awt.List;
import java.util.HashMap;
import java.util.Map;

import Inventory.ListItem;

public class Transaction {
	private String userid, date, id;
	private double totalPrice;
	private Map<String, Integer> items;
	
	public Transaction(String id,String userID, String date,  Map<String, ListItem> virtualCart){
		totalPrice = 0;
		items = new HashMap<>();
		for(String key: virtualCart.keySet()){
			items.put(key, virtualCart.get(key).getAmount());
			totalPrice+=virtualCart.get(key).getProduct().getPrice()*virtualCart.get(key).getAmount();
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
