package Inventory;

import java.util.Comparator;

public class LocationComparator implements Comparator<Location> {

	@Override
	public int compare(Location o1, Location o2) {
		 return o1.getGraphNodeIndex() - o2.getGraphNodeIndex();
	}
	

}
