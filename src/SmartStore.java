import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Inventory.Column;
import Inventory.Container;
import Inventory.InventoryItem;
import Inventory.InventoryManager;

public class SmartStore {
	
	public static ArrayList<Container> layout;
	
	public static void main(String[] args) {
		layout = new ArrayList<Container>();
		try {
			loadFileData();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(layout.size());
	}
	/**
	 * This function populates the program with the initial data such as store layout data structure and inventory 
	 * @throws IOException file not found exception
	 */
	public static void loadFileData() throws IOException {
		File layoutFile = new File("src/StoreLayout.txt");
		try(BufferedReader br = new BufferedReader(new FileReader(layoutFile))) {
			layout = new ArrayList<>();
		    for(String line; (line = br.readLine()) != null; ) {
		        if(!line.equals("Container|isLeft|Columns|Levels")) {
		        	layout = evaluateLayoutLine(line.split("\\|", -1), layout);
		        }
		    }
		    
		}
		InventoryManager.getInstance().initStorage(layout);
		
		File inventoryFile = new File("src/Inventory.txt");
		try(BufferedReader br = new BufferedReader(new FileReader(inventoryFile))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        if(!line.equals("Item|Location|MinCap|MaxCap|Available > Item = ID-Name-Price-Category > Location = Container-isLeft-Column-Row-inStore")) {
		        	InventoryItem item = new InventoryItem(line.split("\\|", -1));
		        	InventoryManager.getInstance().getColumn(item.getLocation()).setItem(item);
		        }
		    }
		    
		}
	}
	/**
	 * This function takes a line from a file that has been separated into components (specifically form the store layout file)
	 * and initiates the data structure that reflects the layout of the store and where products are located.
	 * @param splitFileLine file line separated into components
	 * @param layout the current state of the layout data structure
	 * @return the updated layout data structure
	 */
	private static ArrayList<Container> evaluateLayoutLine(String[] splitFileLine, ArrayList<Container> layout) {
		int containerIndex = Integer.parseInt(splitFileLine[0]);
		if (layout.size() <= containerIndex) {
			layout.add(new Container());
		}
		Boolean isLeft = Boolean.parseBoolean(splitFileLine[1]);
		int columnIndex = Integer.parseInt(splitFileLine[2]);
		int maxLvls = Integer.parseInt(splitFileLine[3]);
		if (isLeft) {
			if (layout.get(containerIndex).getLeft().getColumn().size() <= columnIndex) {
				layout.get(containerIndex).getLeft().getColumn().add(new Column(maxLvls));
			}
			
		}else {
			if (layout.get(containerIndex).getRight().getColumn().size() <= columnIndex) {
				layout.get(containerIndex).getRight().getColumn().add(new Column(maxLvls));
			}
		}
		return layout;
	}
	
}
