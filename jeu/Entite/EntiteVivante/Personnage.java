package Entite.EntiteVivante;

import java.util.ArrayList;

import Entite.Deplacable;
import Entite.Entite;
import Entite.Objet;
import Mapping.Cellule;

public class Personnage extends Entite implements Deplacable{

	int poidTotal; 
	int poidMax;
	int bourse;
	
	
	ArrayList<Objet> inventaire; 
	String name; 
	
	public Personnage(String... n) {
		String na = "Joueur 1";
		if (n.length !=0  && n[0]!="") {
			na = n[0];
		}
		this.setEnergie(1000);
		this.setMaxEnergie(1000);
		poidTotal = 0;
		poidMax = 10;
		bourse = 100;
		name = na;
		inventaire= new ArrayList<Objet>();
	}
	
	boolean ajouterALInventaire(Objet o){
		if ( (inventaire.size() >= 5) && (poidTotal + o.getPoid()) > poidMax ) {
			
			return false;
			}
		inventaire.add(o);
		poidTotal += o.getPoid();
		return true;
	}
	
	void retirerALInventaire(int index) {
		Objet o = inventaire.get(index);
		poidTotal -= o.getPoid();
		inventaire.remove(index);
	}
	

	public boolean deplacer(Cellule cel) {
		int energieDeplacement = 50;
		
		if (this.retirerEnergie(energieDeplacement)) {
			this.setCel(cel);
		}else {
			return false;
		}
		return true;
		
	}

	@Override
	public void recevoirDmg(int dmg) {
	}

	@Override
	public void recevoirHeal(int heal) {
		// TODO Auto-generated method stub
		
	}
	
	
}
