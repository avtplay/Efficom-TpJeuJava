
package Mapping;


import java.util.*;

/**
 * @author alanmenit
 *
 */
public class Cellule {
	private int x;
	private int y;
	public MapZone mapZone = null;
	//private ArrayList<Item> = new List<Item>;
	//private ArrayList<Personnage> = new List<Personnage>();
	
	public MapZone getMapZone() {
		return mapZone;
	}

	public void setMapZone(MapZone mapZone) {
		this.mapZone = mapZone;
	}

	public Cellule() {
		
	}
	
	public Cellule(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Cellule(int x, int y, MapZone mapZone) {
		this.x = x;
		this.y = y;
		this.mapZone = mapZone;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}