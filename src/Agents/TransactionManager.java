package Agents;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Inventory.InventoryManager;
import Inventory.Item;
import Inventory.ListItem;



public class TransactionManager {
	
	private MongoDatabase db;

	private MongoCollection<Document> transactions; // this is actually the DB Collection(The table)
	
	private static TransactionManager instance = null;
	
	private ArrayList<Item> invItems;
	
	


	public static TransactionManager getInstance() {
		
	if(instance == null) instance = new TransactionManager();

	return instance;

	}

	/**

	* This method create the connection to the database

	*/

	public void  DBInit(){
		invItems = InventoryManager.getInstance().getAllItems();

	try{

	// Standard URI format: mongodb://[dbuser:dbpassword@]host:port/dbname

	 

	MongoClientURI uri  = new MongoClientURI("mongodb://dbuser:colegio@ds141401.mlab.com:41401/smartstore"); 

	        MongoClient client = new MongoClient(uri);

	        db = client.getDatabase(uri.getDatabase());
	        
	        transactions = db.getCollection("transactions");
//	        transactions.drop();
//	        db.createCollection("transactions");
//	        transactions = db.getCollection("transactions");

	        

	        System.out.println("Succsesfully conected to DB: SmartStore: Transations Collection");
	        
	         

	      }catch(Exception e){

	        System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );

	      }

	}
	
	
	public ArrayList<Transaction> getAllTransactions(){


		List<Document> allTransactionsDoc = null;
		ArrayList<Transaction> allTransactions = new ArrayList<>();
		try{

			allTransactionsDoc = transactions.find().into(

					new ArrayList<Document>());

			for(Document document : allTransactionsDoc){
			Transaction trans = convertDocumentToTransaction(document);
			
//			System.out.println(trans);
//			System.out.println("\n");
			allTransactions.add(trans);

			}             
		}

		catch(Exception e){

			System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );
		}
		System.out.println(allTransactions.size());
		return allTransactions;
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
	
	
	public void addTransaction(ArrayList<ListItem> itemsArray, Date date, String userId,
			double totalPrice) {

		try{

			
			Document items = new Document();
			Document itemList = new Document();
//			ArrayList<Document> listItemsDocs = new ArrayList<>();
			items.append("size", itemsArray.size());
			Document li;
			for(int index = 0; index < itemsArray.size(); index++){


				
				li = new Document()
						.append("itemId", itemsArray.get(index).getItem().getId())
						.append("itemName", itemsArray.get(index).getItem().getName())
						.append("qty", itemsArray.get(index).getAmount());
				
				itemList.append(index+"", li);//***********************************************************************

			}
			
			items.append("items", itemList);
			
			System.out.println("The items are:");
			System.out.println(items);
			

			Document transaction = new Document("Date", date)
					.append("userId", userId)
					.append("totalPrice", totalPrice)
					.append("items", items);
			
			System.out.println("The transaction is:");
			System.out.println(transaction);

			transactions.insertOne(transaction);
		
			
		}
		catch(Exception e){

			System.err.println("Error" +  e.getClass().getName() + ": " + e.getMessage() );
		}
	}
	
	public Item matchItem(String objectId){
		for(int index=0; index<invItems.size(); index++){
			if(invItems.get(index).getId().contentEquals(objectId)) return invItems.get(index);
		}
		return null;
	}

	
	
	public Transaction convertDocumentToTransaction(Document transactionsDoc){

		Transaction newTransaction = null;
		ArrayList<Document> docs = new ArrayList<>();;
		try{

			String transactionId = "" + transactionsDoc.getObjectId("_id");
			String userId = transactionsDoc.getString("userId");
			Document items = (Document) transactionsDoc.get("items");
			int size = items.getInteger("size");
			ArrayList<ListItem> itemsArray = new ArrayList<>();
			ListItem li;
			Item it;
			Document doc;
			Document itemsDoc = (Document) items.get("items");
			
			for(int index=0; index<size; index++){
				doc = (Document) itemsDoc.get(index+"");
				it = this.matchItem(doc.getString("itemId"));
				
				li = new ListItem(it, doc.getInteger("qty"));
				
				itemsArray.add(li);
				
				
				
			}
			newTransaction = new Transaction(userId, transactionId, transactionsDoc.getDate("Date"),
					transactionsDoc.getDouble("totalPrice"), itemsArray);

		}
		catch(Exception e){
			
			System.err.println("Mario " +  e.getClass().getName() + ": " + e.getMessage() );
		}


		return newTransaction;
	}
	
	
	
	
}
