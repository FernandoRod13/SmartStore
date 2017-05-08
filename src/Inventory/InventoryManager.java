package Inventory;

public class InventoryManager {
	
	private final InventoryManager instance = new InventoryManager();
	
	public InventoryManager getInstance() {
		return instance;
	}
	
	public void addInventory(Boolean inFloor, Item product, int amount) {
		
	}
	
	public void requestInventory(Boolean inFloor, Item product, int amount){
		
	}
	
	public void removeExpiredInventory() {
		
	}
	
	public void changeProductLocation(Item item1, Item item2) {
		
	}
	
	public void removeInventory(Boolean inFloor, Item product, int amount){
		
	}
	
}
