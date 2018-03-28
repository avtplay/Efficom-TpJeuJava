import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Entite.EntiteVivante.Entite;
import Entite.EntiteVivante.Personnage;
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
			switch(choix) {
			case 1:
				
			case 2:
				inventaire(joueur);
			case 3:
				afficherCarte(carte);
			}
		}

	}

	private static void inventaire(Personnage j) {
		nettoyerConsole();
		System.out.println("voici votre inventaire:");
		for(int i=0;i<j.getInventaire().size()-1;i++) {
			System.out.println((i+1)+": \t"+j.getInventaire().get(i).getNom());
		}
		System.out.println("selectionnez un objet");
		String choix = demanderString();
		
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
		for (int i = 0; i < 10; i++) {
			System.out.println();
		}
		c.displayCellGamer(p.getCel(), p.asVisionAmeliorer());
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
