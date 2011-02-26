package edu.aueb.cs.uw.core;

public class Tray {
	
	public static final int TRAY_SIZE=7;
	
	private Tile [] tray;
	private TilePool pool;
	private int playerID;
	private int numUnusedTiles;
	
	public Tray(int playerID,TilePool pool){
		tray=new Tile[TRAY_SIZE];
		this.setPlayerID(playerID);
		this.setNumUnusedTiles(0);
		this.setPool(pool);
	}

	public Tray(Tray t){
		tray=new Tile[TRAY_SIZE];
		for(int i=0;i<t.getNumUnusedTiles();i++)
			tray[i]=new Tile(t.getTile(i));
		this.setPlayerID(t.getPlayerID());
		this.setNumUnusedTiles(t.getNumUnusedTiles());
		this.setPool(new TilePool(t.getPool()));
	}
	
	public int fillTray(){
		Tile t;
		int neededTiles=TRAY_SIZE-numUnusedTiles;
		int addedTiles=0;
		for(int i=0;i<neededTiles;i++){
			if(pool.getNumOfTiles()>0){
				t=pool.getTile();
				t.setPlayerID(playerID);
				tray[getNextFreeSpace()]=t;
				addedTiles++;
			} else break;
		}
		setNumUnusedTiles(getNumUnusedTiles() + addedTiles);
		return addedTiles;
	}
	
	public Tile useTile(int i){
		if((tray[i]==null)||(i>=TRAY_SIZE)
				||(i<0))
			return null;
		Tile t=tray[i];
		for(int j=i;j<getNumUnusedTiles()-1;j++)
			tray[j]=tray[j+1];
		tray[getNumUnusedTiles()-1]=null;
		setNumUnusedTiles(getNumUnusedTiles() - 1);
		return t;
	}
	
	private int getNextFreeSpace() {
		for(int i=0;i<TRAY_SIZE;i++){
			if(tray[i]==null)
				return i;
		}
		return -1;
	}

	
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public int getPlayerID() {
		return playerID;
	}


	public void setPool(TilePool pool) {
		this.pool = pool;
	}

	public TilePool getPool() {
		return pool;
	}
	
	public boolean switchTile(int tilePos){
		if(!pool.hasMoreTiles())
			return false;
		tray[tilePos]=pool.switchTile(tray[tilePos]);
		return true;
	}
	
	public int getNumUnusedTiles(){
		return this.numUnusedTiles;
	}

	public void setNumUnusedTiles(int numUnusedTiles) {
		this.numUnusedTiles = numUnusedTiles;
	}
	
	public String getTileLetter(int tile){
		return Character.toString(tray[tile].getLetter());
	}
	
	public void addTempRemovedTile(Tile t,int i){
		tray[i]=t;
	}
	
	public void addTile(Tile t){
		for(int i=0;i<tray.length;i++){
			if(tray[i]==null){
				tray[i]=t;
				setNumUnusedTiles(++numUnusedTiles);
				break;
			}
		}
	}
	
	public Tile temporaryRemoveTile(int i){
		Tile t=null;
		if(i>=0&&i<TRAY_SIZE){
			t=tray[i];
			tray[i]=null;
		}
		return t;
	}
	
	public Tile getTile(int i){
		if(i>=0&&i<TRAY_SIZE)
			return tray[i];
		return null;
	}
}
