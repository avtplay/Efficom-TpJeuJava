package Entite.objet;

import Entite.EntiteVivante.Entite;
import Interface.Combattant;

public class Arme extends Objet {

	private int puissance;
	public Arme(String n, int p, int pui) {
		super(n,p);
		setPuissance(pui);
	}
	
	public int getPuissance() {
		return puissance;
	}
	
	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}

	@Override
	public void utiliser(Combattant e) {
		// TODO Auto-generated method stub
		
	}

}
