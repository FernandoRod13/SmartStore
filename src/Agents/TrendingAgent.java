package Agents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import Inventory.InventoryManager;
import Inventory.Item;
import Inventory.ListItem;

public class TrendingAgent {

	ArrayList<Transaction> transactions;
	long totalSales;
	ArrayList<TrendItem> trendItems;
	Map<String, TransactionItem> titems;
	
	public TrendingAgent(ArrayList<Transaction> transactions){
		this.transactions = transactions;
		trendItems = new ArrayList<>();
		titems = new HashMap<>();
	}
	
	public void determineProb(){
		int from = 0, to;
		for(TrendItem titem: trendItems){
			to = from + round((float) titem.sales/totalSales);
			titem.setRange(from, to);
			from = to;
		}
	}
	
	public int round(float numb){
		int current = (int) numb;
		if(numb-current>=.5f) current++;
		return current;
	}

	public void determineTrends(){
		Map<String, Integer> trends = new HashMap<>();
		Map<String, Item> items = new HashMap<>();
		totalSales = 0;
		for(Transaction trans: transactions){ //for every transaction
			for(ListItem item: trans.getItems()){ //for every item in the transaction
				//add transaction information
				if(!trends.containsKey(item.getItem().getId())){
					trends.put(item.getItem().getId(), item.getAmount());
					items.put(item.getItem().getId(), item.getItem());
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
			trendItems.add(new TrendItem(items.get(key), trends.get(key)));
			//			titems.put(key, trends.get(key));
		}

		Collections.sort(trendItems);
	}


	public void determineMinStock(){
		//TrendsBased?

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
		double avg;
		for(TransactionItem i: titems.values()){
			avg = i.getAverage();
			System.out.println(i.getItem());
			System.out.println("AVG "+avg);
			System.out.println("STD Dev "+i.getStandardDeviation(avg));
			System.out.println("Lower: " + i.getLowerMin());
			System.out.println("Upper: " + i.getUpperMin());
			System.out.println("\n");

		}

		//Get a list of values and sort according to sales

		//now should visit every TrendItem and change min, max



	}

	public void setCapacities(){
		int cap = 0;
		TransactionItem item;
		for(int index=0; index<trendItems.size(); index++){
			item = titems.get(trendItems.get(index).getItem().getId());
			if(index<trendItems.size()*.3){
				cap =item.getUpperMin()*30;
			}
			else cap = item.getLowerMin()*30;

			InventoryManager.getInstance().setMinCapacity(trendItems.get(index).getItem(), cap);
			InventoryManager.getInstance().setMaxCapacity(trendItems.get(index).getItem(), cap*2);
		}
	}


	private class TrendItem implements Comparable<TrendItem>{
		private long sales;
		private Item item;
		private int from, to;
		TrendItem(Item i, long s){
			item =i;
			sales = s;

		}

		public Item getItem(){
			return item;
		}
		
		public void setRange(int from, int to){
			this.from = from;
			this.to = to;
		}
		
		public int getFrom(){
			return from;
		}
		
		public int getTo(){
			return to;
		}

		@Override
		public int compareTo(TrendItem item) {
			// TODO Auto-generated method stub
			return (int) (item.sales - sales);
		}

	}
	
	

	public void printTrending(){
		for(TrendItem item: trendItems){
			System.out.println(item.getItem().getId()+" "+item.sales);
		}
		System.out.println();
	}

	/**
	 * This Item contains all the necessary information to get the average sales per day for any iteration
	 * @author mario
	 *
	 */
	private class TransactionItem implements Comparable<TransactionItem>{
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
