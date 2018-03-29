package Entite.EntiteVivante;

public enum EnumEtatSante {
	BONETAT(0, "Bon état"),
	MALADE(2, "Malade"),
	EMPOISONNE(3, "Empoisonné");
	
	private int degatSubit;
	private String nom;
	
	EnumEtatSante(int degatSubit, String nom){
		this.degatSubit = degatSubit;
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getDegatSubit() {
		return degatSubit;
	}

	public void setDegatSubit(int degatSubit) {
		this.degatSubit = degatSubit;
	}
}