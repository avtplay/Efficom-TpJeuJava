package Entite.EntiteVivante;

import java.util.ArrayList;
import java.util.Random;

import Entite.Vehicule.EnumVehicule;
import Entite.Vehicule.Vehicule;
import Entite.objet.Arme;
import Entite.objet.Objet;
import Interface.Combattant;
import Interface.Deplacable;
import Interface.Jouable;
import Mapping.Cellule;

public class Personnage extends Entite implements Deplacable, Combattant, Jouable {

	int poidTotal;
	int poidMax;
	int bourse;
	boolean visionAmeliorer = false;
	boolean dort = false;
	Arme armeEquipe = new Arme("poing", 0, 20);
	private EnumEtatSante etatSante = EnumEtatSante.BONETAT;
	Arme armeEquiper;
	ArrayList<Objet> inventaire;
	String name;
	EnumVehicule vehicule;

	public Arme getArmeEquiper() {
		return armeEquiper;
	}

	public boolean isDort() {
		return dort;
	}

	public void setDort(boolean dort) {
		this.dort = dort;
	}

	public void desequiper() {
		armeEquiper.setEstEquiper(false);
		this.armeEquiper = armeEquipe;
		armeEquipe.setEstEquiper(true);
	}

	public Arme getArmeEquipe() {
		return armeEquipe;
	}

	public boolean estVehiculer() {
		return !(vehicule == null);
	}

	public EnumVehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(EnumVehicule vehicule) {
		this.vehicule = vehicule;
	}

	public void setArmeEquipe(Arme armeEquipe) {
		this.armeEquipe = armeEquipe;
	}

	public void setArmeEquiper(Arme armeEquiper) {
		this.armeEquiper.setEstEquiper(false);
		armeEquiper.setEstEquiper(true);
		this.armeEquiper = armeEquiper;
	}

	public boolean peutUtiliserVehicule(Cellule cDestination) {
		return (this.vehicule.getCarburant() > 0) && (this.vehicule.getIdZone() == cDestination.getMapZone().getId());
	}

	public Personnage(Cellule c, String... n) {
		String na = "Joueur 1";
		if (n.length != 0 && n[0] != "") {
			na = n[0];
		}
		if (n.length != 0 && n[1] != "") {
			this.setTag(n[1]);
		}
		armeEquipe.setEstEquiper(true);
		armeEquiper = armeEquipe;
		this.setEnergie(1000);
		this.setMaxEnergie(1000);
		poidTotal = 0;
		poidMax = 10;
		bourse = 100;
		name = na;
		inventaire = new ArrayList<Objet>();
		ArrayList<Entite> list = c.getListPersonnage();
		list.add(this);
		c.setListPersonnage(list);
		this.setCel(c);
	}

	public boolean ajouterALInventaire(Objet o) {
		if ((inventaire.size() >= 5) && (poidTotal + o.getPoid()) > poidMax) {

			return false;
		}
		inventaire.add(o);
		poidTotal += o.getPoid();
		System.out.println(inventaire.size() + "taille de l'inventaire");
		return true;
	}

	void retirerALInventaire(int index) {
		Objet o = inventaire.get(index);
		poidTotal -= o.getPoid();
		inventaire.remove(index);
	}

	public boolean deplacer(Cellule cel) {
		int energieDeplacement = 10;
		if (estVehiculer() && peutUtiliserVehicule(cel)) {
			vehicule.setCarburant(vehicule.getCarburant() - vehicule.getConsomationCarburant());
		} else if (this.retirerEnergie(energieDeplacement)) {

			switch (cel.getMapZone().getId()) {
			case 1:
			case 2:
				energieDeplacement = 15;
				break;

			}
			this.getCel().setJoueurPres(false);
			ArrayList<Entite> l = this.getCel().getListPersonnage();
			l.remove(this);
			this.getCel().setListPersonnage(l);
			this.setCel(cel);
			l = cel.getListPersonnage();
			l.add(this);
			cel.setListPersonnage(l);
			this.getCel().setJoueurPres(true);

			this.effetEtatSante();
			this.appliqueDegatEtatSante();
		} else {
			return false;
		}
		return true;

	}

	public void appliqueDegatEtatSante() {
		this.setEnergie(this.getEnergie() - this.etatSante.getDegatSubit());
		System.out.println(this.etatSante.getNom());
	}

	public void effetEtatSante() {
		if (this.risqueMaladie())
			this.setEtatSante(EnumEtatSante.MALADE);

		return;
	}

	public boolean risqueMaladie() {
		Random rand = new Random();

		return rand.nextInt(100) == 0;
	}

	public void toStringPersonnage() {
		System.out.println(this.getName());
		System.out.println("Argent: " + this.getBourse() + "\tEtat santé: " + this.getEtatSante().getNom());
		System.out.println("Energie: " + this.getEnergie() + "\n"
				+ (this.getVehicule() != null ? ("carburant vehicule: " + this.getVehicule().getCarburant()) : ""));
		return;
	}

	@Override
	public void recevoirDmg(int dmg) {
		this.retirerEnergie(dmg);
	}

	@Override
	public void recevoirHeal(int heal) {
		// TODO Auto-generated method stub
		if (this.getEnergie() + heal >= 1000)
			this.setEnergie(1000);
		else
			this.setEnergie(this.getEnergie() + heal);

	}

	@Override
	public void attaquer(Combattant a) {
		a.recevoirDmg(this.getArmeEquipe().getPuissance());

	}

	@Override
	public void setjumelle() {
		// TODO Auto-generated method stub
		visionAmeliorer = true;
	}

	public void soignerEtat(Combattant e) {
		return;
	}

	@Override
	public void modifierEtatSante(EnumEtatSante etatSante) {
		this.setEtatSante(etatSante);
	}

	public void supprimerDeLInventaire(Objet select) {
		inventaire.remove(select);

	}

	public void supprimerDeLInventaire(int i) {
		inventaire.remove(i);
		System.out.println("inventaire size" + inventaire.size());
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

	public boolean isVisionAmeliorer() {
		return visionAmeliorer;
	}

	public void setVisionAmeliorer(boolean visionAmeliorer) {
		this.visionAmeliorer = visionAmeliorer;
	}

	public EnumEtatSante getEtatSante() {
		return etatSante;
	}

	public void setEtatSante(EnumEtatSante etatSante) {
		this.etatSante = etatSante;
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

	@Override
	public boolean Teleporter(Cellule cel) {
		if(this.getVehicule() != null && this.getVehicule().getTag().equals("TEL") && this.getVehicule().getCarburant() > 0) {
			this.getCel().setJoueurPres(false);
			ArrayList<Entite> l = this.getCel().getListPersonnage();
			l.remove(this);
			this.getCel().setListPersonnage(l);
			this.setCel(cel);
			l = cel.getListPersonnage();
			l.add(this);
			cel.setListPersonnage(l);
			this.getCel().setJoueurPres(true);
			this.effetEtatSante();
			this.appliqueDegatEtatSante();
			this.vehicule.setCarburant(this.getVehicule().getCarburant() - this.getVehicule().getConsomationCarburant());
			this.getCel().getVehicule().add(this.vehicule);
			this.vehicule = null;
		}
		return false;
	}

}