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

import com.mongodb.Block;

import com.mongodb.client.MongoCursor;

import static com.mongodb.client.model.Filters.*;

import com.mongodb.client.result.DeleteResult;

import static com.mongodb.client.model.Updates.*;

import com.mongodb.client.result.UpdateResult;


import Inventory.InventoryManager;
import Inventory.Item;
import Inventory.Location;

import java.util.ArrayList;

import java.util.List;

import java.util.Arrays;

public class UserManager {

	private MongoDatabase db;

	private MongoCollection<Document> users; // this is actually the DB Collection(The table)
	
	private ArrayList<User> activeUsers;

	private static UserManager instance = null;
	
	


	public static UserManager getInstance() {

	if(instance == null) instance = new UserManager();

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

	        users = db.getCollection("users"); 

	       

	        System.out.println("Succsesfully conected to DB: SmartStore: UserManager Collection");

	         

	      }catch(Exception e){

	        System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );

	      }

	}
	
	
	public ArrayList<User> getAllUsers(){


		List<Document> allUsersDoc = null;
		ArrayList<User> allUsers = new ArrayList<>();
		try{

			allUsersDoc = (List<Document>) users.find().into(

					new ArrayList<Document>());

			for(Document document : allUsersDoc){
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
	 * Create a new user in the database
	 * @param name
	 * @param lastName
	 */
	public void addUserToDB(String name, String lastName) {

		try{

			Document newUser = new Document("name", name)

					.append("lastName", lastName);

			users.insertOne(newUser);

		}
		catch(Exception e){

			System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );
		}
	}
	
	
	/**

	* Get all the items in the inventory

	* @return all the items in the inventory

	*/

	public User convertDocumentToUser(Document user){

		User newUser = null;

		try{

			String objectID = "" + user.getObjectId("_id");
			newUser = new User(objectID, user.getString("name"), user.getString("lastName"));

		}
		catch(Exception e){

			System.err.println(e.getClass().getName() + ": " + e.getMessage() );
		}

		return newUser;
	}

	
	
	
}
