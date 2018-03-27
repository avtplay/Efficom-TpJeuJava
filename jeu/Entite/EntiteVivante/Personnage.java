package Entite.EntiteVivante;

import java.util.ArrayList;

import Entite.Entite;
import Entite.Objet;
import Mapping.Cellule;

public class Personnage extends Entite{

	int poidTotal; 
	int poidMax;
	int maxEnergie;
	int energie;
	int bourse;
	
	
	ArrayList<Objet> inventaire; 
	String name; 
	
	public Personnage(String... n) {
		String na = "Joueur 1";
		if (n.length !=0  && n[0]!="") {
			na = n[0];
		}
		energie = 1000;
		maxEnergie = energie;
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
	
	boolean  retirerEnergie (int minus) {
		energie = energie-minus;
		return energie <= 0 ;
	}
	
	boolean ajouterEnergie (int plus) {
		if(energie == maxEnergie )
			return false;
		energie = energie > maxEnergie ? maxEnergie : energie+plus;
		return true;
	}
	
	void deplacer(Cellule cel) {
		this.setCel(cel);
	}

	@Override
	public void recevoirDmg(int dmg) {
	}
	
	
}
