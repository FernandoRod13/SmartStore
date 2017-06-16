
package Inventory;


import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;



public class InventoryManager {


	private MongoDatabase db;

	private MongoCollection<Document> inventory;

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
//			inventory.drop();
//			db.createCollection("inventory");
//			inventory = db.getCollection("inventory"); 




			System.out.println("Succsesfully conected to DB: SmartStore: Inventory Manager Collection");



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

	 * @param inStoreAvailable

	 * @param stockAvailable

	 * @param retailPrice

	 * @param category

	 * @param cost

	 */

	public void addInventory(Location itemLocation, String name, int mincap, int maxcap, int inStoreAvailable,

			int stockAvailable, double retailPrice, String category, double cost) {


		try{

			Document location = convertLocationToDocument(itemLocation);

			Document newItem = new Document("name", name)
					.append("mincap", mincap)
					.append("maxcap", maxcap)
					.append("inStoreAvailable", inStoreAvailable)
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

	public ArrayList<Item> getAllItems(){


		List<Document> allItemsDoc = null;
		ArrayList<Item> allItems = new ArrayList<>();
		try{

			allItemsDoc = (List<Document>) inventory.find().into(

					new ArrayList<Document>());

			for(Document document : allItemsDoc){
			Item item = convertDocumentToItem(document);
			
			System.out.println(item);
			System.out.println("\n");
			allItems.add(item);

			}             
		}

		catch(Exception e){

			System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );
		}
		return allItems;
	}
	
	public void notifySensor(Item i){
		if(i.getInStsoreAvailable()<=i.getMincap()){ 
			System.out.println("Refilled");
			this.setAvailableItem(i.getLocation(), i.getMaxcap());
		}
		
	}
	
	

	/**

	 * It looks for the item that have that location in the Database

	 * @param itemLocation

	 * @return The information of the item that is find in that location

	 */

	public Item getSingleItem(Location itemLocation){

		List<Document> items = null;

		Document location = convertLocationToDocument(itemLocation);

		Document item = null;

		try{



			items = (List<Document>) inventory.find(eq("location", location)).into(new ArrayList<Document>());


			item = items.get(0);

//			System.out.println("Search Successfully");

//			System.out.println(IDToString(item));


		}

		catch(Exception e){

			System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );

		}

		return convertDocumentToItem(item);

	}
	
	public void setAvailableItem(Location itemLocation, int quantity){
		Document location = convertLocationToDocument(itemLocation);


		try{

//			Item item = getInstance().getSingleItem(itemLocation);

			inventory.findOneAndUpdate(eq("location", location),
					combine(set("inStoreAvailable",quantity)));
			
//			System.out.println("After the user buys the item the file was updated Successfully");

		}

		catch(Exception e){

			System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );
		}
		
	}
	
	public void setMinCapacity(Item item, int capacity){
		Document loc = this.convertLocationToDocument(item.getLocation());

		try{
			inventory.updateMany(eq("location", loc),

					combine(set("mincap",capacity )));
		}
		catch(Exception e){
			System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	
	public void setMaxCapacity(Item item, int capacity){
		Document loc = this.convertLocationToDocument(item.getLocation());		
		try{
			inventory.updateMany(eq("location", loc),

					combine(set("maxcap",capacity )));
			
		}
		catch(Exception e){
			System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage());
		}
	}



	/**

	 * Method to know if they currently amount in the floor is enough

	 * @param itemLocation

	 * @return

	 */

	public boolean needToBringItemsToFloor(Location itemLocation){


		try{

			Item item = getInstance().getSingleItem(itemLocation);

			int amountInFloor = item.getInStsoreAvailable();



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

		Item item = null;

		try{

			item = getInstance().getSingleItem(itemLocation);


			int currentInStoreValue = item.getInStsoreAvailable();

			int currentInStockValue = item.getStockAvailable();


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



	/**

	 * When the user attempt to buy an item the inventory subtracts those items

	 * from the inventory 

	 * @param itemLocation

	 * @param amount

	 */

	public void userBuysItem(Location itemLocation, int amount){

		Document location = convertLocationToDocument(itemLocation);


		try{

			Item item = getInstance().getSingleItem(itemLocation);


			int currentInStoreValue = item.getInStsoreAvailable();

			int newAmount = currentInStoreValue - amount;


			inventory.findOneAndUpdate(eq("location", location),

					combine(set("inStoreAvailable",newAmount )));
			
			this.notifySensor(item);


//			System.out.println("After the user buys the item the file was updated Successfully");

		}

		catch(Exception e){

			System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );


		}


	}
	
	/**
	 * This method is call when The user returns the item 
	 * @param itemLocation
	 * @param amount
	 */
	public void userReturnsItem(Location itemLocation, int amount){

		Document location = convertLocationToDocument(itemLocation);


		try{

			Item item = getInstance().getSingleItem(itemLocation);


			int currentInStoreValue = item.getInStsoreAvailable();

			int newAmount = currentInStoreValue + amount;


			inventory.findOneAndUpdate(eq("location", location),

					combine(set("inStoreAvailable",newAmount )));


			System.out.println("After the user returns the item the file was updated Successfully");

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
	 * This method convert the Document Object to Item object
	 * @param item
	 * @return
	 */
	public Item convertDocumentToItem(Document item) {
		Item newItem = null;
		try{
			
		String objectID = "" + item.getObjectId("_id");
		Document locationDoc = item.get("location",Document.class);
		
		int container = locationDoc.getInteger("container");
		boolean isLeft = locationDoc.getBoolean("isLeft");
		int column = locationDoc.getInteger("column");
		int row = locationDoc.getInteger("row");
		int node = locationDoc.getInteger("nodeIndex");
		Location location = new Location(container, isLeft, column, row, node);
	
		newItem = new Item(objectID, item.getString("name"), item.getDouble("retailPrice"),
				item.getString("category"), item.getInteger("mincap"),item.getInteger("maxcap"),
				item.getInteger("inStoreAvailable"),item.getInteger("inStockAvailable"),item.getDouble("cost"),
				location);
		}
		catch(Exception e){

			System.err.println("Error " +  e.getClass().getName() + ": " + e.getMessage() );
		}
		
		return newItem;
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

