package Mapping;

import java.util.*;

/**
 * @author alanmenit
 *
 */
public class Zone {

	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private MapZone zoneId;
	
	public Zone(MapZone idZone, int tailleX, int tailleY) {
		Random r = new Random();
		this.startX = r.nextInt(tailleX - 0);
		do {
		this.endX = this.startX + r.nextInt(tailleX - this.startX);
		}while(this.endX == this.startX);
		
		this.startY = r.nextInt(tailleY);
		do {
			this.endY =this.startY + r.nextInt(tailleY - this.startY);
		}while(this.endY == this.startY);
		this.zoneId = idZone;
	}
	
	public boolean seChevauche(Zone z) {
		
		return (this.startX < z.startX && this.endX > z.startX) || (this.startY < z.startY && this.endY > z.startY)
				|| (this.startX < z.endX && this.endX > z.endX) || (this.startY < z.endY && this.endY > z.endY);

	}
	
	public boolean containsCellule(int x, int y) {
		return (this.startX < x && this.endX > x) && (this.startY < y && this.endY > y);
	}
	
	public Cellule[][] attribuateCell(Cellule[][]cell, int tailleX, int tailleY) {
		for(int i = 0; i < tailleX; i++) {
			for(int j = 0; j < tailleY; j++) {
				if(this.containsCellule(i, j))
					cell[i][j].setMapZone(MapZone.MER);
			}
		}
		
		return cell;
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

	public MapZone getZoneId() {
		return zoneId;
	}

	public void setZoneId(MapZone zoneId) {
		this.zoneId = zoneId;
	}
}