package Inventory;

public class ListItem {
	private Item product;
	private int amount;
	public ListItem(Item product, int amount) {
		super();
		this.product = product;
		this.amount = amount;
	}
	public Item getProduct() {
		return product;
	}
	public void setProduct(Item product) {
		this.product = product;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
