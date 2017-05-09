package Inventory;
public class Location {
	
	private int column;
	private boolean isLeft;
	private int row;
	private int container;
	private boolean infloor;
	
	public Location(int column, boolean isLeft, int row, int container, boolean infloor) {
		super();
		this.column = column;
		this.isLeft = isLeft;
		this.row = row;
		this.container = container;
		this.infloor = infloor;
	}

	public Location(String[] splitFileLine){
		//this(splitFileLine[0],splitFileLine[1],Integer.parseInt(splitFileLine[2]),splitFileLine[3],Integer.parseInt(splitFileLine[4]), Boolean.parseBoolean(splitFileLine[5]));
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public boolean isLeft() {
		return isLeft;
	}

	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getContainer() {
		return container;
	}

	public void setContainer(int container) {
		this.container = container;
	}

	public boolean isInfloor() {
		return infloor;
	}

	public void setInfloor(boolean infloor) {
		this.infloor = infloor;
	}

}
