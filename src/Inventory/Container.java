package Inventory;

public class Container {
	private Shelf left;
	private Shelf right;
	public Container(Shelf left, Shelf right) {
		super();
		this.left = left;
		this.right = right;
	}
	/**
	 * This constructor is only used when initializing the product to populate the store data structure with inventory
	 */
	public Container() {
		super();
		this.left = new Shelf();
		this.right = new Shelf();
	}

	public Shelf getLeft() {
		return left;
	}

	public void setLeft(Shelf left) {
		this.left = left;
	}

	public Shelf getRight() {
		return right;
	}

	public void setRight(Shelf right) {
		this.right = right;
	}
	
}
