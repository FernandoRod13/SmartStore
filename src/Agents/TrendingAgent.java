package Agents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TrendingAgent {
	
	ArrayList<Transaction> transactions;
	Map<String, Integer> trends;
	long totalSales;
	
	public TrendingAgent(ArrayList<Transaction> transactions){
		this.transactions = transactions;
		trends = new HashMap<>();
	}
	
	public void determineTrends(){
		totalSales = 0;
		for(Transaction trans: transactions){ //for every transaction
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
				 //user for budget distribution
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
	
	//TODO: get average sales per day for each Item, and set the minCapacity according to trending
	

}
