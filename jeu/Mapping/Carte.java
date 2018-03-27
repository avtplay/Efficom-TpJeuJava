
package Mapping;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.color.*;

/**
 * @author alanmenit
 *
 */
public class Carte {
	protected int largeur;
	protected int longueur;
	protected int nombreZone;
	protected ArrayList<Zone> listsZoneMap = new ArrayList<Zone>();
	protected Cellule[][] cell;
	protected int nbSpawn;
	protected Coordinate[] spanwCoordinate;
	
	
	public Carte() {
		this.largeur = 50;
		this.longueur = 50;
		this.nombreZone = 4;
		this.nbSpawn = 3;
		
		this.cell = new Cellule[this.largeur][this.longueur];
		
		this.listsZoneMap.add(new Zone(MapZone.FORET, this.largeur, this.longueur));
		
		for(int i = 1; i < this.nombreZone-1; i++) {
			boolean resInd = false;
			Zone tmp;
			do {
				tmp = new Zone(MapZone.getById(i), this.largeur, this.longueur);
				for(int j = 0; j < this.listsZoneMap.size(); j++) {
					resInd = this.listsZoneMap.get(j).seChevauche(tmp);
				}
			}while(resInd);
			this.listsZoneMap.add(tmp);
		}
		
		for(int i = 0; i < this.largeur; i++) {
			boolean ok = false;
			for(int j = 0; j < this.longueur; j++) {
				ok = false;
				for(int k = 0; k < this.listsZoneMap.size(); k++) {
					if(this.listsZoneMap.get(k).containsCellule(i, j)) {
						this.cell[i][j] = new Cellule(i, j, MapZone.getById(k));
						ok = true;

					}
				}
				if(!ok)
					this.cell[i][j] = new Cellule(i, j, MapZone.getById(this.nombreZone-1));
			}
		}
		
		this.spanwCoordinate = new Coordinate[this.nbSpawn];
		for(int i = 0; i < this.nbSpawn; i++) {
			this.spanwCoordinate[i] = new Coordinate();
			this.spanwCoordinate[i].generateCoordinate(this.largeur, this.longueur);
		}
		
		int cpt = 0;
		while(cpt < this.nbSpawn) {
		for(int i = 0; i < this.largeur; i++) {
			for(int j = 0; j < this.longueur; j++) {
				this.cell[i][j].setSpawnCase(this.spanwCoordinate[cpt].equalCoordinate(i, j));
			}
		}
		cpt++;
		}
	
	}
	
	public void printCarte() {
		for(int i = 0; i < this.largeur; i++) {
			System.out.print("\t|");
			
			for(int j = 0; j < this.longueur; j++) {
				System.out.print("---|");
			}
			
			System.out.println();
			
			System.out.print((i+1)+"\t|");
			
			for(int j = 0; j < this.longueur; j++) {
				System.out.print(this.cell[i][j].mapZone.getTag()+"|");
			}
			
			System.out.println();
		}
	}
	
	public void displayCellGamer(Cellule cellJ, boolean jumelle) {
		int vision = 1;
		
		if(jumelle) {
			vision = 2;
		}
		
		for(int i = cellJ.getX()-vision; i <= cellJ.getX()+vision; i++) {
			for(int j = cellJ.getY()-vision; j <= cellJ.getY()+vision; j++) {
				if(i >= 0 && j >= 0 && i < this.largeur && j <= this.longueur) {
					this.cell[i][j].displayCellule();
				}
			}
		}
	}
	
	public void ajouterMonstre() {
		
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}

	public int getNombreZone() {
		return nombreZone;
	}

	public void setNombreZone(int nombreZone) {
		this.nombreZone = nombreZone;
	}

	public ArrayList<Zone> getListsZoneMap() {
		return listsZoneMap;
	}

	public void setListsZoneMap(ArrayList<Zone> listsZoneMap) {
		this.listsZoneMap = listsZoneMap;
	}

	public Cellule[][] getCell() {
		return cell;
	}

	public void setCell(Cellule[][] cell) {
		this.cell = cell;
	}
}