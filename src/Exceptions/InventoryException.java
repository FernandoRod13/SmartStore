package Exceptions;

public class InventoryException extends Exception {
	public InventoryException(int amount) {
		super("You have entered an invalid inventory chnage request. Desired amount change: " + amount);
	}
    
}
