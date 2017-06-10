import Agents.Transaction;
import Agents.User;
import Agents.UserManager;
import Inventory.InventoryManager;
import Inventory.Location;

public class SmartStore {

	

	public static void main(String[] args) {
		
		try {
			InventoryManager invMang = InventoryManager.getInstance();
			UserManager userManager = UserManager.getInstance();
			
			invMang.DBInit();
			userManager.DBInit();
			User user = userManager.getAllUsers().get(0);
			System.out.println(user.getName());
//			userManager.addUserToDB("Cristian", "Melendez");
//			
//			userManager.addUserToDB("Martito", "Melendez");			// Por que ustedes son mis hijos jaja
//			userManager.addUserToDB("Fernando", "Melendez");
			
			Location newLocation = new Location(2,false,1,3);
			//Location, name, mincap, maxcap, inStoreAvailable, stockAvailable, retailPrice,  category, cost
			invMang.addInventory( newLocation, "XBOX", 30, 100, 30,
					15, 300.00, "electronics",60.00);
			user.userPickItem(invMang.getSingleItem(newLocation), 1);
			System.out.println(user.getVirtualCart().size());
			Transaction t = new Transaction("t1",user.getId(), "10-6-17", user.getVirtualCart());
			System.out.println(t.getTotalPrice());
			
//			System.out.println(invMang.getSingleItem(newLocation));
//			invMang.userBuysItem(newLocation, 20);
//			invMang.minimunCapacityReach(newLocation);
//			invMang.needToBringItemsToFloor(newLocation);
//			ArrayList<Item> items = invMang.getAllItems();
			
			
			
			
			
			
			
			
			
		} catch (Exception  e) {
			System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );
		}

		
	}
	

}
