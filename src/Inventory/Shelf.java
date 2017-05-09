package Inventory;
import java.util.ArrayList;
public class Shelf {
	private ArrayList<Column> column = new ArrayList<>();
	
	
	public Shelf() {
		super();
	}
	
	
	
	public Shelf(String[] splitfileLine){
		this(Integer.parseInt(splitfileLine[3]), splitfileLine[2], Integer.parseInt(splitfileLine[0]), splitfileLine[1]);
	}



	public ArrayList<Column> getColumn() {
		return column;
	}



	public void setColumn(ArrayList<Column> column) {
		this.column = column;
	}
	
	
	
	
}