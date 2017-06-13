package Agents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TrendingAgent {

//	ArrayList<Transaction> transactions;
	ArrayList<ArrayList<Transaction>> iterations;//for testing purposes only, later will be replaced with db queries
	Map<String, Integer> trends;
	long totalSales;

	public TrendingAgent(ArrayList<Transaction> transactions){
		iterations = new ArrayList<>();
		iterations.add(transactions);
		trends = new HashMap<>();
	}

	public void determineTrends(){
		totalSales = 0;
		for(ArrayList<Transaction> list: iterations)
		for(Transaction trans: list){ //for every transaction
			for(String key: trans.getItems().keySet()){ //for every item in the transaction
				//add transaction information
				if(!trends.containsKey(key)){
					trends.put(key, trans.getItems().get(key));
					//number of visits per user will be considered later
				}
				else{
					trends.put(key, trans.getItems().get(key)+trends.get(key));
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

  public void addTransaction(Transaction transaction){
     getCurrentIteration().add(transaction);
  }

	public void determineMinStock(){
		//TrendsBased?
		Map<String, TransactionItem> titems = new HashMap<>();
		//convert
		for(ArrayList<Transaction> list: iterations)
		for(Transaction t: list){
			for(String k: t.getItems().keySet()){
				if(!titems.containsKey(k)){ //if the transaction item has not been created yet
					titems.put(k, new TransactionItem(k));
				}
				titems.get(k).addSale(t.getDate(), t.getItems().get(k)); //add sale
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

	public void changeIteration(){
		if(iterations.size()==6){
			iterations.remove(0);
		}
		iterations.add(new ArrayList<>());
	}

	public ArrayList<Transaction> getCurrentIteration(){
		if(iterations.isEmpty()) return null; //throw exception if necessary later
		return iterations.get(iterations.size()-1);
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

		public void addSale(String date, int amount){
			long a;
			if(!salesPerDay.containsKey(date)){
				a = amount;
			}else{
				a = salesPerDay.get(date)+amount;
			}
			salesPerDay.put(date, a);
		}

		public double getAverage(){
			if(salesPerDay.isEmpty()){
				return 0;

			}
			double avg = 0;
			for(Long am: salesPerDay.values()){
				avg+=am;
			}
			System.out.println();

			return avg/salesPerDay.size();
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
