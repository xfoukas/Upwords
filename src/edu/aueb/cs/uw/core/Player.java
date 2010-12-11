package edu.aueb.cs.uw.core;

public class Player {
	
	private String nickname;
	private int playerID;
	private Tray tray;
	private TilePool pool;
	private int color;
	
	public Player(String nickname,int color, TilePool pool){
		this.setColor(color);
		this.setNickname(nickname);
		this.setPool(pool);
		this.tray=new Tray(playerID, pool);
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setTray(Tray tray) {
		this.tray = tray;
	}

	public Tray getTray() {
		return tray;
	}

	public void setPool(TilePool pool) {
		this.pool = pool;
	}

	public TilePool getPool() {
		return pool;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getColor() {
		return color;
	}
	
}
