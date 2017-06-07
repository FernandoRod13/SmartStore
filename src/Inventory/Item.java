package Inventory;

public class Item {
	private String id;
	private String name;
	private double retailPrice;
	private String category;
	private int mincap;
	private int maxcap;
	private int inStoreAvailable;
	private	int stockAvailable;
	private double cost;
	private Location location;
	
	
	
	public Item(String id, String name, double retailPrice, String category, int mincap, int maxcap,
			int inStoreAvailable, int stockAvailable, double cost, Location location) {
		super();
		this.id = id;
		this.name = name;
		this.retailPrice = retailPrice;
		this.category = category;
		this.mincap = mincap;
		this.maxcap = maxcap;
		this.inStoreAvailable = inStoreAvailable;
		this.stockAvailable = stockAvailable;
		this.cost = cost;
		this.location = location;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public double getRetailPrice() {
		return retailPrice;
	}



	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public int getMincap() {
		return mincap;
	}



	public void setMincap(int mincap) {
		this.mincap = mincap;
	}



	public int getMaxcap() {
		return maxcap;
	}



	public void setMaxcap(int maxcap) {
		this.maxcap = maxcap;
	}



	public int getInStsoreAvailable() {
		return inStoreAvailable;
	}



	public void setInStsoreAvailable(int inStsoreAvailable) {
		this.inStoreAvailable = inStsoreAvailable;
	}



	public int getStockAvailable() {
		return stockAvailable;
	}



	public void setStockAvailable(int stockAvailable) {
		this.stockAvailable = stockAvailable;
	}



	public double getCost() {
		return cost;
	}



	public void setCost(double cost) {
		this.cost = cost;
	}



	public Location getLocation() {
		return location;
	}



	public void setLocation(Location location) {
		this.location = location;
	}



	@Override
	public String toString() {
		return "Id= " + id + "\nName= " + name + "\nRetail Price= " + retailPrice + "\nCategory=" + category
				+ "\nmincap= " + mincap + "\nMaxcap= " + maxcap + "\nIn Store Available= " + inStoreAvailable
				+ "\nStock Available= " + stockAvailable + "\nCost=" + cost + "\nLocation=" + location + "]";
	}

	
	
	
	
	
}
