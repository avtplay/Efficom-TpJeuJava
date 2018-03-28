package Entite.EntiteVivante;

import java.util.ArrayList;

import Entite.objet.Objet;
import Interface.Combattant;
import Interface.Deplacable;
import Interface.Jouable;
import Mapping.Cellule;

public class Personnage extends Entite implements Deplacable, Combattant, Jouable{

	int poidTotal; 
	int poidMax;
	int bourse;
	boolean visionAmeliorer = false;
	
	public boolean asVisionAmeliorer() {
		return visionAmeliorer;
	}

	public int getPoidTotal() {
		return poidTotal;
	}

	public void setPoidTotal(int poidTotal) {
		this.poidTotal = poidTotal;
	}

	public int getPoidMax() {
		return poidMax;
	}

	public void setPoidMax(int poidMax) {
		this.poidMax = poidMax;
	}

	public int getBourse() {
		return bourse;
	}

	public void setBourse(int bourse) {
		this.bourse = bourse;
	}

	public ArrayList<Objet> getInventaire() {
		return inventaire;
	}

	public void setInventaire(ArrayList<Objet> inventaire) {
		this.inventaire = inventaire;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVisionAmeliorer() {
		return visionAmeliorer;
	}

	public void setVisionAmeliorer(boolean visionAmeliorer) {
		this.visionAmeliorer = visionAmeliorer;
	}

	ArrayList<Objet> inventaire; 
	String name; 
	
	public Personnage(Cellule c, String... n) {
		String na = "Joueur 1";
		if (n.length !=0  && n[0]!="") {
			na = n[0];
		}
		if (n.length !=0  && n[1]!="") {
			this.setTag(n[1]);
		}
		this.setEnergie(1000);
		this.setMaxEnergie(1000);
		poidTotal = 0;
		poidMax = 10;
		bourse = 100;
		name = na;
		inventaire= new ArrayList<Objet>();
		ArrayList<Entite> list = c.getListPersonnage();
		list.add(this);
		c.setListPersonnage(list);
		this.setCel(c);
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
			ArrayList<Entite> l = this.getCel().getListPersonnage();
			l.remove(this);
			this.getCel().setListPersonnage(l);
			this.setCel(cel);
			l = cel.getListPersonnage();
			l.add(this);
			cel.setListPersonnage(l); 
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

	@Override
	public void attaquer(Combattant a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setjumelle() {
		// TODO Auto-generated method stub
		visionAmeliorer = true;
	}
	
	
}
