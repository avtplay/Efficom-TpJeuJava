package Mapping;


import java.util.ArrayList;

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
	
	
	public Carte() {
		this.largeur = 50;
		this.longueur = 50;
		this.nombreZone = 4;
		
		this.cell = new Cellule[this.largeur][this.longueur];
		//for(int i = 0; i < this.largeur; i++)
		//	for(int j = 0; j < this.longueur; j++)
			
		
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
	
	}
	public void printCarte() {
        for(int i = 0; i < this.largeur; i++) {
            System.out.print("Line "+i+":\t");
            for(int j = 0; j < this.longueur; j++) {
                System.out.print(this.cell[i][j]+"|");
            }
            System.out.println();
        }
    }
}