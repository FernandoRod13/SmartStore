package Inventory;
import java.util.ArrayList;

public class InventoryManager {
	private ArrayList<Container> storeInventory;
	private ArrayList<Container> stockInventory;
	
	private static InventoryManager instance = null;
	
	public static InventoryManager getInstance() {
		if(instance == null) instance = new InventoryManager();
		return instance;
	}
	
	public void addInventory(Boolean inFloor, Location location, int amount) {
		
	}
	
	public void requestInventory(Boolean inFloor, Location location, int amount){
		
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
}
