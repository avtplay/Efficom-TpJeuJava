import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Entite.EntiteVivante.Deplacement;
import Entite.EntiteVivante.Druide;
import Entite.EntiteVivante.Entite;
import Entite.EntiteVivante.Monstre;
import Entite.EntiteVivante.Personnage;
import Entite.Vehicule.EnumVehicule;
import Entite.objet.Antidote;
import Entite.objet.Arme;
import Entite.objet.Jumelle;
import Entite.objet.Objet;
import Entite.objet.Potion;
import Mapping.Carte;
import Mapping.Cellule;
import Mapping.Coordinate;

public class Manager {

	static Scanner sc;
	Random r;

	public void deplacer(Personnage joueur, Carte carte, Deplacement d) {
		Cellule cell = joueur.getCel();
		Coordinate coordDesination = new Coordinate(cell.getX() + d.getDeplacementX(),
				cell.getY() + d.getDeplacementY());
		Cellule cellDestination = carte.getCelluleViaCoordonne(coordDesination);
		joueur.deplacer(cellDestination);
		nettoyerConsole();
		System.out.println("vous vous etes depalcer sur en " + cellDestination);
		if (cellDestination.getOr() != 0) {
			System.out
					.println("vous avez trouvez " + cellDestination.getOr() + " que vous avez ajouter a votre bourse.");
			joueur.setBourse(joueur.getBourse() + cellDestination.getOr());
		}
		for (Entite e : cellDestination.getListPersonnage()) {
			if (e instanceof Monstre) {
				Monstre m = (Monstre) e;
				System.out.println("vous avez rencontrer un monstre");
				combat(joueur, m);
			}
			if (e instanceof Druide) {
				Druide dr = (Druide) e;
				nettoyerConsole();
				System.out.println("Vous avez rencontrer un druide ! \n il vous propose sort pour 50 d'or.");
				utiliserDruide(joueur, dr);
			}
		}
		//// objet
		ramasserObjet(joueur, carte);
		this.entrerDansVehicule(joueur);
	}

	public static void combat(Personnage joueur, Monstre m) {
		Random r = new Random();
		if ((r.nextInt(1) + 1) > 2) {

			m.attaquer(joueur);
			System.out
					.println("le monstre vous attaque ! \n il vous reste " + joueur.getEnergie() + " point d'energie");
		}
		int cpt = 0;
		while (m.estEnVie() && joueur.estEnVie()) {
			if (cpt % 2 == 0) {
				joueur.attaquer(m);
				System.out.println("vous attaquer le monstre il lui rest " + (m.getEnergie()>0?m.getEnergie():0) + " points d'energie");
			}
			if (cpt % 2 == 1) {
				m.attaquer(joueur);
				System.out
						.println("le monstre vous attaque ! \n il vous reste " + joueur.getEnergie() + " point d'energie");
			}
			cpt++;
		}
		joueur.getCel().supprimerPersonnage(m);
		System.out.println((joueur.estEnVie()?"vous avez gagner !":"votre defaite est Honteuse !")+ " -->");
		demanderString();
	}

	public void utiliserDruide(Personnage p, Druide d) {
		int choix = 0;
		System.out.println("Souhatez-vous utiliser le druide?\n\tOui: 1\n\tNon: 2");
		do {
			choix = demanderInt();
		} while (choix != 1 && choix != 2);

		if (choix == 1) {
			if (d.paiementAccepte(p)) {
				d.utiliser(p);
			}
		}
		return;
	}
	
	public void entrerDansVehicule(Personnage p) {
		if(p.getCel().getVehicule().size() > 0) {
			int cpt =0;
			for(int i=0;i<p.getCel().getVehicule().size();i++) {
				System.out.println("un véhicule de type "+p.getCel().getVehicule().get(i).getNom()+" est present sur la cellule voulez vous l'utiliser ? \n 1:\t Oui \t \t 2: \t Non");
				int choixUtiliserVehicule = demanderInt();
				if(choixUtiliserVehicule == 1) {
					if(p.getVehicule() !=null) {
					EnumVehicule vehiculeTmp = p.getVehicule();
					p.getCel().getVehicule().add(vehiculeTmp);
					}
					p.setVehicule(p.getCel().getVehicule().get(i));
					p.getCel().getVehicule().remove(i);
					
				}
				cpt ++;
			}
		}
	}

