package Entite.objet;

import Interface.Combattant;

public class Antidote extends Potion{

	public Antidote() {
		super("Antidote", 3, 50);
	}
	
	@Override
	public void utiliser(Combattant e) {
		e.recevoirHeal(heal);
		e.soignerEtat(e);
	}
}