
package Inventory;

import java.net.UnknownHostException;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
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
	
	
	public void createInventory(){
		try{
		
		Document canvas = new Document("name", "BBolitaaa")
		        .append("qty", 200)
		        .append("department", "Limpieza");
		
		inventory.insertOne(canvas);
		System.out.println("Succsesfully Insert Items in Inventory");
		
		}
		
		catch(Exception e){
			System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );
		}
	}
	
	public void addInventory(Boolean inFloor, Location location, int amount) {
		
		
		
		
		
	}
	
	public void requestInventory(Boolean inFloor, Location location, int amount){
		
	}
	
	public void removeExpiredInventory() {
		
	}
	
	public void changeProductLocation(Item item1, Item item2) {
		
	}
	
	public void removeInventory(Boolean inFloor, Item product, int amount){
		
	}
	/**
	 * This function is used to initiate the store layout data structure for stock inventory and store inventory
	 * @param layout layout Data structure that represents the store
	 */
	public void initStorage(ArrayList<Container> layout){
		this.stockInventory = layout;
		this.storeInventory = layout;
	}
	
	/**
	 * This function is the main function used to find items were every they are whether it be in store or in stock
	 * @param location location of the object
	 * @return Inventory item stored at the specified location
	 */
	public InventoryItem findItem(Location location) {
		if (location.isInStore()) {
			Container c = storeInventory.get(location.getContainer());
			if(location.isLeft()) {
				return c.getLeft().getColumn().get(location.getColumn()).getItems().get(location.getRow());
			}else {
				return c.getRight().getColumn().get(location.getColumn()).getItems().get(location.getRow());
			}
		}else {
			Container c = stockInventory.get(location.getContainer());
			if(location.isLeft()) {
				return c.getLeft().getColumn().get(location.getColumn()).getItems().get(location.getRow());
			}else {
				return c.getRight().getColumn().get(location.getColumn()).getItems().get(location.getRow());
			}
		}
	}
	
	/**
	 * This function is used to get the column of a specific location (mainly to add inventory to the specific row of said column).
	 * this function is implemented in the loadDataFromFiles in smartStore
	 * @param location specified location for the column
	 * @return the desired column object
	 */
	public Column getColumn(Location location) {
		if (location.isInStore()) {
			Container c = storeInventory.get(location.getContainer());
			if(location.isLeft()) {
				return c.getLeft().getColumn().get(location.getColumn());
			}else {
				return c.getRight().getColumn().get(location.getColumn());
			}
		}else {
			Container c = stockInventory.get(location.getContainer());
			if(location.isLeft()) {
				return c.getLeft().getColumn().get(location.getColumn());
			}else {
				return c.getRight().getColumn().get(location.getColumn());
			}
		}
	}
	/**
	 * This method is called by the sensor when the sensor receives a valid user interaction such as grabbing or returning an item
	 * @param loc the location of the sensor
	 * @param amount the amount of items return if positive or the amount of items taken if negative.
	 */
	public void recieveSensorNotification(Location loc, int amount) {
		
	}
}
