package Entite.EntiteVivante;

import Interface.Combattant;
import Interface.Deplacable;
import Mapping.Cellule;

public class Monstre extends Entite implements Deplacable, Combattant{
	
	int puissance;
	
	public Monstre(Cellule c, int puissance, String tag) {
		this.setCel(c);
		this.setTag(tag);
		this.puissance = puissance;
	}
	@Override
	public void recevoirDmg(int dmg) {
		this.retirerEnergie(dmg);
	}

	@Override
	public void recevoirHeal(int heal) {
		this.ajouterEnergie(heal);	
	}

	@Override
	public boolean deplacer(Cellule c) {
		this.setCel(c);
		return true;
	}

	@Override
	public void attaquer(Combattant a) {
		a.recevoirDmg(puissance);
	}

}
