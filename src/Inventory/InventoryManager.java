
package Inventory;

import java.net.UnknownHostException;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


public class InventoryManager {
	
	private MongoDatabase db;
	private MongoCollection<Document> inventory;
	
	private ArrayList<Container> storeInventory;
	private ArrayList<Container> stockInventory;
	
	private static InventoryManager instance = null;
	
	public static InventoryManager getInstance() {
		if(instance == null) instance = new InventoryManager();
		return instance;
	}
	/**
	 * This method create the connection to the database
	 */
	public void  DBInit(){
		 try{
			 	// Standard URI format: mongodb://[dbuser:dbpassword@]host:port/dbname
			 
			 	MongoClientURI uri  = new MongoClientURI("mongodb://dbuser:colegio@ds141401.mlab.com:41401/smartstore"); 
		        MongoClient client = new MongoClient(uri);
		        db = client.getDatabase(uri.getDatabase());
		        inventory = db.getCollection("inventory"); 
		        
		        System.out.println("Succsesfully conected to DB: SmartStore");
	         
	      }catch(Exception e){
	         System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );
	      }
	}
	/**
	 * Insert an item into the DataBase
	 * @param itemLocation
	 * @param name
	 * @param mincap
	 * @param maxcap
	 * @param inStsoreAvailable
	 * @param stockAvailable
	 * @param retailPrice
	 * @param category
	 * @param cost
	 */
	public void addInventory(Location itemLocation, String name, int mincap, int maxcap, int inStsoreAvailable,
			int stockAvailable, double retailPrice, String category, double cost) {
		
		try{
		Document location = convertLocationToDocument(itemLocation);
		
		Document newItem = new Document("name", name)
		        .append("mincap", mincap)
		        .append("maxcap", maxcap)
		        .append("inStoreAvailable", inStsoreAvailable)
		        .append("inStockAvailable", stockAvailable)
		        .append("retailPrice", retailPrice)
		        .append("cost", cost)
		        .append("category", category)
		        .append("location", location);
		       
		inventory.insertOne(newItem);
		
		}
		catch(Exception e){
			System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );
		}	
	}
	
	
	/**
	 * Get all the items in the inventory
	 * @return all the items in the inventory
	 */
	public List<Document> getAllItems(){
		
		List<Document> allItems = null;
		try{		
			 	allItems = (List<Document>) inventory.find().into(
					new ArrayList<Document>());
	 
	               for(Document document : allItems){
	            	   System.out.println("All items are");
	                   System.out.println(document);
	               }             
		}
		catch(Exception e){
			System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );
		}	
		return allItems;
	}
	
	
	/**
	 * It looks for the item that have that location in the Database
	 * @param itemLocation
	 * @return The information of the item that is find in that location
	 */
	public Document getSingleItem(Location itemLocation){
		List<Document> items = null;
		Document location = convertLocationToDocument(itemLocation);
		Document item = null;
		try{

			items = (List<Document>) inventory.find(eq("location", 
					location)).into(new ArrayList<Document>());
			
			item = items.get(0);
			System.out.println("Search Successfully");
			System.out.println(IDToString(item));
			
		}
		catch(Exception e){
			System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );
		}
		return item;
	}
	
	
	/**
	 * Method to know if they currently amount in the floor is enough
	 * @param itemLocation
	 * @return
	 */
	public boolean needToBringItemsToFloor(Location itemLocation){
		
		try{
			Document item = getInstance().getSingleItem(itemLocation);
			int amountInFloor = item.getInteger("inStoreAvailable");

			if(10 > amountInFloor){
				System.out.println("You have to move some items from the stock to the floor");
				return true;
			}
			else{
				System.out.println("The floor is good for now");
				return false;
			}
		}
		catch(Exception e){
			System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );
			return false;
		}
	}
	
	
	/**
	 * Method to transfer amount of items from the stock to the floor
	 * @param ammountToTransfer the amount to transfer from the stock to the floor
	 * @param itemLocation the location of the item 
	 * @return
	 */
	public boolean transferFromStockToFloor(int ammountToTransfer,Location itemLocation){
		Document location = convertLocationToDocument(itemLocation);
		Document item = null;
		try{
			item = getInstance().getSingleItem(itemLocation);
			
			int currentInStoreValue = item.getInteger("inStoreAvailable");
			int currentInStockValue = item.getInteger("inStockAvailable");
			
			inventory.findOneAndUpdate(eq("location", location),
					combine(set("inStoreAvailable",currentInStoreValue + ammountToTransfer ),
					set("inStockAvailable", currentInStockValue - ammountToTransfer )));
			System.out.println("File updated Successfully");
		
		}
		catch(Exception e){
			System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * This method change the location of an item, remember that the "Gondolas" have the same location TAG in the Floor
	 * And in the stock, this is to make it easier to find in both sides
	 * @param newLocation the new location where the item is going to be put in the Store and in the stock
	 * @param currentLocation
	 * @return true if the transaction is complete false otherwise
	 * 
	 */
	public boolean updateLocation(Location newLocation, Location currentLocation){
		Document newLocationDocument = convertLocationToDocument(newLocation);
		Document currentLocationDocument = convertLocationToDocument(currentLocation);
		
		try{
			inventory.findOneAndUpdate(eq("location", currentLocationDocument),
					combine(set("location", newLocationDocument )));
			System.out.println("File updated Successfully");
		}
		catch(Exception e){
			System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * This method check if the minimum amount of items has been reached,if this happens 
	 * it return a true indicating to the inventory manager that he needs to buy more items
	 * @param itemLocation
	 * @return
	 */
	public boolean minimunCapacityReach(Location itemLocation){
		
		try{
			Document item = getInstance().getSingleItem(itemLocation);
			int minimumRequired = item.getInteger("mincap");
			int currentlyTotalAmount = item.getInteger("inStoreAvailable") + item.getInteger("inStockAvailable");

			if(minimumRequired > currentlyTotalAmount){
				System.out.println("You have to buy more items for the stock");
				return true;
			}
			else{
				System.out.println("The stock is good for now");
				return true;
			}
		}
		catch(Exception e){
			System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );
			return false;
		}
	}
	
	
	/**
	 * When the user attempt to buy an item the inventory subtracts those items
	 * from the inventory 
	 * @param itemLocation
	 * @param amount
	 */
	public void userBuysItem(Location itemLocation, int amount){
		Document location = convertLocationToDocument(itemLocation);
		
		try{
			Document item = getInstance().getSingleItem(itemLocation);
			
			int currentInStoreValue = item.getInteger("inStoreAvailable");
			int newAmount = currentInStoreValue - amount;
			
			inventory.findOneAndUpdate(eq("location", location),
					combine(set("inStoreAvailable",newAmount )));
			
			System.out.println("After the user buys the item the file was updated Successfully");
		}
		catch(Exception e){
			System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );
			
		}
		
	}

	/**
	 * This piece of code is use many times in the class so i made it a method
	 * @param location
	 * @return
	 */
	public Document convertLocationToDocument(Location location){
		Document newLocation = new Document("container",location.getContainer())
				.append("isLeft", location.isLeft())
				.append("column", location.getColumn())
				.append("row", location.getRow());
	return newLocation;
	}
	
	/**
	 * This method returns a pretty String of the Item Document(ID)
	 * @param item
	 * @return
	 */
	public String IDToString(Document item){
		
		String result = "Item ID: "  + item.getObjectId("_id") + "\n";
		result = result + "Name: " + item.getString("name") + "\n";
		result = result + "Minimum capacity: "  + item.getInteger("mincap") +  "\n";
		result = result + "Maximum capacity: "  + item.getInteger("maxcap") +  "\n";
		result = result + "Items available in Store: " + item.getInteger("inStoreAvailable") +  "\n";
		result = result + "Items available in Stock: " + item.getInteger("inStockAvailable") +  "\n";
		result = result + "Retail Price: " + item.getDouble("retailPrice") +  "\n";
		result = result + "Cost of the item to store: " + item.getDouble("cost") +  "\n";
		result = result + "Category: " + item.getString("category") +  "\n";
	
		return result;
	}
	/**
	 * This method return a pretty String of the Location Document
	 * @param location
	 * @return
	 */
	public String LDToString(Document location){
		String result = "Container: " + location.getString("container") + " ";
		result = result + "Left : " + location.getBoolean("isLeft") + " ";
		result = result + "Column : " + location.getInteger("column") + " ";
		result = result + "Row : " + location.getInteger("row") + "\n";
		
		return result;
	}
	
	
	
}
