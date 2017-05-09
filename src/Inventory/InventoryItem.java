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
	/**
	 * This constructor is only used when initializing the product to populate the store data structure with inventory
	 * @param splitFileLine a file line spliced into an array of components
	 */
	public InventoryItem(String[] splitFileLine) {
		this(new Item(splitFileLine[0].split("\\-", -1)), new Location(splitFileLine[1].split("\\-", -1)), 
				Integer.parseInt(splitFileLine[2]), Integer.parseInt(splitFileLine[3]), Integer.parseInt(splitFileLine[4]));
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
