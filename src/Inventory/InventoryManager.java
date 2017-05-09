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
	
	public void initStorage(ArrayList<Container> layout){
		this.stockInventory = layout;
		this.storeInventory = layout;
	}
	
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
