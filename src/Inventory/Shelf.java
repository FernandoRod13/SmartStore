package Inventory;
import java.util.ArrayList;
public class Shelf {

	private ArrayList<Column> column;

	public Shelf() {

		super();
		column = new ArrayList<>();
	}

	public ArrayList<Column> getColumn() {
		return column;
	}

	public void setColumn(ArrayList<Column> column) {
		this.column = column;
	}




}
