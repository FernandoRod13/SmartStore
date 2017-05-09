package Inventory;
import java.util.ArrayList;
public class Shelf {
	private ArrayList<Column> column = new ArrayList<>();
	
	
	public Shelf() {
		column.add(new Column());
		column.add(new Column());
		column.add(new Column());
	}
	
	
	public Shelf(String[] splitfileLine){

	}



	public ArrayList<Column> getColumn() {
		return column;
	}



	public void setColumn(ArrayList<Column> column) {
		this.column = column;
	}
	
	
	
	
}