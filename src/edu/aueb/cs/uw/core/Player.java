package edu.aueb.cs.uw.core;


public class Player {
	
	private String nickname;
	private int playerID;
	private Tray tray;
	private TilePool pool;
	private int color;
	private int score;
	
	public Player(String nickname,int color,int playerID, TilePool pool){
		this.setPlayerID(playerID);
		this.setColor(color);
		this.setNickname(nickname);
		this.setPool(pool);
		this.tray=new Tray(playerID, pool);
		this.setScore(0);
	}

	/*public Player(Parcel in){
		readFromParcel(in);
		this.setPool(null);
		this.tray=new Tray(playerID, pool);
		this.setScore(0);
	}*/
	
	/*public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }
 
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };*/
	
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
		this.tray=new Tray(playerID, pool);
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

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}
/*
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(nickname);
		dest.writeInt(color);
		dest.writeInt(playerID);		
	}
	
	public void readFromParcel(Parcel in) {
		this.nickname=in.readString();
		this.color=in.readInt();
		this.playerID=in.readInt();
	}*/
	
}
