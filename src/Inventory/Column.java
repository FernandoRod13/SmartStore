package Inventory;
import java.util.ArrayList;

public class Column {
	ArrayList<InventoryItem> items = new ArrayList<>();

	public Column() {
		super();
		
	}

	public ArrayList<InventoryItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<InventoryItem> items) {
		this.items = items;
	}
	
	
	
	
}
