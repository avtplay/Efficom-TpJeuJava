/**
 * 
 */
package Mapping;

import java.util.*;

import Entite.objet.*;
import Entite.EntiteVivante.Entite;
import Entite.EntiteVivante.Personnage;
import Entite.Vehicule.Vehicule;

/**
 * @author alanmenit
 *
 */
public class Cellule {
	private int x;
	private int y;
	public MapZone mapZone = MapZone.FORET;
	private boolean objectifPresent = false;
	private boolean spawnCase = false;
	private ArrayList<Objet> listObjet = new ArrayList<Objet>();
	private ArrayList<Entite> listPersonnage = new ArrayList<Entite>();
	private ArrayList<Vehicule> vehicule = new ArrayList<Vehicule>();
	public ArrayList<Vehicule> getVehicule() {
		return vehicule;
	}

	public void setVehicule(ArrayList<Vehicule> vehicule) {
		this.vehicule = vehicule;
	}
	private int or = 0;
	
	public int getOr() {
		return or;
	}

	public void setOr(int or) {
		this.or = or;
	}

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
	
	public void displayCellule() {
		System.out.print("|");
		for(int i = 0; i < 20; i++)
			System.out.print("--");
		System.out.println("|");
		
		System.out.print("|"+this.or+"");
		for(int i = 0; i < 19; i++)
			System.out.print(this.mapZone.getRepresentation());
		System.out.println("|");
		
		System.out.print("|");
		for(int i = 0; i < 20; i++)
			System.out.print(this.mapZone.getRepresentation());
		System.out.println("|");
		
		System.out.print("|");
		for(int i =  0; i < this.listObjet.size(); i++)
			System.out.print(this.listObjet.get(i).getNom()+"\t");
		System.out.println("|");
		
		System.out.print("|");
		for(int i =  0; i < this.listPersonnage.size(); i++)
			System.out.print(this.listPersonnage.get(i).getTag()+ "\t");
		System.out.println("|");
		
		System.out.print("|");
		for(int i = 0; i < 20; i++)
			System.out.print(this.mapZone.getRepresentation());
		System.out.println("|");
		
		System.out.print("|");
		for(int i = 0; i < 20; i++)
			System.out.print(this.mapZone.getRepresentation());
		System.out.println("|");
		
		System.out.print("|");
		for(int i = 0; i < 20; i++)
			System.out.print("--");
		System.out.println("|");
		
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
	
	public String toString() {
		return "coordonne de la cellule <"+this.getX()+","+this.getY()+">";
	}
}