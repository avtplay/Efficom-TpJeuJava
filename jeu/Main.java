import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Entite.EntiteVivante.Deplacement;
import Entite.EntiteVivante.Druide;
import Entite.EntiteVivante.Entite;
import Entite.EntiteVivante.Monstre;
import Entite.EntiteVivante.Personnage;
import Entite.objet.Antidote;
import Entite.objet.Arme;
import Entite.objet.Objet;
import Entite.objet.Potion;
import Mapping.Carte;
import Mapping.Cellule;
import Mapping.Coordinate;

public class Main {

	static Scanner sc = new Scanner(System.in);
	Random r = new Random();
	static boolean fin = false;

	public static void main(String[] args) {

		ArrayList<Entite> entites;
		Carte carte = new Carte();
		entites = carte.getEntites();
		System.out.println("Entrer nom du joueur");
		String nomJoueur = demanderString();
		String TagJoueur = "J1";
		Cellule spawnJoueur = carte.getCelluleViaCoordonne(new Coordinate(5, 5));
		String[] paramJoueur = new String[] { nomJoueur, TagJoueur };
		Personnage joueur = new Personnage(spawnJoueur, paramJoueur);
		entites.add(joueur);
		System.out.println("Bienvenue dans le jeu " + nomJoueur + " la partie va debuter");
		demanderString();
		while (!fin) {
			nettoyerConsole();
			affichageTour(joueur, carte);
			int choix = 0;
			do {
				choix = choixAction();
			} while (choix == 0);
			System.out.println(choix);
			switch (choix) {
				/////////////////////////////////////////// DEPLACEMENT ///////////////////////////////////////////
			case 1: 
				Deplacement d =menuDeplacement(carte,joueur);
				deplacer(joueur,carte,d);
				break;
				/////////////////////////////////////////// INVENTAIRE ///////////////////////////////////////////
			case 2:
				System.out.println("inventaire");
				inventaire(joueur);
				break;
				/////////////////////////////////////////// CARTE ///////////////////////////////////////////
			case 3:
				System.out.println("carte");
				afficherCarte(carte);
				break;
			}
		}

	}

