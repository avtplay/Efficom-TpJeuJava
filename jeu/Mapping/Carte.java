/**
 * 
 */
package Mapping;

import java.util.ArrayList;
import java.util.Random;

import Entite.objet.*;
import Entite.EntiteVivante.Druide;
import Entite.EntiteVivante.Entite;
import Entite.EntiteVivante.Monstre;
import Entite.Vehicule.EnumVehicule;
import Entite.Vehicule.Vehicule;
import Entite.objet.Potion;

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
	protected ArrayList<Entite> entites = new ArrayList<Entite>();
	
	public Carte() {
		this.largeur = 50;
		this.longueur = 50;
		this.nombreZone = 4;
		this.nbSpawn = 15;
		
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
				for(int k = 0; k < 3; k++) {
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
		
		this.generateSpanwCase();
		this.genererMonstreObjetOr();
		
	}
	
	private void generateSpanwCase() {
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
        
        int xMin, xMax, yMin, yMax;
        yMin = cellJ.getY() - vision;
        if(cellJ.getY() - vision < 0)
            yMin = 0;
        
        yMax = cellJ.getY() + vision;
        if(cellJ.getY() + vision > this.longueur-1)
            yMax = this.longueur - 1;
        
        xMin = cellJ.getX() - vision;
        if(cellJ.getX() - vision < 0)
            xMin = 0;
        
        xMax = cellJ.getX() + vision;
        if(cellJ.getX() + vision > this.largeur-1)
            xMax = this.largeur-1;
        
        System.out.println(cellJ.getX()+"/"+cellJ.getY());
        System.out.println(xMin+"/"+xMax+"/"+yMin+"/"+yMax);
        for (int i = xMin; i <= xMax; i++) {
            
            for(int j = yMin; j <= yMax; j++)
            for(int k = 0; k < 20; k++) {
                if(k%20 == 0) {
                    System.out.print("|");
                }else {
                    System.out.print("--");
                }
            }
            System.out.println("|");
            
            for(int j = yMin; j <= yMax; j++) {
                for(int k = 0; k < 20; k++) {
                    if(k%20 == 0) {
                        System.out.print("|");
                    }else {
                        System.out.print(this.cell[i][j].getMapZone().getRepresentation());
                    }
                }
            }
            System.out.println("|");
            
            for(int j = yMin; j <= yMax; j++) {
                if(this.cell[i][j].getListObjet().size() == 0)
                    for(int k = 0; k < 20; k++) {
                        if(k%20 == 0) {
                            System.out.print("|");
                        }else {
                            System.out.print(this.cell[i][j].getMapZone().getRepresentation());
                        }
                    }
                else {
                    System.out.print("|");
                    String str = "";
                    for(int tmp = 0; tmp < this.cell[i][j].getListObjet().size(); tmp++) {
                        str += this.cell[i][j].getListObjet().get(tmp).getNom()+" ";
                        //System.out.print(this.cell[i][j].getListObjet().get(tmp).getNom()+" ");
                    }
                    int taille = 40 - (str.length()+1);
                    for(int k = 0; k < taille-1; k++) {
                        str += this.cell[i][j].getMapZone().getRepresentation().charAt(0);
                    }
                    System.out.print(str);
                }
            }
            System.out.println("|");
            
            for(int j = yMin; j <= yMax; j++) {
                if(this.cell[i][j].getListPersonnage().size() == 0)
                    for(int k = 0; k < 20; k++) {
                        if(k%20 == 0) {
                            System.out.print("|");
                        }else {
                            System.out.print(this.cell[i][j].getMapZone().getRepresentation());
                        }
                    }
                else {
                    System.out.print("|");
                    String str = "";
                    for(int tmp = 0; tmp < this.cell[i][j].getListPersonnage().size(); tmp++) {
                        str += this.cell[i][j].getListPersonnage().get(tmp).getTag();
                        //System.out.print(this.cell[i][j].getListPersonnage().get(tmp).getTag()+"\t");
                    }
                    int taille = 40 - (str.length()+1);
                    for(int k = 0; k < taille-1; k++) {
                        str += this.cell[i][j].getMapZone().getRepresentation().charAt(0);
                    }
                    System.out.print(str);
                }
            }
            System.out.println("|");
            
            for(int j = yMin; j <= yMax; j++) {
                if(this.cell[i][j].getVehicule().size() == 0)
                    for(int k = 0; k < 20; k++) {
                        if(k%20 == 0) {
                            System.out.print("|");
                        }else {
                            System.out.print(this.cell[i][j].getMapZone().getRepresentation());
                        }
                    }
                else {
                    System.out.print("|");
                    String str = "";
                    for(int tmp = 0; tmp < this.cell[i][j].getVehicule().size(); tmp++) {
                        str += this.cell[i][j].getVehicule().get(tmp).getNom()+" ";
                        //System.out.print(this.cell[i][j].getVehicule().get(tmp).getNom()+"\t");
                    }
                    int taille = 40 - (str.length()+1);
                    for(int k = 0; k < taille-1; k++) {
                        str += this.cell[i][j].getMapZone().getRepresentation().charAt(0);
                    }
                    System.out.print(str);
                }
            }
            System.out.println("|");
            
            for(int j = yMin; j <= yMax; j++) {
                for(int k = 0; k < 20; k++) {
                    if(k%20 == 0) {
                        System.out.print("|");
                    }else {
                        System.out.print(this.cell[i][j].getMapZone().getRepresentation());
                    }
                }
            }
            System.out.println("|");
            
            for(int j = yMin; j <= yMax; j++)
                for(int k = 0; k < 20; k++) {
                    if(k%20 == 0) {
                        System.out.print("|");
                    }else {
                        System.out.print("--");
                    }
                }
            System.out.println("|");
        }
    }
	
	public int getNbSpawn() {
		return nbSpawn;
	}

	public void setNbSpawn(int nbSpawn) {
		this.nbSpawn = nbSpawn;
	}

	public Coordinate[] getSpanwCoordinate() {
		return spanwCoordinate;
	}

	public void setSpanwCoordinate(Coordinate[] spanwCoordinate) {
		this.spanwCoordinate = spanwCoordinate;
	}
	
	private void genererMonstreObjetOr() {
		for(int i = 0; i < this.nbSpawn; i++) {
			Random r = new Random();
			switch(r.nextInt(7)) {
				case 0:
					this.ajouterMonstre(this.spanwCoordinate[i]);
					break;
					
				case 1:
					this.ajouterDruide(this.spanwCoordinate[i]);
					break;
					
				case 2:
					this.ajouterOr(this.spanwCoordinate[i]);
					break;
					
				case 3:
					this.ajouterPotion(this.spanwCoordinate[i]);
					break;
					
				case 4:
					this.ajouterArme(this.spanwCoordinate[i]);
					break;
					
				case 5:
					this.ajouterVehicule(this.spanwCoordinate[i]);
					break;
					
				default:
					this.ajouterJumelle(this.spanwCoordinate[i]);
					break;
			}
		}
	}
	
	private void ajouterMonstre(Coordinate coord) {
		Monstre e = new Monstre(this.cell[coord.getX()][coord.getY()],150,"Mon");
		this.cell[coord.getX()][coord.getY()].getListPersonnage().add(e);
		entites.add(e);
	}
	
	public ArrayList<Entite> getEntites() {
		return entites;
	}

	public void setEntites(ArrayList<Entite> entites) {
		this.entites = entites;
	}

	private void ajouterDruide(Coordinate coord) {
		this.cell[coord.getX()][coord.getY()].getListPersonnage().add(new Druide(this.cell[coord.getX()][coord.getY()],"DR",1));
	}
	
	private void ajouterVehicule(Coordinate coord) {
		this.cell[coord.getX()][coord.getY()].getVehicule().add(EnumVehicule.getVehiculeByMapZone(this.cell[coord.getX()][coord.getY()].mapZone));
	}
	
	private void ajouterJumelle(Coordinate coord) {
		this.cell[coord.getX()][coord.getY()].getListObjet().add(new Jumelle("Jumelle", 1));
	}
	
	private void ajouterArme(Coordinate coord) {
		Random r = new Random();
		switch(r.nextInt(4)%2) {
		case 0:
			this.cell[coord.getX()][coord.getY()].getListObjet().add(new Arme("Ak47", 2, 5));
			break;
			
		default:
			this.cell[coord.getX()][coord.getY()].getListObjet().add(new Arme("Epée", 3, 6));
			break;
			
		}
	}
	
	private void ajouterPotion(Coordinate coord) {
		Random r = new Random();
		switch(r.nextInt(4)) {
		case 0:
			this.cell[coord.getX()][coord.getY()].getListObjet().add(new Potion("Simple Potion", 2, 50));
			break;
			
		case 1:
			this.cell[coord.getX()][coord.getY()].getListObjet().add(new Potion("Super Potion", 3, 100));
			break;
			
		case 2:
			this.cell[coord.getX()][coord.getY()].getListObjet().add(new Potion("Low Potion", 1, 25));
			break;
			
		default:
			this.cell[coord.getX()][coord.getY()].getListObjet().add(new Potion("Great Potion", 2, 75));
			break;
		}
		
	}
	
	private void ajouterOr(Coordinate coord) {
		Random or = new Random();
		this.cell[coord.getX()][coord.getY()].setOr(or.nextInt(50));;
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
	
	public Cellule getCelluleViaCoordonne(Coordinate c) {
		return this.cell[c.getX()][c.getY()];
	}
	
	public ArrayList<Cellule> getCellulevoisine(Cellule c) {
		ArrayList<Cellule> list = new ArrayList<Cellule>();
		for (int i = c.getX() - 1; i <= c.getX() + 1; i++) {
			for (int j = c.getY() - 1; j <= c.getY() + 1; j++) {
				if (i >= 0 && j >= 0 && i < this.largeur && j <= this.longueur && !(i == c.getX() && j == c.getY())) {
					list.add(this.cell[i][j]);
				}
			}
		}
		return list;
	}
}