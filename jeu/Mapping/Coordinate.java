package Mapping;

import java.util.Random;

public class Coordinate {

	private int x;
	private int y;
	
	public Coordinate() {
		
	}
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Coordinate(int[] tab) {
		this.x = tab[0];
		this.y = tab[1];
	}
	
	public void generateCoordinate(int tailleX, int tailleY) {
		Random r = new Random();
		
		this.x = r.nextInt(tailleX);
		this.y = r.nextInt(tailleY);
	}

	public boolean equalCoordinate(int x, int y){
		return this.x == x && this.y == y;
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