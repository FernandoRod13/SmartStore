package Inventory;

public class Gondola {
	private int levels;
	private String id;
	private int isleID;
	private String isleGondolaID;
	
	public Gondola(int levels, String id, int isleID, String isleGondolaID) {
		super();
		this.levels = levels;
		this.id = id;
		this.isleID = isleID;
		this.isleGondolaID = isleGondolaID;
	}
	
	public Gondola(String[] splitfileLine){
		this(Integer.parseInt(splitfileLine[3]), splitfileLine[2], Integer.parseInt(splitfileLine[0]), splitfileLine[1]);
	}
	
	public int getIsleID() {
		return isleID;
	}

	public void setIsleID(int isleID) {
		this.isleID = isleID;
	}

	public int getLevels() {
		return levels;
	}
	
	public void setLevels(int levels) {
		this.levels = levels;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getIsleGondolaID() {
		return isleGondolaID;
	}

	public void setIsleGondolaID(String isleGondolaID) {
		this.isleGondolaID = isleGondolaID;
	}
	
	
}