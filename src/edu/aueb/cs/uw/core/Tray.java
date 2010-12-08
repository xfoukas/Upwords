package edu.aueb.cs.uw.core;

public class Tray {
	
	public static final int TRAY_SIZE=7;
	
	private Tile [] tray;
	private int playerID;
	private int numOfTiles;
	
	public Tray(int playerID){
		tray=new Tile[TRAY_SIZE];
		this.setPlayerID(playerID);
		this.setNumOfTiles(0);
	}

	public void beginRound(){
		/*TODO pull from pool*/
	}
	
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setNumOfTiles(int numOfTiles) {
		this.numOfTiles = numOfTiles;
	}



	public int getNumOfTiles() {
		return numOfTiles;
	}
}
