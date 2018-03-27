package Entite.EntiteVivante;

import java.util.Random;

import Entite.Deplacable;
import Entite.Entite;
import Entite.Utilisable;
import Entite.Combattant;

public class Druide extends Entite implements Utilisable{

	int heal = 250;
	
	public Druide(String tag, int energie) {
		this.setTag(tag);
		this.setEnergie(energie);
	}

	@Override
	public void utiliser(Combattant e) {
		Random r = new Random();
		if(r.nextInt(10) >= 6) {
			e.recevoirHeal(50);
		}
	}

}
