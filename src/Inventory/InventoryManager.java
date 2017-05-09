package Inventory;
import java.util.ArrayList;

public class InventoryManager {
	private ArrayList<Container> floorInventory;
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
	
	public void addInventory(Boolean inFloor, Location location, int amount){
		ArrayList<Container> inventory;
		if(inFloor) inventory = floorInventory;
		else inventory = stockInventory;
		Container c = inventory.get(location.getContainer());
		Shelf s;
		if(location.isLeft())
			s  = c.getLeft();
		else s = c.getRight();
		InventoryItem item = s.getColumn().get(location.getColumn()).items.get(0);
		item.setAvailable(item.getAvailable() + amount);
		
	}
	public void addNewInventory(Item i, Boolean inFloor, Location location,int min, int max, int amount) {
		ArrayList<Container> inventory;
		if(inFloor) inventory = floorInventory;
		else inventory = stockInventory;
		Container c = inventory.get(location.getContainer());
		Shelf s;
		if(location.isLeft())
			s  = c.getLeft();
		else s = c.getRight();
		s.getColumn().get(location.getColumn()).items.add(new InventoryItem(i,location, min, max, amount));
		
		
		
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
	
	public InventoryItem findItem(Location location) {
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
