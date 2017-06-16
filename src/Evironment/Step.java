package Evironment;

import java.util.ArrayList;

import Inventory.Item;

public class Step {
	private Node currentNode;
	private int objective;
	private ArrayList<Node> trace;
	private String itemName;
	
	public Step(Node currentNode, int objective, ArrayList<Node> trace, String itemName) {
		super();
		this.currentNode = currentNode;
		this.objective = objective;
		this.trace = trace;
		this.itemName = itemName;
	}
	
	public String toString() {
		String res =  "\nCurrent Node index: "+ this.currentNode.getIndex()+"\nObjective Node index: "+this.objective
				+ "\nGrocery Item at Objective: " + this.itemName + "\nPath: ";
		for(Node n: this.trace) {
			res+= n.getIndex() + " -> "; 
		}
		res = res.substring(0, res.length()-4);
		return res;
	}
	
	public ArrayList<Node> getTrace() {
		return this.trace;
	}
	
	
	
	
}
