package Inventory;
public class Location {
	
	private String gondolaID;
	private String gondolaSide;
	private int gondolaLevel;
	private String aisleGondolaID;
	private int aisleID;
	private boolean infloor;
	
	
	public Location(String gondolaID, String gondolaSide, int gondolaLevel,
			String aisleGondolaID, int aisleID, boolean infloor) {
		super();
		this.gondolaID = gondolaID;
		this.gondolaSide = gondolaSide;
		this.gondolaLevel = gondolaLevel;
		this.aisleGondolaID = aisleGondolaID;
		this.aisleID = aisleID;
		this.infloor = infloor;
	}
	
	public Location(String[] splitFileLine){
		this(splitFileLine[0],splitFileLine[1],Integer.parseInt(splitFileLine[2]),splitFileLine[3],Integer.parseInt(splitFileLine[4]));
	}

	public String getGondolaID() {
		return gondolaID;
	}

	public void setGondolaID(String gondolaID) {
		this.gondolaID = gondolaID;
	}

	public String getGondolaSide() {
		return gondolaSide;
	}

	public void setGondolaSide(String gondolaSide) {
		this.gondolaSide = gondolaSide;
	}

	public int getGondolaLevel() {
		return gondolaLevel;
	}

	public void setGondolaLevel(int gondolaLevel) {
		this.gondolaLevel = gondolaLevel;
	}

	public String getAisleGondolaID() {
		return aisleGondolaID;
	}

	public void setAisleGondolaID(String aisleGondolaID) {
		this.aisleGondolaID = aisleGondolaID;
	}

	public int getAisleID() {
		return aisleID;
	}

	public void setAisleID(int aisleID) {
		this.aisleID = aisleID;
	}
	
	

}
