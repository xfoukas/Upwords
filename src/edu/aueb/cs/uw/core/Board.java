package edu.aueb.cs.uw.core;

import java.util.LinkedList;

public class Board {
	
	public static final int BOARD_SIZE=10;
	
	private TileStack [][] board;
	private LinkedList<AddedTile> tilesAdded;
	private int turn;
	private boolean isFirstWord;
	
	private class AddedTile{
		private int x,y;
		private Tile tile;
		
		public AddedTile(int x,int y,Tile tile){
			this.setX(x);
			this.setY(y);
			this.setTile(tile);
		}
		
		public void setX(int x) {
			this.x = x;
		}
		public int getX() {
			return x;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getY() {
			return y;
		}
		public void setTile(Tile tile) {
			this.tile = tile;
		}
		public Tile getTile() {
			return tile;
		}
	}
	
	public Board(){
		board=new TileStack[BOARD_SIZE][BOARD_SIZE];
		tilesAdded=new LinkedList<AddedTile>();
		setTurn(1);
		isFirstWord=true;
	}
	
	public int commit(){
		int score;
		if(!isValidPlacement())
			return -1;
		score=getScore();
		endTurn();
		return score;
	}
	
	public int getScore(){
		int score=0;
		if(allLettersUsed())
			score+=10;
		for(AddedTile t : tilesAdded){
			/*BONUS LETTER*/
			score+=2;
		}
		
		/*TODO*/
		return score;
	}
	
	private boolean allLettersUsed(){
		return (tilesAdded.size()==Tray.TRAY_SIZE)?
				true:false;
	}
	
	public boolean isValidPlacement(){
		int size;
		size=tilesAdded.size();
		if(size==0)
			return false;
		if(isFirstWord){
			/*TODO must be in a row
			 * What I have done is wrong
			 */
			boolean hasCentralCell=false;
			for(AddedTile t : tilesAdded){
				if(isCentralCell(t.getX(), t.getY()))
					hasCentralCell=true;
				if(!isValidTilePlacement(t))
					return false;
			}
			return hasCentralCell;
		}
		for(AddedTile t : tilesAdded){
			if(!isValidTilePlacement(t))
				return false;
		}
		return true;
	}
	
	public boolean isValidTilePlacement(AddedTile tile){
		return hasNeighbours(tile.getX(), tile.getY());
	}
	

	public boolean hasNeighbours(int x,int y){
		return (hasTile(x-1, y)||hasTile(x+1, y)
				||hasTile(x, y-1)||hasTile(x, y+1));
	}
	
	public boolean hasTile(int x,int y){
		if((x>=0)&&(x<BOARD_SIZE)&&
				(y>=0)&&(y<BOARD_SIZE)){
			return !(board[x][y].isEmpty());
		}
		return false;
	}
	
	private boolean isCentralCell(int x,int y){
		int topLeft=(BOARD_SIZE/2)-2;
		return (x==topLeft&&y==topLeft)
				||(x==topLeft&&y==topLeft+1)
				||(x==topLeft+1&&y==topLeft)
				||(x==topLeft+1&&y==topLeft+1);
	}
	
	public void undoAll(){
		for(AddedTile t : tilesAdded){
			t.getTile().setAge(0);
			board[t.getX()][t.getY()].deleteTop();
		}
	}
	
	public boolean addTile(Tile tile,int x, int y){
		boolean added;
		if((board[x][y])==null)
			board[x][y]=new TileStack();
		else if(!(canAddTile(x, y,tile)))
			return false;
		added=board[x][y].addTile(tile);
		if(added) {
			tile.setAge(getTurn());
			tilesAdded.add(new AddedTile(x, y, tile));
		}
		return added;
	}
	
	public boolean removeTile(int x,int y){
		TileStack ts=board[x][y];
		return ts.removeTop();
	}
	
	public boolean removeTile(Tile tile){
		TileStack ts;
		for(AddedTile t : tilesAdded){
			if(tile.equals(t.getTile())){
				ts=board[t.getX()][t.getY()];
				return ts.removeTile(tile);
			}	
		}
		return false;
	}
	
	public boolean canAddTile(int x,int y,Tile tile){
		TileStack stack;
		Tile t;
		if((stack=board[x][y])==null)
			return true;
		t=stack.getTop();
		if(t==null)
			return true;
		if(t.getLetter()==tile.getLetter())
			return false;
		return (stack.getTop().getAge()==turn)?
			false:stack.canAddTile();
	}
	
	public void nextTurn(){
		turn++;
	}
	
	public void endTurn(){
		isFirstWord=false;
		tilesAdded=new LinkedList<AddedTile>();
		this.nextTurn();
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public int getTurn() {
		return turn;
	}
}
