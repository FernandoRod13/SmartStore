package Inventory;
import java.util.ArrayList;

public class Column {
	ArrayList<InventoryItem> items;
	ArrayList<Sensor> sensors;
	/**
	 * This constructor is only used when initializing the product to populate the store data structure with inventory
	 * @param lvls number of rows in a column of a shelf
	 */
	public Column(int lvls) {
		super();
		items = new ArrayList<>();
		sensors = new ArrayList<>();
		for(int i = 0; i < lvls; i++) {
			items.add(new InventoryItem(null, null, 0, 0, 0));
			sensors.add(new Sensor(null, null, null));
		}
	}

	public ArrayList<InventoryItem> getItems() {
		return items;
	}
	/**
	 * This function is used when reading from file to populate the store with the products and sensors 
	 * to the corresponding location
	 * @param item Inventory item to be added (read from file)
	 */
	public void setItem(InventoryItem item) {
		items.set(item.getLocation().getRow(), item);
		sensors.set(item.getLocation().getRow(), new Sensor(item.getItem(),item.getLocation(),item.getItem().getId()));
	}
	
	
	
	
}
