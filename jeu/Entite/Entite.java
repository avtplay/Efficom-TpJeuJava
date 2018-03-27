package Entite;

import Mapping.Cellule;

public abstract class Entite {
	private Cellule cel;
	
	public void setCel(Cellule cel) {
		this.cel = cel;
	}
	
	public Cellule getCel() {
		return this.cel;
	}
	
	public abstract void recevoirDmg(int dmg);
}
