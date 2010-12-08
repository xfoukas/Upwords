package edu.aueb.cs.uw.core;

public class Tile {

	public static final int NO_PLAYER_ID=-1;
	
	private int playerID;
	private char letter;
	private int age;
	
	public Tile(char letter,int playerID){
		this.setLetter(letter);
		this.setPlayerID(playerID);
		this.setAge(0);
	}
	
	public Tile(char letter){
		this.setLetter(letter);
		this.setPlayerID(0);
		this.setAge(0);
	}
	
	public Tile(){
		this(' ');
		this.setAge(0);
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public char getLetter() {
		return letter;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}
	
	public int incrementAge(){
		return ++age;
	}
	
	public int decrementAge(){
		return (age>0)?--age:0;
	}
	
	@Override
	public boolean equals(Object o) {
		Tile tile;
		if(o==null)
			return false;
		if(this==o)
			return true;
		if(!(o instanceof Tile))
			return false;
		tile=(Tile)o;
		return this.age==tile.getAge()
			&&this.letter==tile.getLetter()
			&&this.playerID==tile.getPlayerID();		
	}
	
}
