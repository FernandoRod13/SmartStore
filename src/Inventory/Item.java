package Inventory;

public class Item {
	private String id;
	private String name;
	private double price;
	private String category;
	
	public Item(String id, String name, String category, double price){
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
	}
	
	public Item(String[] splitfileLine) {
		this(splitfileLine[0], splitfileLine[1], splitfileLine[3], Double.parseDouble(splitfileLine[2]));
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}	
	
}
