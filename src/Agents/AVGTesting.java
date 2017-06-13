package Agents;

import java.util.ArrayList;
import java.util.Random;

import Inventory.InventoryManager;
import Inventory.Item;

public class AVGTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InventoryManager.getInstance().DBInit();
		UserManager.getInstance().DBInit();
		ArrayList<User> users = UserManager.getInstance().getAllUsers();
		ArrayList<Item> items = InventoryManager.getInstance().getAllItems();
		System.out.println(items.size());
		User u = users.get(0);
		Random r = new Random();
		ArrayList<Transaction> transactions = new ArrayList<>();
		TrendingAgent agent = new TrendingAgent(transactions);
		for(int iter = 0; iter<5; iter++){
			for(int index = 0; index<10; index++){
				for(Item i: items){
					u.userPickItem(i, r.nextInt(10));
				}
				agent.getCurrentIteration().add(new Transaction("","",""+index,u.getVirtualCart()));
				u.setVirtualCart(new ArrayList<>());
			}
			agent.changeIteration();
		}
		agent.determineMinStock();
		agent.determineTrends();

	}

}
