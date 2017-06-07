import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



import Agents.User;
import Agents.UserManager;
import Inventory.InventoryManager;
import Inventory.Item;
import Inventory.Location;

public class SmartStore {

	

	public static void main(String[] args) {
		
		try {
			InventoryManager invMang = InventoryManager.getInstance();
			UserManager userManager = UserManager.getInstance();
			
			userManager.addUserToDB("Cristian", "Melendez");
			
			userManager.addUserToDB("Martito", "Melendez");			// Por que ustedes son mis hijos jaja
			userManager.addUserToDB("Fernando", "Melendez");
			
			Location newLocation = new Location(2,false,1,3);
			
//			invMang.addInventory( newLocation, "Escoba", 50, 185, 30,
//					100, 10.56, "limpieza",5.78);
			
//			System.out.println(invMang.getSingleItem(newLocation));
//			invMang.userBuysItem(newLocation, 20);
//			invMang.minimunCapacityReach(newLocation);
//			invMang.needToBringItemsToFloor(newLocation);
			ArrayList<Item> items = invMang.getAllItems();
			
			
			
			
			
			
			
			
			
		} catch (Exception  e) {
			System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );
		}

		
	}
	

}
