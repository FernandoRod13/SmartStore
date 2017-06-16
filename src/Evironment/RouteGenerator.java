package Evironment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import Inventory.InventoryManager;
import Inventory.ListItem;
import Inventory.Location;

public class RouteGenerator {
	private static RouteGenerator instance;
	private StoreMap graph;
	
	private RouteGenerator() {
		try {
			this.graph = new StoreMap();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static RouteGenerator getInstance(){
		if(instance != null) {
			return instance;
		}
		return new RouteGenerator();
	}
	
	private ArrayList<Location> extractLocationList(ArrayList<ListItem> groceryList) {
		if (groceryList.size() == 0)
			return null;
		ArrayList<Location> locations = new ArrayList<>();
		for(ListItem item: groceryList) {
			locations.add(item.getItem().getLocation());
		}
		return locations;
	}
	
	public ArrayList<Step> getStoreDirections(ArrayList<ListItem> groceryList) {
		ArrayList<Step> steps = new ArrayList<>();
		ArrayList<Location> locations = extractLocationList(groceryList);
		Node current = graph.getEntry();
		for(Location loc: locations) {
			Step step = new Step(current, loc.getGraphNodeIndex(), traceRoute(current,loc.getGraphNodeIndex()), 
					InventoryManager.getInstance().getSingleItem(loc));
			steps.add(step);
			ArrayList<Node> nodeList = step.getTrace();
			current = nodeList.get(nodeList.size()-1);			
		}
		steps.add(new Step(current,this.graph.getExit().getIndex(), traceRoute(current,this.graph.getExit().getIndex()),null));
		return steps;
	}
	
	private ArrayList<Node> traceRoute(Node current, int nodeIndex) {
		ArrayList<Node> trace = new ArrayList<>();
		HashMap<Integer, Integer> visited = new HashMap<>();
		Queue<Node> queue = new LinkedList<>();
		queue.add(current);
		current.setParent(-2);
		while(!queue.isEmpty()) {
			Node popped = queue.poll();
			for (Node node : popped.getNeighbors()) {
				if(!queue.contains(node) || !visited.containsKey(node.getIndex())){
					node.setParent(popped.getIndex());
					queue.add(node);
				}
			}
			if(popped.getParent() == -2) {
				visited.put(popped.getIndex(), popped.getIndex());
				popped.setParent(-1);
			}else{
				visited.put(popped.getIndex(), popped.getParent());
				popped.setParent(-1);
			}
			
			if(popped.getIndex() == nodeIndex) {
				break;
			}
		}
		Stack<Integer> reverseTrace = new Stack<>();
		Integer trackingIndex = nodeIndex;
		while(visited.get(trackingIndex) != visited.get(current.getIndex())) {
			reverseTrace.push(trackingIndex);
			trackingIndex = visited.get(trackingIndex);
		}
		trace.add(current);
		reverseTrace.pop();
		while(!reverseTrace.isEmpty()) {
			Integer index = reverseTrace.pop();
			trace.add(graph.getGraph().get(index));
		}
		return trace;
	}
	
	
	

}
