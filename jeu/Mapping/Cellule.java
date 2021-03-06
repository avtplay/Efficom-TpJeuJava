/**
 * 
 */
package Mapping;

import java.util.*;

import Entite.objet.*;
import Entite.EntiteVivante.Entite;
import Entite.EntiteVivante.Monstre;
import Entite.EntiteVivante.Personnage;
import Entite.Vehicule.EnumVehicule;
import Entite.Vehicule.Vehicule;

/**
 * @author alanmenit
 *
 */
public class Cellule {
	private boolean joueurPres = false;
	private boolean finPres = false;
	public boolean isJoueurPres() {
		return joueurPres;
	}

	public void setJoueurPres(boolean joueurPres) {
		this.joueurPres = joueurPres;
	}

	public boolean isFinPres() {
		return finPres;
	}

	public void setFinPres(boolean finPres) {
		this.finPres = finPres;
	}
	private int x;
	private int y;
	public MapZone mapZone = MapZone.FORET;
	private boolean objectifPresent = false;
	private boolean spawnCase = false;
	private ArrayList<Objet> listObjet = new ArrayList<Objet>();
	private ArrayList<Entite> listPersonnage = new ArrayList<Entite>();
	private ArrayList<EnumVehicule> vehicule = new ArrayList<EnumVehicule>();
	
	public ArrayList<EnumVehicule> getVehicule() {
		return vehicule;
	}

	public void setVehicule(ArrayList<EnumVehicule> vehicule) {
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

	public void supprimerObjet(Objet o) {
		 listObjet.remove(o);
	}
	
	public void ajouterObjet(Objet o) {
		listObjet.add(o);
	}
	public Monstre checkMonstre() {
		for(Entite e: this.getListPersonnage()) {
			if(e instanceof Monstre) {
				return (Monstre)e;
			}
		}
		return null;
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
	
	public void supprimerPersonnage(Entite p) {
		listPersonnage.remove(p);
	}
	
	public void ajouterPersonnage(Entite p) {
		listPersonnage.add(p);
	}
	
	public String toString() {
		return "<"+(this.getX()+1)+","+(this.getY()+1)+">";
	}
}
