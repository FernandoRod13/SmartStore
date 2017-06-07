package Inventory;

public class ListItem {
	private Item item;
	private int amount;
	
	public ListItem(Item item, int amount) {
		super();
		this.item = item;
		this.amount = amount;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item product) {
		this.item = product;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
