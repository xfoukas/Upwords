package edu.aueb.cs.uw.core;

public class GameConfigs {

	static final int D_NUM_PLAYERS=2;
	
	public enum D_COLORS {
		BLACK,BLUE,YELLOW,RED
	}
	
	private int numPlayers;
	
	
	public GameConfigs(){
		this(D_NUM_PLAYERS);
	}
	
	public GameConfigs(int numPlayers){
		this.setNumPlayers(numPlayers);
	}

	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}

	public int getNumPlayers() {
		return numPlayers;
	}
	
}
