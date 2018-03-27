package Entite.Vehicule;

import Entite.Deplacable;
import Entite.Entite;
import Mapping.Cellule;

public abstract class Vehicule extends Entite implements Deplacable {
	
	String nom;
	
	public Vehicule(int energie, String nom, String tag, Cellule c) {
		this.setEnergie(energie);
		this.nom = nom;
		this.setTag(tag);
		this.setCel(c);
	}
	
	public boolean deplacer(Cellule c, int cout) {
		return this.retirerEnergie(cout);
	}
}
