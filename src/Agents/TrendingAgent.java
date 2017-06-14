package Agents;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import Inventory.ListItem;

public class TrendingAgent {

	//	ArrayList<Transaction> transactions;
//	ArrayList<ArrayList<Transaction>> iterations;//for testing purposes only, later will be replaced with db queries
	ArrayList<Transaction> transactions;
	Map<String, Integer> trends;
	long totalSales;

	public TrendingAgent(ArrayList<Transaction> transactions){
//		iterations = new ArrayList<>();
//		iterations.add(transactions);
		this.transactions = transactions;
		trends = new HashMap<>();
	}
	

	public void determineTrends(){
		totalSales = 0;
			for(Transaction trans: transactions){ //for every transaction
				for(ListItem item: trans.getItems()){ //for every item in the transaction
					//add transaction information
					if(!trends.containsKey(item.getItem().getId())){
						trends.put(item.getItem().getId(), item.getAmount());
						//number of visits per user will be considered later
					}
					else{
						trends.put(item.getItem().getId(), item.getAmount()+trends.get(item.getItem().getId()));
						//number of visits per user will be considered later

					}
					//used for budget distribution
					//number of visits per user will be considered later


				}


			}
		for(String key: trends.keySet()){
			totalSales+=trends.get(key);
		}
	}


	public Map<String, Double> budgetDistribution(double budget){
		Map<String, Double> budgetDis = new HashMap<>();

		for(String key: trends.keySet()){
			//convert to dollars
			double value =(double) trends.get(key)/(double) totalSales*budget;
			double toDollars = value*100;
			if(toDollars-(int)toDollars>=.5) toDollars++;

			//add
			budgetDis.put(key, (int) (toDollars)/100d);
		}
		return budgetDis;

	}

	public void determineMinStock(){
		//TrendsBased?
		Map<String, TransactionItem> titems = new HashMap<>();
		//convert
		String key;
			for(Transaction t: transactions){
				for(ListItem item: t.getItems()){
					key = item.getItem().getId();
					if(!titems.containsKey(key)){ //if the transaction item has not been created yet
						titems.put(key, new TransactionItem(key));
					}
					titems.get(key).addSale(t.getDate(), item.getAmount()); //add sale
				}
			}
		
		//print lower and upper min stock
		for(TransactionItem i: titems.values()){
			System.out.println(i.getItem());
			System.out.println("AVG "+i.getAverage());
			System.out.println("STD Dev "+i.getStandardDeviation(i.getAverage()));
			System.out.println("Lower: " + i.getLowerMin());
			System.out.println("Upper: " + i.getUpperMin());
			System.out.println("\n");

		}

		//Get a list of values and sort according to sales

		//now should visit every TrendItem and change min, max



	}


	//TODO: get average sales per day for each Item, and set the minCapacity according to trending

	/**
	 * This Item contains all the neccesary information to get the average sales per day for any iteration
	 * @author mario
	 *
	 */
	public class TransactionItem implements Comparable<TransactionItem>{
		private String id;
		private Map<String, Long> salesPerDay; //<Date, Sales>
		private String item;

		public TransactionItem(String i){
			item = i;
			salesPerDay = new HashMap<>();
		}

		public void addSale(Date date, int amount){
			long a;
			if(!salesPerDay.containsKey(date)){
				a = amount;
			}else{
				a = salesPerDay.get(date.toString())+amount;
			}
			salesPerDay.put(date.toString(), a);
		}

		public double getAverage(){
			if(salesPerDay.isEmpty()){
				return 0;

			}
			double avg = 0;
			for(Long am: salesPerDay.values()){
				avg+=am;
			}

			return avg/(salesPerDay.size());
		}

		public double getStandardDeviation(double avg){
			if(salesPerDay.isEmpty()) return 0;
			double dev = 0;
			for(Long value: salesPerDay.values()){
				dev+=Math.pow(value-avg, 2);
			}

			return Math.sqrt(dev/salesPerDay.size());
		}

		public int getUpperMin(){
			double avg = getAverage();
			return (int) (avg+getStandardDeviation(avg));

		}

		public int getLowerMin(){
			double avg = getAverage();
			return (int) (avg-getStandardDeviation(avg)/2) + 1; //at least one
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Map<String, Long> getSalesPerDay() {
			return salesPerDay;
		}

		public void setSalesPerDay(Map<String, Long> salesPerDay) {
			this.salesPerDay = salesPerDay;
		}

		public String getItem() {
			return item;
		}

		public void setItem(String item) {
			this.item = item;
		}

		@Override
		public int compareTo(TransactionItem item) {
			// TODO Auto-generated method stub
			double avg1 = getAverage(), avg2 = item.getAverage();

			if(avg1>avg2) return 1;
			if(avg1==avg2) return 0;
			return -1;
		}




	}
}
