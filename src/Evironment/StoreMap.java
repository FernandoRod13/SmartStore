package Evironment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Inventory.Location;

public class StoreMap {
	private Node entry;
	private Node exit;
	
	public StoreMap() throws FileNotFoundException, IOException{
		ArrayList<Node> graph = new ArrayList<>();
		for(int i = 0; i<45; i++) {
			 graph.add(new Node(i));
		}
		entry = graph.get(0);
		entry.setEntry(true);
		exit = graph.get(36); 
		exit.setExit(true);
		
		File graphFile = new File("src/Graph.txt");
		try(BufferedReader br = new BufferedReader(new FileReader(graphFile))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        if(!line.equals("Node|neighbors")) {
		        	String[] fragments= line.split("\\|", -1);
		        	int nodeIndex = Integer.parseInt(fragments[0]);
		        	String[] strNeighborIndexes = fragments[1].split(",", -1);
		        	int[] neighborIndexes = new int[strNeighborIndexes.length];
		        	for(int i = 0; i<neighborIndexes.length;i++){
		        		neighborIndexes[i] = Integer.parseInt(strNeighborIndexes[i]);
		        	}
		        	Node n = graph.get(nodeIndex);
		        	for(int index: neighborIndexes) {
		        		n.addNeighbor(graph.get(index));
		        	}
		        	
		        }
		    }
		    
		}
		File storelayout = new File("src/StoreLayout.txt");
		try(BufferedReader br = new BufferedReader(new FileReader(storelayout))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        if(!line.equals("Container|isLeft|Columns|Levels|GraphNode")) {
		        	String[] layoutFragments = line.split("\\|", -1);
		        	for(int j = 0; j < 3; j++) {
		        		Location l = new Location(layoutFragments,j);
		        		Node node = graph.get(l.getGraphNodeIndex());
		        		node.addLocation(l);
		        	}
		        }
		    }
		}
		System.out.println("finished graph");
	}

	public Node getEntry() {
		return entry;
	}

	public Node getExit() {
		return exit;
	}
	
	

}