	public static void ramasserObjet(Personnage p, Carte c) {
		int choixObjet = -1;
		while (choixObjet != 0 && !p.getCel().getListObjet().isEmpty()) {
			System.out.println("vous ave trouvez des objets: ");
			for (int k = 0; k < p.getCel().getListObjet().size(); k++) {
				System.out.println(p.getCel().getListObjet().get(k).getNom() + ": \t \t" + (k + 1));
			}
			System.out.println("le quel voulez vous ramasser (0 pour aucun) ");
			choixObjet = demanderInt();
			try {
				if (choixObjet == 0)
					return;
				Objet objetRamasser = p.getCel().getListObjet().get(choixObjet - 1);
				if (!p.ajouterALInventaire(objetRamasser)) {
					System.out.println(
							"votre inventaire est plein! \n Voulez vous l'echanger avec un autre objet ?\n Oui: \t 1 \t \t Non:\t 2");
					int choixJeter = demanderInt();
					if (choixJeter == 1) {
						System.out.println("quel objet jeter ? ");
						listerInvenrtaire(p);
						int indexAjeter = demanderInt();
						Objet Ajeter = prendreObjetDansInventaire(p, indexAjeter - 1);
						p.ajouterALInventaire(objetRamasser);
						p.getCel().supprimerObjet(objetRamasser);
						p.getCel().ajouterObjet(Ajeter);
					}
				} else {
					p.getCel().supprimerObjet(objetRamasser);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public Deplacement menuDeplacement(Carte c, Personnage j) {
		// TODO Auto-generated method stub
		Deplacement depla = null;
		System.out.println(
				"Vous etes actuellement en " + j.getCel() + "\n Dans quelle direction voulez vous aller ? \n ");
		ArrayList<Deplacement> deplacements = c.getDeplacementDisponible(j.getCel());
		for (int r = 0; r < deplacements.size() - 1; r++) {
			int affichageIndex = r + 1;
			System.out.println(deplacements.get(r).getStringDeplacement(deplacements.get(r), false) + ": \t \t "
					+ (affichageIndex));
		}
		int choix = demanderInt();
		try {
			depla = deplacements.get(choix - 1);
			System.out.println(depla.getDeplacementX() + "," + depla.getDeplacementY());
		} catch (Exception e) {
			System.out.println("une erreur est survenue");
			demanderString();
			nettoyerConsole();
		}
		return depla;
	}

	public void inventaire(Personnage j, Carte c) {
		boolean flag = false;
		while (!flag) {
			try {
				nettoyerConsole();
				System.out.println("voici votre inventaire:");
				listerInvenrtaire(j);

				System.out.println("selectionnez un objet. \t tapez 0 pour retour");
				int choix = demanderInt();
				if (choix == 0) {
					return;
				}
				Objet select = j.getInventaire().get(choix - 1);
				System.out.println("vous avez selectionnez: " + select.getNom() + "\n que voulez vous en faire");
				if (select instanceof Arme) {
					Arme o = (Arme) j.getInventaire().get(choix - 1);
					System.out.println("1:\t Equiper \t \t  2:\t Jeter \n 3:\t Dessequiper \t \t  4:\t Annuler");
					String choix2 = demanderString();
					switch (choix2) {
					case "1":
						if (!o.isEstEquiper()) {
							j.setArmeEquiper(o);
							flag = true;
						} else {
							System.out.println("Cette arme est deja �quiper");
						}
						break;
					case "2":
						j.supprimerDeLInventaire(select);
						ArrayList<Objet> l = j.getCel().getListObjet();
						l.add(o);
						j.getCel().setListObjet(l);
						flag = true;
						break;
					case "3":
						j.desequiper();
						break;
					case "4":
						break;
					default:
						break;
					}
				} else if (select instanceof Antidote) {
					Potion o = (Antidote) j.getInventaire().get(choix - 1);
					System.out.println(" 1:\t Utiliser \t \t  2:\t Jeter \n 3:\t Annuler");
					String choix2 = demanderString();
					switch (choix2) {
					case "1":
						o.utiliser(j);
						System.out.println("votre energie est maintenat �: " + j.getEnergie() + "/" + j.getMaxEnergie()
								+ "\n et vous etes entierement guerri");
						j.supprimerDeLInventaire(choix - 1);
						flag = true;
						break;
					case "2":
						j.supprimerDeLInventaire(select);
						ArrayList<Objet> l = j.getCel().getListObjet();
						l.add(o);
						j.getCel().setListObjet(l);
						flag = true;
						break;
					case "3":
						break;
					default:
						break;
					}
				} else if (select instanceof Potion) {
					Potion o = (Potion) j.getInventaire().get(choix - 1);
					System.out.println("1:\t Utiliser \t \t  2:\t Jeter \n 3:\t Annuler");
					String choix2 = demanderString();
					switch (choix2) {
					case "1":
						o.utiliser(j);
						System.out
								.println("votre energie est maintenat �: " + j.getEnergie() + "/" + j.getMaxEnergie());
						j.supprimerDeLInventaire(choix - 1);
						flag = true;
						break;
					case "2":
						j.supprimerDeLInventaire(select);
						ArrayList<Objet> l = j.getCel().getListObjet();
						l.add(o);
						j.getCel().setListObjet(l);
						flag = true;
						break;
					case "3":
						flag = true;
						break;
					default:
						break;
					}
				}
				else if (select instanceof Jumelle) {
					Jumelle o = (Jumelle)select;
					System.out.println("1:\t Utiliser \t \t  2:\t Jeter \n 3:\t Annuler");
					String choix2 = demanderString();
					switch (choix2) {
					case "1":
						o.utiliser(j);
						System.out
								.println("vous voyer maintenant plus loin");
						c.displayCellGamer(j.getCel(), true);
						System.out.println("Appuyer sur Entrer pour continuer");
						demanderString();
						j.setVisionAmeliorer(false);
						flag = true;
						break;
					case "2":
						j.supprimerDeLInventaire(select);
						j.setVisionAmeliorer(false);
						ArrayList<Objet> l = j.getCel().getListObjet();
						l.add(o);
						j.getCel().setListObjet(l);
						flag = true;
						break;
					case "3":
						flag = true;
						break;
					default:
						break;
					}
				}
			} catch (Exception e) {
				flag = false;
			}
		}

	}

	public static void afficherCarte(Carte c) {
		nettoyerConsole();
		// TODO Auto-generated method stub
		System.out.println("voici votre carte: \n \n");
		c.printCarte();
		System.out.println("appuyer sur Entrer pour quitter");
		demanderString();
	}

	public static void listerInvenrtaire(Personnage p) {
		for (int i = 0; i < p.getInventaire().size(); i++) {
			System.out.println(p.getInventaire().get(i).getNom() + ": \t " + (i + 1));
		}
	}

	public static Objet prendreObjetDansInventaire(Personnage p, int index) {
		return p.getInventaire().remove(index);
	}

	public static String demanderString() {
		String str = "";
		sc = new Scanner(System.in);
		str = sc.nextLine();
		return str;
	}

	public static int demanderInt() {
		int i = -1;
		sc = new Scanner(System.in);
		i = sc.nextInt();
		return i;
	}

	public static void nettoyerConsole() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}

	public void affichageTour(Personnage p, Carte c) {
		System.out.println("Tu es actuellement a la position " + p.getCel());
		System.out.println("voici les zonnes que tu peux apercevoir");
		for (int i = 0; i < 3; i++) {
			System.out.println();
		}
		c.displayCellGamer(p.getCel(), p.isVisionAmeliorer());
	}

	public int choixAction() {
		System.out.println("Quel action voulez vous effectuer ? \n ");
		System.out.println("1: \t Se d�placer ");
		System.out.println("2: \t Inventaire");
		System.out.println("3: \t regarder votre carte");
		String choix = demanderString();
		switch (choix) {
		case "1":
			return 1;
		case "2":
			return 2;
		case "3":
			return 3;
		default:
			return 0;
		}
	}
}