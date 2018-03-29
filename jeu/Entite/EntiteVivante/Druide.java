package Entite.EntiteVivante;

import java.util.Random;

import Interface.Combattant;
import Interface.Deplacable;
import Interface.Utilisable;
import Mapping.Cellule;

public class Druide extends Entite implements Utilisable{

	private int moneyToHeal = 50;
	private int healValue ;
	
	public Druide(Cellule c,String tag, int energie) {
		this.setCel(c);
		this.setTag(tag);
		this.setEnergie(energie);
		this.healValue = 1000;
	}

	@Override
	public void utiliser(Combattant e) {
		Random r = new Random();
		if(r.nextInt(2) == 1) {
			e.recevoirHeal(this.healValue);
			e.modifierEtatSante(EnumEtatSante.BONETAT);
			System.out.println("AMENO Guérison");
		}else {
			System.out.println("Crève moldu");
			e.modifierEtatSante(EnumEtatSante.EMPOISONNE);
		}
			
	}
	
	public boolean paiementAccepte(Personnage p) {
		if(p.getBourse() >= this.getMoneyToHeal()) {
			p.setBourse(p.getBourse() - this.getMoneyToHeal());
			return true;
		}
		System.out.println("Sors devant moi le plouc tu es fauché");
		return false;
	}

	public int getMoneyToHeal() {
		return moneyToHeal;
	}

	public void setHeal(int heal) {
		this.moneyToHeal = heal;
	}

}