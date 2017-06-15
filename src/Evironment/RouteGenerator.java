package Evironment;

import java.util.ArrayList;

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
	
	public ArrayList<Location> extractLocationList(ArrayList<ListItem> groceryList) {
		if (groceryList.size() == 0)
			return null;
		ArrayList<Location> locations = new ArrayList<>();
		for(ListItem item: groceryList) {
			locations.add(item.getItem().getLocation());
		}
		locations.sort(new LocationComparator());
		return locations;
	}
	
	public ArrayList<Node> getStoreDirections(ArrayList<ListItem> groceryList) {
		ArrayList<Node> path = new ArrayList<>();
		ArrayList<Location> locations = extractLocationList(groceryList);
		Node current = graph.getEntry();
		for(Location loc: locations) {
			path.addAll(traceRoute(current,loc.getGraphNodeIndex()));
			current = path.get(path.size()-1);
		}
		path.addAll(traceRoute(current,graph.getExit().getIndex()));
		return path;
	}
	
	public ArrayList<Node> traceRoute(Node current, int nodeIndex) {
		ArrayList<Node> trace = new ArrayList<>();
		return trace;
	}
	
	

}
