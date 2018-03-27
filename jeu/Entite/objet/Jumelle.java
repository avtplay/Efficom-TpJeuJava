package Entite.objet;

import Entite.Jouable;
import Entite.Combattant;

public class Jumelle extends Objet{

	public Jumelle(String n, int p) {
		super(n, p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void utiliser(Combattant e) {
		if (e instanceof Jouable) {
			Jouable j = (Jouable) e;
			j.setjumelle();
		}
	}

}
