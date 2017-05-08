package Inventory;

public class Sensor {
	private Item product;
	private Location location;
	private String id;
	public Sensor(Item product, Location location, String id) {
		super();
		this.product = product;
		this.location = location;
		this.id = id;
	}
	public Item getProduct() {
		return product;
	}
	public void setProduct(Item product) {
		this.product = product;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
