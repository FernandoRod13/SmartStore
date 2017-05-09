import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Inventory.Shelf;
import Inventory.Item;

public class SmartStore {
	
	public static ArrayList<Shelf> gondolas;
	public static ArrayList<Item> inventory;
	
	public static void main(String[] args) {
		gondolas = new ArrayList<Shelf>();
		inventory = new ArrayList<Item>();
		try {
			loadFileData();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(gondolas.size());
		System.out.println(inventory.size());
	}
	
	public static void loadFileData() throws IOException {
		File gondolaFile = new File("src/StoreLayout.txt");
		try(BufferedReader br = new BufferedReader(new FileReader(gondolaFile))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        if(!line.equals("aisle|GID|aisleGID|lvls")) {
		        	gondolas.add(new Shelf(line.split("\\|", -1)));
		        }
		    }
		    
		}
		File inventoryFile = new File("src/Inventory.txt");
		try(BufferedReader br = new BufferedReader(new FileReader(inventoryFile))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        if(!line.equals("Name|ID|Price|Category|StoreLocation > StoreLocation = GID-GSide-GLvl-isleGID-isleID-inFloor")) {
		        	inventory.add(new Item(line.split("\\|", -1)));
		        }
		    }
		    
		}
	}
}
