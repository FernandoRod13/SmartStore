package Agents;
import java.util.ArrayList;

import Inventory.InventoryManager;
import Inventory.Item;
import Inventory.ListItem;

public class User {
	private String id;
	private String name;
	private String lastName;
	private ArrayList<ListItem> virtualCart;
	private ArrayList<ListItem> groceryList;
	private double totalPriceInCart;
	
	
	public User(String id, String name, String lastName) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.totalPriceInCart = 0;
		this.virtualCart = new ArrayList<>();
		this.groceryList = new ArrayList<>();
	}
	
	/**
	 * The user grabs an item, the Sensor send the signal, The System add the item to his virtual cart
	 * and updates the inventory so others users see the changes in case they also are looking that object
	 * @param item
	 * @param qty
	 */
	public void userPickItem(Item item, int qty){

		ListItem newListItem = new ListItem(item,qty);
		virtualCart.add(newListItem);
		InventoryManager.getInstance().userBuysItem(item.getLocation(), qty);
		totalPriceInCart+=item.getRetailPrice()*qty;
		
		//notifies the Sensor which then decides to refill or not
//		InventoryManager.getInstance().notifySensor(item);
	}
	

	/**
	 * The user returns the item, the system put back the item in the iventory
	 * @param item
	 * @param qty
	 */
	public void userReturnAnItem(Item item, int qty){
		
		int indexOfItem = getIndexOfItem(item.getId());
		virtualCart.remove(indexOfItem);
		InventoryManager.getInstance().userReturnsItem(item.getLocation(), qty);
		totalPriceInCart-=item.getRetailPrice()*qty;
		
		//notifies the Sensor which then decides to refill or not
//		InventoryManager.getInstance().notifySensor(item);		

	}
	
	/**
	 * This method is to find an item in the cart, otherwise return -1
	 * @param id
	 * @return
	 */
	public int getIndexOfItem(String id){

		for(int i = 0; i < virtualCart.size() ; i++){

			if(virtualCart.get(i).getItem().getId().equals(id)){
				return i;
			}
		}
		return -1;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	
	public double getCartTotal(){
		return totalPriceInCart;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", lastName=" + lastName + ", virtualCart=" + virtualCart
				+ ", groceryList=" + groceryList + "]";
	}

	
}	
	

