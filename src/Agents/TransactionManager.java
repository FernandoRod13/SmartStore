package Agents;

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
import java.util.Date;

import com.mongodb.Block;

import com.mongodb.client.MongoCursor;

import static com.mongodb.client.model.Filters.*;

import com.mongodb.client.result.DeleteResult;

import static com.mongodb.client.model.Updates.*;

import com.mongodb.client.result.UpdateResult;


import Inventory.InventoryManager;
import Inventory.Item;
import Inventory.ListItem;
import Inventory.Location;

import java.util.ArrayList;

import java.util.List;

import java.util.Arrays;



public class TransactionManager {
	
	private MongoDatabase db;

	private MongoCollection<Document> transactions; // this is actually the DB Collection(The table)
	
	private static TransactionManager instance = null;
	
	


	public static TransactionManager getInstance() {

	if(instance == null) instance = new TransactionManager();

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

	        transactions = db.getCollection("transactions"); 

	       

	        System.out.println("Succsesfully conected to DB: SmartStore: Transations Collection");

	         

	      }catch(Exception e){

	        System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );

	      }

	}
	
	
	public ArrayList<Transaction> getAllTransactions(){


		List<Document> allTransactionsDoc = null;
		ArrayList<Transaction> allTransactions = new ArrayList<>();
		try{

			allTransactionsDoc = (List<Document>) transactions.find().into(

					new ArrayList<Document>());

			for(Document document : allTransactionsDoc){
			User user = convertDocumentToUser(document);
			
			System.out.println(user);
			System.out.println("\n");
			allUsers.add(user);

			}             
		}

		catch(Exception e){

			System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );
		}
		return allUsers;
	}
	
	/**
	 * 
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
	
	
	public void addTransaction( ArrayList<ListItem> itemsArray, Date date, String userId,
			double totalPrice) {

		try{

			Document items = new Document("itemId": )

			Document transaction = new Document("Date", date)
					.append("userId", userId)
					.append("totalPrice", totalPrice)
					.append("items", items);

			transactions.insertOne(transaction);
		}
		catch(Exception e){

			System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );
		}
	}

	
	
	public User convertDocumentToTransactions(Document TransactionsDoc){

		User newUser = null;

		try{

			String objectID = "" + user.getObjectId("_id");
			newUser = new User(objectID, user.getString("name"), user.getString("lastName"));

		}
		catch(Exception e){

			System.err.println("Error Soy un pendejo" +  e.getClass().getName() + ": " + e.getMessage() );
		}

		return newUser;
	}
	
	
	
	
}
