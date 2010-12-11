package edu.aueb.cs.uw.core;

public class Tray {
	
	public static final int TRAY_SIZE=7;
	
	private Tile [] tray;
	private TilePool pool;
	private int playerID;
	private int numOfTiles;
	
	public Tray(int playerID,TilePool pool){
		tray=new Tile[TRAY_SIZE];
		this.setPlayerID(playerID);
		this.setNumOfTiles(0);
		this.setPool(pool);
	}

	public int beginRound(){
		Tile t;
		int neededTiles=TRAY_SIZE-numOfTiles;
		int addedTiles=0;
		for(int i=0;i<neededTiles;i++){
			if(pool.getNumOfTiles()>0){
				t=pool.getTile();
				t.setPlayerID(playerID);
				tray[getNextFreeSpace()]=t;
				addedTiles++;
			} else break;
		}
		return addedTiles;
	}
	
	public Tile useTile(int i){
		if((tray[i]==null)||(i>=TRAY_SIZE)
				||(i<0))
			return null;
		return tray[i];
	}
	
	private int getNextFreeSpace() {
		for(int i=0;i<TRAY_SIZE;i++){
			if(tray[i]==null)
				return i;
		}
		return -1;
	}

	public int getNeededTiles(){
		return TRAY_SIZE-numOfTiles;
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

	public void setPool(TilePool pool) {
		this.pool = pool;
	}

	public TilePool getPool() {
		return pool;
	}
}
