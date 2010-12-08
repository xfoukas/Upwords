package edu.aueb.cs.uw;

public class Dimensions {
	
	private int boardheight, scoreHeight, trayHeight;
	private int padding,top;
	private int totalWidth, totalHeight;
	private int cellSize;
	
	public Dimensions(){
		boardheight=scoreHeight=
		trayHeight=padding=top=
		totalHeight=totalWidth=
		cellSize=0;
	}

	public void setBoardheight(int boardheight) {
		this.boardheight = boardheight;
	}

	public int getBoardheight() {
		return boardheight;
	}

	public void setScoreHeight(int scoreHeight) {
		this.scoreHeight = scoreHeight;
	}

	public int getScoreHeight() {
		return scoreHeight;
	}

	public void setTrayHeight(int trayHeight) {
		this.trayHeight = trayHeight;
	}

	public int getTrayHeight() {
		return trayHeight;
	}

	public void setPadding(int padding) {
		this.padding = padding;
	}

	public int getPadding() {
		return padding;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getTop() {
		return top;
	}

	public void setTotalWidth(int totalWidth) {
		this.totalWidth = totalWidth;
	}

	public int getTotalWidth() {
		return totalWidth;
	}

	public void setTotalHeight(int totalHeight) {
		this.totalHeight = totalHeight;
	}

	public int getTotalHeight() {
		return totalHeight;
	}

	public void setCellSize(int cellSize) {
		this.cellSize = cellSize;
	}

	public int getCellSize() {
		return cellSize;
	}

}
