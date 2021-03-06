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
import Entite.objet.Jumelle;
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
		entites.addAll(carte.getEntites());
		System.out.println("Entrer nom du joueur");
		String nomJoueur = m.demanderString();
		String TagJoueur = "J1";
		Cellule spawnJoueur = carte.getCelluleViaCoordonne(new Coordinate(25, 25));
		Cellule spawnMonster =  carte.getCelluleViaCoordonne(new Coordinate(25, 26));
		Monstre mo = new Monstre(spawnMonster,50,"M");
		Jumelle a = new Jumelle( );
		carte.ajouterTeleporter(new Coordinate(24,24));
		carte.getCelluleViaCoordonne(new Coordinate(24, 25)).ajouterObjet(a);
		spawnMonster.ajouterPersonnage(mo); 
		
		carte.getEntites().add(mo);
		Cellule objettest = carte.getCelluleViaCoordonne(new Coordinate(26, 25));
		objettest.ajouterObjet(new Potion("potion de batard", 1, -25));
		objettest.ajouterPersonnage(new Druide(objettest, "Druide", 0));
		String[] paramJoueur = new String[] { nomJoueur, TagJoueur };
		Personnage joueur = new Personnage(spawnJoueur, paramJoueur);
		//joueur.setVisionAmeliorer(true);
		spawnJoueur.setJoueurPres(true);
		System.out.println("Bienvenue dans le jeu " + nomJoueur + " la partie va debuter");
		m.demanderString();
		while (joueur.estEnVie() && !joueur.getCel().isObjectifPresent() ) {
			carte.genererMonstreDeplacement();
			m.nettoyerConsole();
			m.affichageTour(joueur, carte);
			int choix = 0;
			joueur.toStringPersonnage();
			if(joueur.getCel().checkMonstre() != null) {
				m.combat(joueur, joueur.getCel().checkMonstre());
			}
			joueur.setDort(false);
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
			//////////////////////////////////////////////////////////////////////////////////////
			case 2:
				System.out.println("inventaire");
				m.inventaire(joueur,carte);
				break;
			 /////////////////////////////////////////// CARTE
			/////////////////////////////////////////// ///////////////////////////////////////////
			case 3:
				System.out.println("carte");
				m.afficherCarte(carte);
				break;
			case 4: 
				joueur.recevoirHeal(50);
				joueur.setDort(true);
			}
		}
		System.out.println(!joueur.estEnVie()?"PERDU ! nooob ":"GAGNE ! GG ");
	}

	
}