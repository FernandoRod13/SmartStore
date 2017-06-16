import java.util.ArrayList;

import Agents.TransactionManager;
import Agents.User;
import Agents.UserManager;
import Evironment.RouteGenerator;
import Evironment.Step;
import Evironment.StoreMap;
import Inventory.InventoryManager;
import Inventory.Item;
import Inventory.ListItem;
import Inventory.Location;

public class SmartStore {

	

	public static void main(String[] args) {
		
		try {
			InventoryManager invMang = InventoryManager.getInstance();
			UserManager userManager = UserManager.getInstance();
			TransactionManager transManager = TransactionManager.getInstance();
			
			invMang.DBInit();
			userManager.DBInit();
			transManager.DBInit();
			
			User user = userManager.getAllUsers().get(0);
			System.out.println(user.getName());

			
			Location newLocation = new Location(2,false,1,3);

			Item item1 = new Item("2345434f","XBOX",60.00,"electronics", 30, 100, 30,
					15, 300.00, newLocation);
			
			Item item2 = new Item("234656f","Nintendo",56.00,"electronics", 30, 100, 30,
					15, 356.00, newLocation);
//			InventoryManager.getInstance().addInventory(newLocation, "apples", 10, 30, 30, 30, .60, "fruit", .20);
//			newLocation = new Location(2,false,1,2);
//			InventoryManager.getInstance().addInventory(newLocation, "oranges", 10, 30, 30, 30, .30, "fruit", .15);
//			newLocation = new Location(2,false,1,1);
//			InventoryManager.getInstance().addInventory(newLocation, "bananas", 10, 30, 30, 30, .50, "fruit", .25);
//			newLocation = new Location(1,false,1,1);
//			InventoryManager.getInstance().addInventory(newLocation, "lemons", 10, 30, 30, 30, .25, "fruit", .12);

			ListItem listItem1 = new ListItem(item1,3);
			ListItem listItem2 = new ListItem(item2,4);
			
			ArrayList<ListItem> items = new ArrayList<>();
			
			items.add(listItem1);
			items.add(listItem2);
			ArrayList<Item> testList = InventoryManager.getInstance().getAllItems();
			ArrayList<ListItem> list = new ArrayList<>();
			for(Item i: testList) {
				list.add(new ListItem(i, 2));
			}
			testSearch(list);
//			transManager.addTransaction(items, new Date(), "801124795", (300.00 * 3) + (356 * 4));

			
//			System.out.println(invMang.getSingleItem(newLocation));
//			invMang.userBuysItem(newLocation, 20);
//			invMang.minimunCapacityReach(newLocation);
//			invMang.needToBringItemsToFloor(newLocation);
//			ArrayList<Item> items = invMang.getAllItems();
	
		} catch (Exception  e) {
			System.out.println(e);
			System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );
		}

		
	}
	
	public static void testSearch(ArrayList<ListItem> groceryList) {
		ArrayList<Step> steps = RouteGenerator.getInstance().getStoreDirections(groceryList);
		for(Step s: steps) {
			System.out.println(s.toString());
		}
	}
	

}
