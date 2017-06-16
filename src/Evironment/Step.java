package Evironment;

import java.util.ArrayList;

import Inventory.Item;

public class Step {
	private Node currentNode;
	private int objective;
	private ArrayList<Node> trace;
	private Item item;
	
	public Step(Node currentNode, int objective, ArrayList<Node> trace, Item item) {
		super();
		this.currentNode = currentNode;
		this.objective = objective;
		this.trace = trace;
		this.item = item;
	}
	
	public String toString() {
		String res =  "\nCurrent Node index: "+ this.currentNode.getIndex()+"\n Objective Node index: "+this.objective
				+ "\nGrocery Item at Objective: " + this.item.getName() + "\nPath: ";
		for(Node n: this.trace) {
			res+= n.getIndex() + " -> "; 
		}
		return res;
	}
	
	public ArrayList<Node> getTrace() {
		return this.trace;
	}
	
	
	
	
}
