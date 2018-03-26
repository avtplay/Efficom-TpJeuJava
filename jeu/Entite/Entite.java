package Entite;

import Mapping.Cellule;

public abstract class Entite {
	Cellule cel;
	
	void setCel(Cellule cel) {
		this.cel = cel;
	}
	
	Cellule getCel() {
		return this.cel;
	}
}
