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
	
	/**
	 * Takes an item from the store's inventory and adds it to virtual Cart
	 * @param location A location object that represents the position of the Product in the store
	 * @param amount the quantity of products taken from the store
	 * @throws Exception This will change later, happens when inventory manager' method find returns null
	 */
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
			//else updates amount
			virtualCart.get(item.getId()).setAmount(virtualCart.get(item.getId()).getAmount()+amount); 
		}
		
		
	}
	/**
	 * Returns a product to the a store container
	 * @param item The product that will be returned
	 * @param location The position in the store where the Item will be located
	 * @param amount the quantity of items being returned
	 * @throws Exception 
	 */
	public void returnItem(Item item, Location location, int amount) throws Exception{
		//if item is in cart
		if(virtualCart.containsKey(item.getId())){
			ListItem litem = virtualCart.get(item.getId());
			if(amount>litem.getAmount())  //if quantity exceeds the available items in cart
				amount = litem.getAmount(); //set amount to the available amount
			
			if(!item.getId().contentEquals(InventoryManager.getInstance().findItem(location).getItem().getId())) 
				throw new Exception("Wrong location for cart Item");
			//takes item from cart
			litem.setAmount(litem.getAmount() - amount);
			if(litem.getAmount()==0) //if item is no longer in cart  
				virtualCart.remove(item.getId()); //removes it from virtual carte
			
			//returns items from cart to the store
			InventoryManager.getInstance().addInventory(true, location, amount);
			
		}
		else throw new Exception("Item wasn't in cart");
		
		
	}
	
	/**
	 * Adds an Item to the grocery list of the User
	 * @param product the item to add to the grocery list
	 * @param amount the quantity of the item that the user needs
	 */
	public void addItemToList(Item product, int amount) {
		//if item is in list already
		if(groceryList.containsKey(product.getId())){
			ListItem litem = groceryList.get(product.getId());
			litem.setAmount(litem.getAmount() + amount); //update the amount
		}
		else{//if item is new in list
			groceryList.put(product.getId(), new ListItem(product, amount)); 
			//add item to list
		}
		
		
	}
	/**
	 * Removes an item from the grocery list
	 * @param product the product that will be removed
	 * @param amount the quantity of the product to be removed
	 * @throws Exception
	 */
	public void removeItemFromList(Item product, int amount) throws Exception {
		if(!groceryList.containsKey(product.getId())) throw new Exception("Product wasn't in list");
		
		ListItem litem = groceryList.get(product.getId());
		if(amount>litem.getAmount()) amount = litem.getAmount(); //if the amount to be removed exceeds the amount in list, set amount to the available amount
		litem.setAmount(litem.getAmount()-amount);//remove amount from list
		if(litem.getAmount()<=0) groceryList.remove(product.getId()); //if item is not in list anymore, remove from grocery list
		
	}
	/**
	 * Returns the number of times an item is in the grocery list
	 * @param i the item that will be counter
	 * @return the number of times an item is in the grocery list
	 */
	public int getAmountInGroceryList(Item i){
		Integer am;
		if(!this.groceryList.containsKey(i.getId())) 
			am = 0;
		else
			am = this.groceryList.get(i.getId()).getAmount();
		
		return am;
	}
	
	/**
	 * Returns the number of times an item is in the virtual cart
	 * @param i the item that will be counter
	 * @return the number of times an item is in the virtual cart
	 */
	public int getAmountInVirtualCart(Item i){
		Integer am;
		if(!this.virtualCart.containsKey(i.getId())) 
			am = 0;
		else
			am = this.virtualCart.get(i.getId()).getAmount();
		return am;
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
