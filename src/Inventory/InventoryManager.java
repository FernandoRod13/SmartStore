package Inventory;
import java.util.ArrayList;

public class InventoryManager {
	private ArrayList<Container> storeInventory;
	private ArrayList<Container> stockInventory;
	
	private static InventoryManager instance = null;
	private InventoryManager(){
		floorInventory = new ArrayList<>();
		stockInventory = new ArrayList<>();
		init();
	}
	
	private void init(){
		floorInventory.add(new Container());
	}
	
	public static InventoryManager getInstance() {
		if(instance == null) instance = new InventoryManager();
		return instance;
	}
	
	//used for add an amount of an existing item in inventory 
	public void addInventory(Boolean inFloor, Location location, int amount){
		//decide what inventory will be managed
		ArrayList<Container> inventory;
		if(inFloor) 
			inventory = floorInventory;
		else 
			inventory = stockInventory;
		
		//set the container
		Container c = inventory.get(location.getContainer());
		
		//set the shelf
		Shelf s;
		if(location.isLeft())
			s  = c.getLeft();
		else 
			s = c.getRight();
		
		//get the InventoryItem
		InventoryItem item = s.getColumn().get(location.getColumn()).items.get(location.getRow());
		item.setAvailable(item.getAvailable() + amount); //add items
		
	}
	
	//used to add a new Item in inventory
	public void addNewInventory(Item i, Boolean inFloor, Location location,int min, int max, int amount) throws Exception {
		//Decide the inventory that will be managed
		ArrayList<Container> inventory;
		if(inFloor) 
			inventory = floorInventory;
		else 
			inventory = stockInventory;
		//set the container
		Container c = inventory.get(location.getContainer());
		//set the Shelf
		Shelf s;
		if(location.isLeft())
			s  = c.getLeft();
		else 
			s = c.getRight();
		
		//if there is space
		if(s.getColumn().get(location.getColumn()).items.size()<3)
			s.getColumn().get(location.getColumn()).items.add(new InventoryItem(i,location, min, max, amount));
		else throw new Exception("Can't add more items in this Column");
		
		
		
	}
	
	public void requestInventory(Boolean inFloor, Location location, int amount){
		ArrayList<Container> inventory;
		if(inFloor) inventory = floorInventory;
		else inventory = stockInventory;
		Container c = inventory.get(location.getContainer());
		Shelf s;
		if(location.isLeft())
			s  = c.getLeft();
		else s = c.getRight();
		InventoryItem im = s.getColumn().get(location.getColumn()).items.get(0);
		im.setAvailable(im.getAvailable() - amount);
	}
	
	public void removeExpiredInventory() {
		
	}
	
	public void changeProductLocation(Item item1, Item item2) {
		
	}
	
	public void removeInventory(Boolean inFloor, Item product, int amount){
		
	}
	/**
	 * This function is used to initiate the store layout data structure for stock inventory and store inventory
	 * @param layout layout Data structure that represents the store
	 */
	public void initStorage(ArrayList<Container> layout){
		this.stockInventory = layout;
		this.storeInventory = layout;
	}
	
	/**
	 * This function is the main function used to find items were every they are whether it be in store or in stock
	 * @param location location of the object
	 * @return Inventory item stored at the specified location
	 */
	public InventoryItem findItem(Location location) {
		if (location.isInStore()) {
			Container c = storeInventory.get(location.getContainer());
			if(location.isLeft()) {
				return c.getLeft().getColumn().get(location.getColumn()).getItems().get(location.getRow());
			}else {
				return c.getRight().getColumn().get(location.getColumn()).getItems().get(location.getRow());
			}
		}else {
			Container c = stockInventory.get(location.getContainer());
			if(location.isLeft()) {
				return c.getLeft().getColumn().get(location.getColumn()).getItems().get(location.getRow());
			}else {
				return c.getRight().getColumn().get(location.getColumn()).getItems().get(location.getRow());
			}
		}
	}
	
	/**
	 * This function is used to get the column of a specific location (mainly to add inventory to the specific row of said column).
	 * this function is implemented in the loadDataFromFiles in smartStore
	 * @param location specified location for the column
	 * @return the desired column object
	 */
	public Column getColumn(Location location) {
		if (location.isInStore()) {
			Container c = storeInventory.get(location.getContainer());
			if(location.isLeft()) {
				return c.getLeft().getColumn().get(location.getColumn());
			}else {
				return c.getRight().getColumn().get(location.getColumn());
			}
		}else {
			Container c = stockInventory.get(location.getContainer());
			if(location.isLeft()) {
				return c.getLeft().getColumn().get(location.getColumn());
			}else {
				return c.getRight().getColumn().get(location.getColumn());
			}
		}
	}
	/**
	 * This method is called by the sensor when the sensor receives a valid user interaction such as grabbing or returning an item
	 * @param loc the location of the sensor
	 * @param amount the amount of items return if positive or the amount of items taken if negative.
	 */
	public void recieveSensorNotification(Location loc, int amount) {
		
	}
}
