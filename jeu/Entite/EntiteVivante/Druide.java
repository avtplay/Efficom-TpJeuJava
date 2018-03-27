package Entite.EntiteVivante;

import java.util.Random;

import Interface.Combattant;
import Interface.Deplacable;
import Interface.Utilisable;
import Mapping.Cellule;

public class Druide extends Entite implements Utilisable{

	int heal = 250;
	
	public Druide(Cellule c,String tag, int energie) {
		this.setCel(c);
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
