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
	protected ArrayList<Objet> objets = new ArrayList<Objet>();

	public Carte() {
		this.largeur = 50;
		this.longueur = 50;
		this.nombreZone = 4;
		this.nbSpawn = 15;

		this.cell = new Cellule[this.largeur][this.longueur];

		this.listsZoneMap.add(new Zone(MapZone.FORET, this.largeur, this.longueur));

		for (int i = 1; i < this.nombreZone - 1; i++) {
			boolean resInd = false;
			Zone tmp;
			do {
				tmp = new Zone(MapZone.getById(i), this.largeur, this.longueur);
				for (int j = 0; j < this.listsZoneMap.size(); j++) {
					resInd = this.listsZoneMap.get(j).seChevauche(tmp);
				}
			} while (resInd);
			this.listsZoneMap.add(tmp);
		}

		for (int i = 0; i < this.largeur; i++) {
			boolean ok = false;
			for (int j = 0; j < this.longueur; j++) {
				ok = false;
				for (int k = 0; k < this.listsZoneMap.size(); k++) {
					if (this.listsZoneMap.get(k).containsCellule(i, j)) {
						this.cell[i][j] = new Cellule(i, j, MapZone.getById(k));
						ok = true;

					}
				}
				if (!ok)
					this.cell[i][j] = new Cellule(i, j, MapZone.getById(this.nombreZone - 1));
			}
		}

		this.spanwCoordinate = new Coordinate[this.nbSpawn];
		for (int i = 0; i < this.nbSpawn; i++) {
			this.spanwCoordinate[i] = new Coordinate();
			this.spanwCoordinate[i].generateCoordinate(this.largeur, this.longueur);
		}

		this.generateSpanwCase();
		this.genererMonstreObjetOr();

	}

	public ArrayList<Entite> getEntites() {
		return entites;
	}

	public void setEntites(ArrayList<Entite> entites) {
		this.entites = entites;
	}

	public ArrayList<Objet> getObjets() {
		return objets;
	}

	public void setObjets(ArrayList<Objet> objets) {
		this.objets = objets;
	}

	private void generateSpanwCase() {
		int cpt = 0;
		while (cpt < this.nbSpawn) {
			for (int i = 0; i < this.largeur; i++) {
				for (int j = 0; j < this.longueur; j++) {
					this.cell[i][j].setSpawnCase(this.spanwCoordinate[cpt].equalCoordinate(i, j));
				}
			}
			cpt++;
		}
	}

	public void printCarte() {
		for (int i = 0; i < this.largeur; i++) {
			System.out.print("\t|");

			for (int j = 0; j < this.longueur; j++) {
				System.out.print("---|");
			}

			System.out.println();

			System.out.print((i + 1) + "\t|");

			for (int j = 0; j < this.longueur; j++) {
				System.out.print(this.cell[i][j].mapZone.getTag() + "|");
			}

			System.out.println();
		}
	}

	public void displayCellGamer(Cellule cellJ, boolean jumelle) {
		int vision = 1;

		if (jumelle) {
			vision = 2;
		}

		for (int i = cellJ.getX() - vision; i <= cellJ.getX() + vision; i++) {
			for (int j = cellJ.getY() - vision; j <= cellJ.getY() + vision; j++) {
				if (i >= 0 && j >= 0 && i < this.largeur && j <= this.longueur) {
					this.cell[i][j].displayCellule();
				}
			}
		}
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
	
	public Cellule getCelluleViaCoordonne(Coordinate c) {
		return this.cell[c.getX()][c.getY()];
	}

	private void genererMonstreObjetOr() {
		for (int i = 0; i < this.nbSpawn; i++) {
			Random r = new Random();
			switch (r.nextInt(6)) {
			case 0:
				this.ajouterMonstre(this.spanwCoordinate[i], 200, "Monstre");
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

			default:
				this.ajouterJumelle(this.spanwCoordinate[i]);
				break;
			}
		}
	}

	private Monstre ajouterMonstre(Coordinate coord, int puissance, String nomMonstre) {

		if (this.cell[coord.getX()][coord.getY()] != null) {
			Monstre m = new Monstre(this.cell[coord.getX()][coord.getY()], puissance, nomMonstre);
			entites.add(m);
			this.cell[coord.getX()][coord.getY()].getListPersonnage().add(m);
			return m;
		}
		return null;
	}

	private void ajouterDruide(Coordinate coord) {

		if (this.cell[coord.getX()][coord.getY()] != null) {
			Druide d = new Druide(this.cell[coord.getX()][coord.getY()], "Dr", 1);
			this.cell[coord.getX()][coord.getY()].getListPersonnage().add(d);
		}
	}

	private void ajouterJumelle(Coordinate coord) {
		this.cell[coord.getX()][coord.getY()].getListObjet().add(new Jumelle("Jumelle", 1));
	}

	private void ajouterArme(Coordinate coord) {
		Random r = new Random();
		switch (r.nextInt(4) % 2) {
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
		switch (r.nextInt(4)) {
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
		this.cell[coord.getX()][coord.getY()].setOr(or.nextInt(50));
		;
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