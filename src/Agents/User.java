package Agents;

import java.util.ArrayList;

import Inventory.Item;
import Inventory.ListItem;
import Inventory.Location;

public class User {
	private String id;
	private String username;
	private ArrayList<ListItem> virtualCart;
	private ArrayList<ListItem> groceryList;
	
	public User(String id, String username) {
		super();
		this.id = id;
		this.username = username;
		this.virtualCart = new ArrayList<ListItem>();
		this.groceryList = new ArrayList<ListItem>();
	}
	
	public void takeItem(Location location, int amount){
		
	}
	public void returnItem(Location location, int amount){
		
	}
	
	public void addItemToList(Item product, int amount) {
		
	}
	public void removeItemFromList(Item product, int amount) {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ArrayList<ListItem> getVirtualCart() {
		return virtualCart;
	}

	public void setVirtualCart(ArrayList<ListItem> virtualCart) {
		this.virtualCart = virtualCart;
	}

	public ArrayList<ListItem> getGroceryList() {
		return groceryList;
	}

	public void setGroceryList(ArrayList<ListItem> groceryList) {
		this.groceryList = groceryList;
	}
	
	
	
}
