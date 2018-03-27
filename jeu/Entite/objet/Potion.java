package Entite.objet;

import Interface.Combattant;
import Interface.Utilisable;

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
