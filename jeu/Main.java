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

	static boolean fin = false;

	public static void main(String[] args) {
		Manager m = new Manager();
		ArrayList<Entite> entites;
		Carte carte = new Carte();
		entites = carte.getEntites();
		System.out.println("Entrer nom du joueur");
		String nomJoueur = m.demanderString();
		String TagJoueur = "J1";
		Cellule spawnJoueur = carte.getCelluleViaCoordonne(new Coordinate(25, 25));

		Cellule objettest = carte.getCelluleViaCoordonne(new Coordinate(26, 25));
		objettest.ajouterObjet(new Potion("postio", 1, 25));
		String[] paramJoueur = new String[] { nomJoueur, TagJoueur };
		Personnage joueur = new Personnage(spawnJoueur, paramJoueur);
		entites.add(joueur);
		spawnJoueur.setJoueurPres(true);
		System.out.println("Bienvenue dans le jeu " + nomJoueur + " la partie va debuter");
		m.demanderString();
		while (!fin) {
			m.nettoyerConsole();
			m.affichageTour(joueur, carte);
			int choix = 0;
			while (choix == 0) {
				choix = m.choixAction();
			}
			System.out.println(choix);
			switch (choix) {
			/////////////////////////////////////////// DEPLACEMENT
			/////////////////////////////////////////// ///////////////////////////////////////////
			case 1:
				Deplacement d = m.menuDeplacement(carte, joueur);
				m.deplacer(joueur, carte, d);
				break;
			/////////////////////////////////////////// INVENTAIRE
			/////////////////////////////////////////// ///////////////////////////////////////////
			case 2:
				System.out.println("inventaire");
				m.inventaire(joueur);
				break;
			/////////////////////////////////////////// CARTE
			/////////////////////////////////////////// ///////////////////////////////////////////
			case 3:
				System.out.println("carte");
				m.afficherCarte(carte);
				break;
			}
		}

	}

	
}
