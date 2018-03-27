import java.util.ArrayList;

import Entite.EntiteVivante.Entite;
import Entite.EntiteVivante.Personnage;
import Mapping.Carte;
import Mapping.Cellule;
import Mapping.Coordinate;

public class Main {

	public static void main(String[] args) {
		System.out.println("hello");
		Carte c = new Carte();
		Cellule spawnPlayer = c.getCelluleViaCoordonne(new Coordinate(5,5));
		Personnage player = new Personnage(spawnPlayer,"player 1","P1");
		player.getCel().getListPersonnage().remove(player);
		player.deplacer(c.getCellulevoisine(player.getCel()).get(1));
		
		System.out.println("Cellules voisinnes de la cellule du joueur("+player.getCel()+")");
		for(Cellule i:c.getCellulevoisine(player.getCel())) {
			System.out.println(i);
		}
		ArrayList<Entite> entites  = new ArrayList<Entite>();
		entites.add(player);
		entites.addAll(c.getEntites());
		//c.printCarte();

	}

}
