package edu.aueb.cs.uw.core;

import java.util.LinkedList;

public class Board {
	
	public static final int BOARD_SIZE=10;
	private static final int COL_ORIENT=1;
	private static final int ROW_ORIENT=0;
	
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
	
	/**
	 * If the placement of tiles is right
	 * calculate the score of the player
	 * 
	 * @return The score of the current player
	 */
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
		int orientation;
		int size=tilesAdded.size();
		AddedTile t;
		if(size==1){
			score+=getScoreHorizontal(tilesAdded.getFirst().getX(),
					tilesAdded.getFirst().getY());
			score+=getScoreVertical(tilesAdded.getFirst().getX(),
					tilesAdded.getFirst().getY());
		}
		t=tilesAdded.getFirst();
		orientation=findOrientation();
		switch (orientation) {
		case COL_ORIENT:
			score+=getScoreVertical(t.getX(), t.getY());
			for(AddedTile tile : tilesAdded)
				score+=getScoreHorizontal(tile.getX(), tile.getY());
			break;
		case ROW_ORIENT:
			score+=getScoreHorizontal(t.getX(), t.getY());
			for(AddedTile tile : tilesAdded)
				score+=getScoreVertical(tile.getX(), tile.getY());
			break;
		default:
			break;
		}
		if(allLettersUsed())
			score+=10;
		for(AddedTile tile : tilesAdded){
			if(TilePool.BONUS_LETTERS.indexOf(tile.getTile().getLetter())!=-1)
					score+=2;
		}
		return score;
	}
	
	private int findOrientation(){
		AddedTile t1,t2;
		if(tilesAdded.size()<2)
			return -1;
		else {
			t1=tilesAdded.get(0);
			t2=tilesAdded.get(1);
			if(t1.getX()==t2.getX())
				return ROW_ORIENT;
			else if(t1.getY()==t2.getY())
				return COL_ORIENT;
			else return -1;
		}
	}
	
	private int getScoreHorizontal(int x,int y){
		int score=0;
		int firstOfRow=y;
		boolean isFirstLevel=true;
		while(firstOfRow-1>=0){
			if(!board[x][firstOfRow-1].isEmpty())
				firstOfRow--;
			else break;
		}
		int i=firstOfRow;
		do {
			if(!board[x][i].hasSingleTile()){
				isFirstLevel=false;
				score+=board[x][i].getSize();
			}
			else
				score++;
			i++;
		} while(board[x][i].isEmpty());
		if(isFirstLevel)
			score*=2;
		return score;
	}
	
	private int getScoreVertical(int x, int y){
		int score=0;
		int firstOfCol=x;
		boolean isFirstLevel=true;
		while(firstOfCol-1>=0){
			if(!board[firstOfCol-1][y].isEmpty())
				firstOfCol--;
			else break;
		}
		int i=firstOfCol;
		do {
			if(!board[i][y].hasSingleTile()){
				isFirstLevel=false;
				score+=board[i][y].getSize();
			}
			else
				score++;
			i++;
		} while(board[i][y].isEmpty());
		if(isFirstLevel)
			score*=2;
		return score;
	}
	
	private boolean allLettersUsed(){
		return (tilesAdded.size()==Tray.TRAY_SIZE)?
				true:false;
	}
	
	public boolean isValidPlacement(){
		int size;
		boolean hasCentralCell=false;
		size=tilesAdded.size();
		if(size==0)
			return false;
		if(isFirstWord){
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
		int numTiles=tilesAdded.size();
		int tilesLeftToCheck=numTiles-1;
		boolean continuousTiles=true;
		int tilesAddedFound=0;
		/*Check row*/
		for(int i=0;i<BOARD_SIZE;i++){
			if(i==tile.getY()) continue; //Do not look at this tile
			//Has a tile?
			if(board[tile.getX()][i].isEmpty()){
				continuousTiles=false;
				continue;
			}
			//if added on this round
			if(board[tile.getX()][i].getTop().getAge()==turn) {
				if(!continuousTiles)
					tilesAddedFound=0;
				tilesAddedFound++;
			}
			continuousTiles=true;
		}
		if(tilesAddedFound==tilesLeftToCheck)
			return true;
		tilesAddedFound=0;
		continuousTiles=true;
		//Do the same for column
		for(int i=0;i<BOARD_SIZE;i++){
			if(i==tile.getX()) continue; //Do not look at this tile
			//Has a tile?
			if(board[i][tile.getY()].isEmpty()){
				continuousTiles=false;
				continue;
			}
			//if added on this round
			if(board[i][tile.getY()].getTop().getAge()==turn) {
				if(!continuousTiles)
					tilesAddedFound=0;
				tilesAddedFound++;
			}
			continuousTiles=true;
		}
		if(tilesAddedFound==tilesLeftToCheck)
			return true;
		return false;
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
	
	public boolean haveMadeChanges(){
		return (tilesAdded.size()>0)?true:false;
	}
	
}
