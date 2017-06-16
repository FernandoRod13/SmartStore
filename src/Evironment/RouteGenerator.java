package Evironment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import Inventory.ListItem;
import Inventory.Location;
import Inventory.LocationComparator;

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
		locations.sort(new LocationComparator());
		return locations;
	}
	
	public ArrayList<Step> getStoreDirections(ArrayList<ListItem> groceryList) {
		ArrayList<Step> steps = new ArrayList<>();
		ArrayList<Location> locations = extractLocationList(groceryList);
		Node current = graph.getEntry();
		for(int i = 0; i < groceryList.size();i++) {
			Location loc = locations.get(i);
			ArrayList<Node> trace = traceRoute(current,loc.getGraphNodeIndex());
			Step step = new Step(current, loc.getGraphNodeIndex(), trace, 
					groceryList.get(i).getItem().getName());
			//System.out.println(i+1+") "+step.toString());
			steps.add(step);
			ArrayList<Node> nodeList = step.getTrace();
			current = nodeList.get(nodeList.size()-1);			
		}
		steps.add(new Step(current,this.graph.getExit().getIndex(), traceRoute(current,this.graph.getExit().getIndex()),"End"));
		return steps;
	}
	
	private ArrayList<Node> traceRoute(Node current, int nodeIndex) {
		ArrayList<Node> trace = new ArrayList<>();
		HashMap<Integer, Integer> visited = new HashMap<>();
		Queue<Node> queue = new LinkedList<>();
		int[] explored = new int[45];
		
		queue.add(current);
		current.setParent(-2);
		while(!queue.isEmpty()) {
			Node popped = queue.poll();
			explored[popped.getIndex()] = 1;
			for (Node node : popped.getNeighbors()) {
				if(explored[node.getIndex()] == 0){
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
		//System.out.println(visited);
		Stack<Integer> reverseTrace = new Stack<>();
		Integer trackingIndex = nodeIndex;
		reverseTrace.push(trackingIndex);
		//System.out.println(reverseTrace);
		while(reverseTrace.peek() != current.getIndex()) {
			trackingIndex = visited.get(trackingIndex);
			//System.out.println(trackingIndex);
			reverseTrace.push(trackingIndex);
		}
		//System.out.println(reverseTrace);
		trace.add(current);
		reverseTrace.pop();
		while(!reverseTrace.isEmpty()) {
			Integer index = reverseTrace.pop();
			trace.add(graph.getGraph().get(index));
		}
		
		return trace;
	}
	
	
	

}
