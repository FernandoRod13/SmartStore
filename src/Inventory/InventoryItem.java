package Inventory;

public class InventoryItem {
	private Item item;
	private Location location;
	private int minCap;
	private int maxCap;
	private int available;
	
	public InventoryItem(Item item, Location location, int minCap, int maxCap, int available) {
		super();
		this.item = item;
		this.location = location;
		this.minCap = minCap;
		this.maxCap = maxCap;
		this.available = available;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getMinCap() {
		return minCap;
	}

	public void setMinCap(int minCap) {
		this.minCap = minCap;
	}

	public int getMaxCap() {
		return maxCap;
	}

	public void setMaxCap(int maxCap) {
		this.maxCap = maxCap;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}
	
	
	
	
}
