package Inventory;
public class Location {
	
	private int column;
	private boolean isLeft;
	private int row;
	private int container;
	private int graphNodeIndex;
	
	
	public Location(int container, boolean isLeft, int column, int row) {
		super();
		this.column = column;
		this.isLeft = isLeft;
		this.row = row;
		this.container = container;
		this.graphNodeIndex = -1;
		
	}
	
	public Location(int container, boolean isLeft, int column, int row, int nodeIndex) {
		super();
		this.column = column;
		this.isLeft = isLeft;
		this.row = row;
		this.container = container;
		this.graphNodeIndex = nodeIndex;
		
	}
	/**
	 * This constructor is only used when initializing the product to populate the store data structure with inventory
	 * @param splitFileLine a file line spliced into an array of components
	 */
	public Location(String[] splitFileLine, int level){
		this(Integer.parseInt(splitFileLine[0]),Boolean.parseBoolean(splitFileLine[1]),Integer.parseInt(splitFileLine[2]),
				level, Integer.parseInt(splitFileLine[4]));
	}

	public int getGraphNodeIndex() {
		return graphNodeIndex;
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

	
	
	@Override
	public String toString() {
		return "Location [container = " + container + ", isLeft= " + isLeft + ", column= " + column +
				", row=" + row +", node = "+graphNodeIndex+ "]";
	}
	
}
