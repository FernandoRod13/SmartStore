package Inventory;
import java.util.ArrayList;

public class InventoryManager {
	private ArrayList<Container> floorInventory;
	private ArrayList<Container> stockInventory;
	
	private final InventoryManager instance = new InventoryManager();
	
	public InventoryManager getInstance() {
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
	
	private InventoryItem findItem(Location location) {
		if (location.isInfloor()) {
			Container c = floorInventory.get(location.getContainer());
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
	
}
