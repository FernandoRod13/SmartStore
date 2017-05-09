import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Agents.User;
import Inventory.InventoryManager;
import Inventory.Item;
import Inventory.Location;
import Inventory.Shelf;

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
		
		//Mario Testing
		InventoryManager im = InventoryManager.getInstance();
		Location l;
		//added French Fries 10
		Item fries = inventory.get(0);
		im.addNewInventory(fries, true,l = new Location(0, false, 0, 0, true),10, 30, 10);
		User user = new User("mario", "marito");
		int total;
				
		try {
			System.out.println("User adds 6 fries in list");
			//User wants 6 ff
			user.addItemToList(fries, 6);
			total = user.getGroceryList().get(fries.getId()).getAmount();
			System.out.println("Total in list " + total);
			total = im.findItem(l).getAvailable();
			System.out.println("Total in inventory " + total);
			System.out.println();
			
			System.out.println("User puts 5 fries in cart");
			user.takeItem(l, 5);
			total = user.getVirtualCart().get(fries.getId()).getAmount();
			System.out.println("Total in cart " + total);
			total = user.getGroceryList().get(fries.getId()).getAmount();
			System.out.println("Total in list " + total);
			total = im.findItem(l).getAvailable();
			System.out.println("Total in inventory " + total);
			System.out.println();
			
			user.returnItem(fries, l, 3);
			System.out.println("Returned 3 from cart to inventory");
			total = im.findItem(l).getAvailable();
			System.out.println("Total in inventory "+total);
			total = user.getVirtualCart().get(fries.getId()).getAmount();
			System.out.println("Total in cart " + total);
			total = user.getGroceryList().get(fries.getId()).getAmount();
			System.out.println("Total in list " + total);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
