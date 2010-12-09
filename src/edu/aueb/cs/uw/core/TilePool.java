package edu.aueb.cs.uw.core;

import java.util.HashMap;
import java.util.Map;

public class TilePool {
	
	private Map<Tile,Integer> tilesMap;
	private int numOfTiles;
	
	public  TilePool(){
		this.setNumOfTiles(getNumOfTiles());
		this.initializeMap();
	}
	
	private int getNumOfTiles(){
		int tiles=0;
		for(int t : tilesMap.values())
			tiles+=t;
		return tiles;
	}
	
	public Tile getTile(){
		/*TODO Maybe make map inverted?*/
		return null;
	}

	public boolean hasMoreTiles(){
		return (getNumOfTiles()>0)?true:false;
	}
	
	public void setNumOfTiles(int numOfTiles) {
		this.numOfTiles = numOfTiles;
	}
	
	private void initializeMap(){
		tilesMap=new HashMap<Tile, Integer>();
		tilesMap.put(new Tile('Α'), 11);
		tilesMap.put(new Tile('Β'), 1);
		tilesMap.put(new Tile('Γ'), 2);
		tilesMap.put(new Tile('Δ'), 2);
		tilesMap.put(new Tile('Ε'), 8);
		tilesMap.put(new Tile('Ζ'), 1);
		tilesMap.put(new Tile('Η'), 7);
		tilesMap.put(new Tile('Θ'), 1);
		tilesMap.put(new Tile('Ι'), 8);
		tilesMap.put(new Tile('Κ'), 4);
		tilesMap.put(new Tile('Λ'), 3);
		tilesMap.put(new Tile('Μ'), 3);
		tilesMap.put(new Tile('Ν'), 6);
		tilesMap.put(new Tile('Ξ'), 1);
		tilesMap.put(new Tile('Ο'), 8);
		tilesMap.put(new Tile('Π'), 4);
		tilesMap.put(new Tile('Ρ'), 5);
		tilesMap.put(new Tile('Σ'), 7);
		tilesMap.put(new Tile('Τ'), 8);
		tilesMap.put(new Tile('Υ'), 4);
		tilesMap.put(new Tile('Φ'), 1);
		tilesMap.put(new Tile('Χ'), 1);
		tilesMap.put(new Tile('Ψ'), 1);
		tilesMap.put(new Tile('Ω'), 3);
	}
}
