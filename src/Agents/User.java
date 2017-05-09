package Agents;

import java.util.HashMap;
import java.util.Map;

import Inventory.InventoryItem;
import Inventory.InventoryManager;
import Inventory.Item;
import Inventory.ListItem;
import Inventory.Location;

public class User {
	private String id;
	private String username;
	private Map<String, ListItem> virtualCart;
	private Map<String, ListItem> groceryList;
	
	public User(String id, String username) {
		super();
		this.id = id;
		this.username = username;
		this.virtualCart = new HashMap<>();
		this.groceryList = new HashMap<>();
	}
	
	public void takeItem(Location location, int amount) throws Exception{
		InventoryItem invItem = InventoryManager.getInstance().findItem(location);
		//not sure yet
		if(invItem==null) throw new Exception("Empty Container?");
		
		//not null at this point
		Item item = invItem.getItem();
		InventoryManager.getInstance().requestInventory(true, location, amount);
		ListItem litem = null;
		
		//if Item is in GroceryList
		if(groceryList.containsKey(item.getId())){
			litem = groceryList.get(item.getId());
			litem.setAmount(litem.getAmount() - amount); //changeAmount
			if(litem.getAmount()<=0) groceryList.remove(item.getId()); //took what he needed
		}
		
		if(litem == null) litem = new ListItem(item, amount); //item asn't in groceryList
		//if item was not in cart adds it
		
		if(!virtualCart.containsKey(item.getId())) {
			virtualCart.put(item.getId(), new ListItem(item, amount));
		}
		else {
			virtualCart.get(item.getId()).setAmount(virtualCart.get(item.getId()).getAmount()+amount); //else updates amount
		}
		
		
	}
	//from virtual cart to Store
	public void returnItem(Item item, Location location, int amount) throws Exception{
		if(virtualCart.containsKey(item.getId())){
			ListItem litem = virtualCart.get(item.getId());
			InventoryManager.getInstance().addInventory(true, location, amount);
			litem.setAmount(litem.getAmount() - amount);
			if(litem.getAmount()<=0) 
				virtualCart.remove(item.getId());
		}
		else throw new Exception("Item wasn't in cart");
		
		
	}
	
	public void addItemToList(Item product, int amount) {
		if(groceryList.containsKey(product.getId())){
			ListItem litem = groceryList.get(product.getId());
			litem.setAmount(litem.getAmount() + amount);
		}
		else{
			groceryList.put(product.getId(), new ListItem(product, amount));
		}
		
		
	}
	public void removeItemFromList(Item product, int amount) throws Exception {
		if(!groceryList.containsKey(product.getId())) throw new Exception("Produc wasn't in list");
		ListItem litem = groceryList.get(product.getId());
		litem.setAmount(litem.getAmount()-amount);
		if(litem.getAmount()<=0) groceryList.remove(product.getId());
		
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

	public Map<String, ListItem> getVirtualCart() {
		return virtualCart;
	}

	public void setVirtualCart(Map<String, ListItem> virtualCart) {
		this.virtualCart = virtualCart;
	}

	public Map<String, ListItem> getGroceryList() {
		return groceryList;
	}

	public void setGroceryList(Map<String, ListItem> groceryList) {
		this.groceryList = groceryList;
	}
	
	
	
}
