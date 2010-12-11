package edu.aueb.cs.uw.core;

import android.graphics.Color;

public class GameConfigs {

	static final int D_NUM_PLAYERS=2;
	
	private Player [] players;
	
	private int numPlayers;
	
	
	public GameConfigs(){
		this.setNumPlayers(D_NUM_PLAYERS);
		players=new Player[D_NUM_PLAYERS];
		players[0]=new Player("Player1", Color.BLUE, null);
		players[1]=new Player("Player2", Color.RED, null);
	}
	
	public GameConfigs(int numPlayers,String [] names, int [] colors){
		this.setNumPlayers(numPlayers);
		players=new Player[numPlayers];
		for(int i=0;i<numPlayers;i++){
			players[i]=new Player(names[i], colors[i], null);
		}
	}

	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}

	public int getNumPlayers() {
		return numPlayers;
	}
	
	public Player [] getPlayersList(){
		return players;
	}
	
}
