package Entite.EntiteVivante;

public enum Deplacement {
	DROITE(1, 0),
	GAUCHE(-1, 0),
	HAUT(0, -1),
	BAS(0, 1),
	DROITEHAUT(1, -1),
	GAUCHEHAUT(-1, -1),
	DROITEBAS(1, 1),
	GAUCHEBAS(-1, 1);
	
	private int deplacementX;
	private int deplacementY;
	
	Deplacement(int x, int y){
		this.deplacementX = x;
		this.deplacementY = y;
	}

	public int getDeplacementX() {
		return deplacementX;
	}
	
	public String getStringDeplacement(Deplacement d,boolean direction) {
		if(d.getDeplacementX() == 1 && d.getDeplacementY() == 0)
			return "DROITE"+ (direction? " 4":"");
		else if(d.getDeplacementX() == -1 && d.getDeplacementY() == 0)
			return "GAUCHE"+ (direction? " 6":"");
		else if(d.getDeplacementX()== 0 && d.getDeplacementY() == -1)
			return "HAUT"+ (direction? " 8":"");
		else if(d.getDeplacementX() == 0 && d.getDeplacementY() == 1)
			return "BAS"+ (direction? " 2":"");
		else if(d.getDeplacementX() == 1 && d.getDeplacementY() == 1)
			return "DROITEBAS"+ (direction? " 3":"");
		else if(d.getDeplacementX() == -1 && d.getDeplacementY() == 1)
			return "GAUCHEBAS"+ (direction? " 1":"");
		else if(d.getDeplacementX() == 1 && d.getDeplacementY() == -1)
			return "DROITEHAUT"+ (direction? " 9":"");
		else if(d.getDeplacementX() == -1 && d.getDeplacementY() == -1)
			return "DROITEHAUT"+ (direction? " 7":"");
		return "";
	}

	public int getDirection(Deplacement d) {
		if(d.getDeplacementX() == 1 && d.getDeplacementY() == 0)
			return 4;
		else if(d.getDeplacementX() == -1 && d.getDeplacementY() == 0)
			return 6;
		else if(d.getDeplacementX()== 0 && d.getDeplacementY() == -1)
			return 8;
		else if(d.getDeplacementX() == 0 && d.getDeplacementY() == 1)
			return 2;
		else if(d.getDeplacementX() == 1 && d.getDeplacementY() == 1)
			return 3;
		else if(d.getDeplacementX() == -1 && d.getDeplacementY() == 1)
			return 1;
		else if(d.getDeplacementX() == 1 && d.getDeplacementY() == -1)
			return 9;
		else if(d.getDeplacementX() == -1 && d.getDeplacementY() == -1)
			return 7;
		return 0;
	}
	
	public static Deplacement getDeplacementviaDireection(int i) {
		if(i == 4)
			return Deplacement.GAUCHE;
		else if(i == 6)
			return Deplacement.DROITE;
		else if(i == 8)
			return Deplacement.HAUT;
		else if(i == 2)
			return Deplacement.BAS;
		else if(i == 3)
			return Deplacement.DROITEBAS;
		else if(i == 1)
			return Deplacement.GAUCHEBAS;
		else if(i == 9)
			return Deplacement.DROITEHAUT;
		else if(i == 7)
			return Deplacement.GAUCHEHAUT;
		return null;
	}
	public void setDeplacementX(int deplacementX) {
		this.deplacementX = deplacementX;
	}

	public int getDeplacementY() {
		return deplacementY;
	}

	public void setDeplacementY(int deplacementY) {
		this.deplacementY = deplacementY;
	}
}