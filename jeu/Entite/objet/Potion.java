package Entite.objet;

import Entite.Utilisable;
import Entite.Combattant;

public class Potion extends Objet{

	int heal;
	
	public Potion(String n, int p, int h) {
		super(n, p);
		this.heal = h;
	}

	@Override
	public void utiliser(Combattant e) {
		e.recevoirHeal(heal);
	}

}
