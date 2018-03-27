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
	
	public static void main(String[] args) {
		
		Carte carte = new Carte();
		String nomJoueur = demanderString();
		String TagJoueur = "J1";
		Cellule spawnJoueur = carte.getCelluleViaCoordonne(new Coordinate(5,5));
	}

	
	
	private static String demanderString(){
		String str ="";
		str = sc.nextLine();
		return str;
	}
}
