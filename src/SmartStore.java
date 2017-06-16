import java.util.ArrayList;

import Agents.TransactionManager;
import Agents.User;
import Agents.UserManager;
import Evironment.RouteGenerator;
import Evironment.Step;
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

			ListItem listItem1 = new ListItem(item1,3);
			ListItem listItem2 = new ListItem(item2,4);
			
			ArrayList<ListItem> items = new ArrayList<>();
			
			items.add(listItem1);
			items.add(listItem2);
			User user1 = userManager.getAllUsers().get(0);
			System.out.println(user.getName());
			
			ArrayList<Item> items1 = invMang.getAllItems();
			
			ArrayList<ListItem> groceryList = new ArrayList<>();
			
			for (int i = 0; i < items1.size(); i++) {
				groceryList.add(new ListItem(items1.get(i),3));
				
			}
		
			user.setGroceryList(groceryList);
			testSearch(user.getGroceryList());

	
		} catch (Exception  e) {
			e.printStackTrace();
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
