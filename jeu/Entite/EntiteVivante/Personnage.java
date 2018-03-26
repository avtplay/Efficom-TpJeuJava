package Entite.EntiteVivante;

import java.util.ArrayList;

import Entite.Entite;
import Entite.Objet;

public class Personnage extends Entite{
	
	int maxEnergie;
	int energie;
	ArrayList<Objet> inventaire; 
	int poidTotal; 
	int poidMax;
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
	
	
	
}
