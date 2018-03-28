package Entite.EntiteVivante;

public enum EnumEtatSante {
	BONETAT(0),
	MALADE(2),
	EMPOISONNE(3);
	
	private int degatSubit;
	
	EnumEtatSante(int degatSubit){
		this.degatSubit = degatSubit;
	}

	public int getDegatSubit() {
		return degatSubit;
	}

	public void setDegatSubit(int degatSubit) {
		this.degatSubit = degatSubit;
	}
}