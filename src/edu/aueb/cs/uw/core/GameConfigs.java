package edu.aueb.cs.uw.core;


import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

public class GameConfigs implements Parcelable{

	

	static final int D_NUM_PLAYERS=2;
	
	private Player [] players;
	
	private int numPlayers;
	
	
	public GameConfigs(){
		this.setNumPlayers(D_NUM_PLAYERS);
		players=new Player[D_NUM_PLAYERS];
		players[0]=new Player("Player1", Color.BLUE,0, null);
		players[1]=new Player("Player2", Color.RED,1, null);
	}
	
	public GameConfigs(int numPlayers,String [] names, int [] colors){
		this.setNumPlayers(numPlayers);
		players=new Player[numPlayers];
		for(int i=0;i<numPlayers;i++){
			players[i]=new Player(names[i], colors[i], i, null);
		}
	}
	
	public GameConfigs(Parcel in){
		readFromParcel(in);
	}
	
	public static final Parcelable.Creator<GameConfigs> CREATOR = new Parcelable.Creator<GameConfigs>() {
        public GameConfigs createFromParcel(Parcel in) {
            return new GameConfigs(in);
        }
 
        public GameConfigs[] newArray(int size) {
            return new GameConfigs[size];
        }
    };

	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}

	public int getNumPlayers() {
		return numPlayers;
	}
	
	public Player [] getPlayersList(){
		return players;
	}
	
	public void readFromParcel(Parcel in) {
		
		this.numPlayers=in.readInt();
		players=new Player[numPlayers];
		for (int i=0;i<numPlayers;i++)
			players[i]=new Player(in.readString(), in.readInt(), in.readInt(), null);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(numPlayers);
		for (int i=0;i<numPlayers;i++){
			dest.writeString(players[i].getNickname());
			dest.writeInt(players[i].getColor());
			dest.writeInt(players[i].getPlayerID());	
		}
	}
	
}
