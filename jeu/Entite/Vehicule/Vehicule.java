package Entite.Vehicule;

import Entite.EntiteVivante.Entite;
import Interface.Deplacable;
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
