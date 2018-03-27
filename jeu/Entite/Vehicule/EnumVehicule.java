package Entite.Vehicule;

import Mapping.MapZone;

public enum EnumVehicule {
	MOTO(2, "Moto", "BIK", 0),
	BATEAU(2, "Bateur", "BAT", 2),
	VOITURE(2, "Voiture", "VOI", 3),
	JETSKI(2, "Jetski", "JET", 1);
	
	private int carburant;
	private String nom;
	private String tag;
	private int idZone;
	
	EnumVehicule(int carburant, String nom, String tag, int idZone){
		this.carburant = carburant;
		this.nom = nom;
		this.tag = tag;
		this.idZone = idZone;
	}

	public static EnumVehicule getVehiculeByMapZone(MapZone z) {
		switch(z.getId()) {
		case 0:
			return EnumVehicule.MOTO;
			
		case 1:
			return EnumVehicule.JETSKI;
			
		case 2:
			return EnumVehicule.BATEAU;
			
		default:
			return EnumVehicule.VOITURE;
		}
	}
	
	public int getCarburant() {
		return carburant;
	}

	public void setCarburant(int carburant) {
		this.carburant = carburant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getIdZone() {
		return idZone;
	}

	public void setIdZone(int idZone) {
		this.idZone = idZone;
	}
}