
package Entite.objet;

import Interface.Combattant;
import Interface.Utilisable;

public abstract class Objet implements Utilisable  {
	
	private String nom;
	private int poid;
	
	public Objet(String n, int p) {
		nom = n;
		poid = p;
	}

	public int getPoid() {
		return poid;
	}

	public void setPoid(int poid) {
		this.poid = poid;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public abstract void utiliser(Combattant e);
}