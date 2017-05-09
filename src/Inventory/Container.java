package Inventory;
import java.util.ArrayList;

public class Container {
private ArrayList<Shelf> left = new ArrayList<>();
private ArrayList<Shelf> rigth = new ArrayList<>();

public Container() {
	super();

}

public ArrayList<Shelf> getLeft() {
	return left;
}

public void setLeft(ArrayList<Shelf> left) {
	this.left = left;
}

public ArrayList<Shelf> getRigth() {
	return rigth;
}

public void setRigth(ArrayList<Shelf> rigth) {
	this.rigth = rigth;
}
	

	
	
	
}
