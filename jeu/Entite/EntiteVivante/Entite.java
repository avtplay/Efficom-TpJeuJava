package Entite.EntiteVivante;

import Mapping.Cellule;

public abstract class Entite {
	private Cellule cel;

	private int maxEnergie;
	private int energie;
	private String tag;
	private EnumEtatSante etatSante = EnumEtatSante.BONETAT;
	
	public EnumEtatSante getEtatSante() {
		return etatSante;
	}

	public void setEtatSante(EnumEtatSante etatSante) {
		this.etatSante = etatSante;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getMaxEnergie() {
		return maxEnergie;
	}

	public void setMaxEnergie(int maxEnergie) {
		this.maxEnergie = maxEnergie;
	}

	public int getEnergie() {
		return energie;
	}

	public void setEnergie(int energie) {
		this.energie = energie;
	}

	public void setCel(Cellule cel) {
		this.cel = cel;
	}
	
	public Cellule getCel() {
		return this.cel;
	}
	
	public boolean estEnVie() {
		return this.getEnergie() > 0;
	}
	
	protected boolean  retirerEnergie (int minus) {
		this.setEnergie( this.getEnergie()-minus);
		return this.estEnVie() ;
	}

	public boolean estVivant() {
		return getEnergie()>0;
	}
	
	protected boolean ajouterEnergie (int plus) {
		if(this.getEnergie() == this.getMaxEnergie() )
			return false;
		int energie = this.getEnergie();
		int maxEnergie = this.getMaxEnergie();
		
		energie = energie > maxEnergie ? maxEnergie : energie+plus;
		
		this.setEnergie(energie);
		return true;
	}
	
}