package edu.aueb.cs.uw.core;

public class Player {
	
	private int playerID;
	private Tray tray;
	
	public Player(){
		
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
	
}
