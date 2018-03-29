package Entite.Vehicule;

import Mapping.MapZone;

public enum EnumVehicule {
	MOTO(50, "Moto", "BIK", 0, 10),
	BATEAU(50, "Bateur", "BAT", 2, 10),
	VOITURE(50, "Voiture", "VOI", 3, 10),
	TELEPORTER(1, "Teleporter", "TEL", 5, 1),
	JETSKI(50, "Jetski", "JET", 1, 10);
	
	private int carburant;
	private String nom;
	private String tag;
	private int idZone;
	private int consomationCarburant;
	
	EnumVehicule(int carburant, String nom, String tag, int idZone, int conso){
		this.carburant = carburant;
		this.nom = nom;
		this.tag = tag;
		this.idZone = idZone;
		this.consomationCarburant = conso;
	}

	public int getConsomationCarburant() {
		return consomationCarburant;
	}

	public void setConsomationCarburant(int consomationCarburant) {
		this.consomationCarburant = consomationCarburant;
	}

	public static EnumVehicule getVehiculeByMapZone(int i) {
		switch(i) {
		case 0:
			return EnumVehicule.MOTO;
			
		case 1:
			return EnumVehicule.JETSKI;
			
		case 2:
			return EnumVehicule.BATEAU;
		case 5:
			return EnumVehicule.TELEPORTER;
			
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