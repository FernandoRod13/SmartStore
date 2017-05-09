package Inventory;

import Exceptions.InventoryException;

public class Sensor{
	private InventoryItem product;
	private Location location;
	private String id;
	private InventoryManager manager;
	public Sensor(InventoryItem product, Location location, String id) {
		super();
		this.product = product;
		this.location = location;
		this.id = id;
		this.manager = InventoryManager.getInstance();
	}
	public InventoryItem getProduct() {
		return product;
	}
	public void setProduct(InventoryItem product) {
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
	
	/**
	 * This function should be called when a user grabs or returns an item at this location. This will notify the manager
	 * so that the stock inventory is updated 
	 * @param amount positive amount indicates user is returning an object to this location, negative indicates 
	 * a user grab an object at this location. This number must not be zero or greater the available amount if negative
	 * or if positive must not be greater than max capacity
	 * @throws InventoryException must be a valid change with respect to the current inventory otherwise an exception is thrown
	 */
	public void notifyInventoryManager(int amount) throws InventoryException {
		if (amount == 0|| 
				(amount > 0 && amount+(product.getMaxCap()-product.getAvailable()) > product.getMaxCap()) || 
				(amount < 0 && (product.getAvailable()-amount) < 0)){
			throw new InventoryException(amount);
		}else {
			manager.recieveSensorNotification(this.location, amount);
		}
	}
}