	private static void deplacer(Personnage joueur, Carte carte, Deplacement d) {
		Cellule cell = joueur.getCel();
		cell.supprimerPersonnage(joueur);
		Coordinate coordDesination = new Coordinate(cell.getX()+d.getDeplacementX(),cell.getY()+d.getDeplacementY());
		Cellule cellDestination = carte.getCelluleViaCoordonne(coordDesination);
		cellDestination.ajouterPersonnage(joueur);
		joueur.deplacer(cellDestination);
		nettoyerConsole();
		System.out.println("vous vous etes depalcer sur en "+cellDestination);
		if(cellDestination.getOr() != 0) {
			System.out.println("vous avez trouvez "+cellDestination.getOr()+" que vous avez ajouter a votre bourse.");
			joueur.setBourse(joueur.getBourse()+cellDestination.getOr());
		}
		for (Entite e : cellDestination.getListPersonnage()) {
			if(e instanceof Monstre) {
				Monstre m = (Monstre)e;
				System.out.println("vous avez rencontrer un monstre");
			}
			if(e instanceof Druide) {
				Druide dr = (Druide)e;
				nettoyerConsole();
				System.out.println("Vous avez rencontrer un druide ! \n il vous propose sort pour 50 d'or.");
			}
		}
		if(! cellDestination.getListObjet().isEmpty()) {
			System.out.println("vous ave trouvez des objets: ");
			for (int k = 0; k < cellDestination.getListObjet().size() - 1; k++) {
				System.out.println(cellDestination.getListObjet().get(k).getNom() + ": \t \t" + (k + 1));
			}
			System.out.println("le quel voulez vous ramasser (0 pour aucun) ");
			int choixObjet = demanderInt(); 
			try {
				if(choixObjet ==0)
					return;
				Objet objetRamasser = cellDestination.getListObjet().get(choixObjet);
				if(!joueur.ajouterALInventaire(objetRamasser)) {
					System.out.println("votre inventaire est plein! \n Voulez vous l'echanger avec un autre objet ?\n Oui: \t 1 \t \t Non:\t 2");
					
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}

	private static Deplacement menuDeplacement(Carte c,Personnage j) {
		// TODO Auto-generated method stub
		Deplacement depla = null;
		System.out.println("Vous etes actuellement en "+j.getCel()+"\n Dans quelle direction voulez vous aller ? \n ");
		ArrayList<Deplacement> deplacements = c.getDeplacementDisponible(j.getCel());
		for (int r =0; r<deplacements.size()-1;r++) {
			System.out.println(deplacements.get(r).getStringDeplacement(deplacements.get(r), false)+": \t \t "+(r+1));
		}
		int choix = demanderInt();
		try {
			depla= deplacements.get(choix-1);
		}
		catch(Exception e) {
			System.out.println("une erreur est survenue");
			demanderString();
			nettoyerConsole();
		}
		return depla;
	}

	private static void inventaire(Personnage j) {
		boolean flag = false;
		while (!flag) {
			try {
				nettoyerConsole();
				System.out.println("voici votre inventaire:");
				for (int i = 0; i < j.getInventaire().size() - 1; i++) {
					System.out.println((i + 1) + ": \t" + j.getInventaire().get(i).getNom());
				}

				System.out.println("selectionnez un objet. \t tapez 0 pour retour");
				int choix = demanderInt();
				if(choix == 0) {
					return;
				}
				Objet select = j.getInventaire().get(choix - 1);
				System.out.println("vous avez selectionnez: " + select.getNom() + "\n que voulez vous en faire");
				if (select instanceof Arme) {
					Arme o = (Arme) j.getInventaire().get(choix - 1);
					System.out.println("1:\t Equiper \t \t  2:\t Jeter \n 3:\t Déséquiper \\t \\t  4:\t Annuler");
					String choix2 = demanderString();
					switch (choix2) {
					case "1":
						if(!o.isEstEquiper()) {
							j.setArmeEquiper(o);
							flag = true;
						}
						else {
							System.out.println("Cette arme est deja équiper");
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
					System.out.println("1:\t Utiliser \t \t  2:\t Jeter \n 3:\t Annuler");
					String choix2 = demanderString();
					switch (choix2) {
					case "1":
						o.utiliser(j);
						System.out.println("votre energie est maintenat à: "+j.getEnergie()+"/"+j.getMaxEnergie()+"\n et vous etes entierement guerri");
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
				}
				else if (select instanceof Potion) {
					Potion o = (Potion) j.getInventaire().get(choix - 1);
					System.out.println("1:\t Utiliser \t \t  2:\t Jeter \n 3:\t Annuler");
					String choix2 = demanderString();
					switch (choix2) {
					case "1":
						o.utiliser(j);
						System.out.println("votre energie est maintenat à: "+j.getEnergie()+"/"+j.getMaxEnergie());
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
			} catch (Exception e) {
				flag = false;
			}
		}

	}

	private static void afficherCarte(Carte c) {
		nettoyerConsole();
		// TODO Auto-generated method stub
		System.out.println("voici votre carte: \n \n");
		c.printCarte();
		System.out.println("appuyer sur Entrer pour quitter");
		demanderString();
	}

	private static String demanderString() {
		String str = "";
		str = sc.nextLine();
		return str;
	}

	private static int demanderInt() {
		int i = -1;
		i = sc.nextInt();
		return i;
	}

	private static void nettoyerConsole() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}

	private static void affichageTour(Personnage p, Carte c) {
		System.out.println("Tu es actuellement a la position " + p.getCel());
		System.out.println("voici les zonnes que tu peux apercevoir");
		for (int i = 0; i < 3; i++) {
			System.out.println();
		}
		c.displayCellGamer(p.getCel(), p.isVisionAmeliorer());
	}

	private static int choixAction() {
		System.out.println("Quel action voulez vous effectuer ? \n ");
		System.out.println("1: \t Se déplacer ");
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
