/**
 * 
 */
package Mapping;

import java.util.*;

import Entite.Entite;
import Entite.Objet;
import Entite.EntiteVivante.Personnage;

/**
 * @author alanmenit
 *
 */
public class Cellule {
	private int x;
	private int y;
	public MapZone mapZone = null;
	private boolean objectifPresent = false;
	private boolean spawnCase = false;
	private ArrayList<Objet> listObjet = new ArrayList<Objet>();
	private ArrayList<Entite> listPersonnage = new ArrayList<Entite>();
	
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
	
	public boolean isObjectifPresent() {
		return objectifPresent;
	}

	public void setObjectifPresent(boolean objectifPresent) {
		this.objectifPresent = objectifPresent;
	}

	public boolean isSpawnCase() {
		return spawnCase;
	}

	public void setSpawnCase(boolean spawnCase) {
		this.spawnCase = spawnCase;
	}

	public ArrayList<Objet> getListObjet() {
		return listObjet;
	}

	public void setListObjet(ArrayList<Objet> listObjet) {
		this.listObjet = listObjet;
	}

	public ArrayList<Entite> getListPersonnage() {
		return listPersonnage;
	}

	public void setListPersonnage(ArrayList<Entite> listPersonnage) {
		this.listPersonnage = listPersonnage;
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