package Interface;

public interface Combattant {
		
	public void recevoirDmg(int dmg);

	public void recevoirHeal(int heal);
	
	public void attaquer(Combattant a);
	
	public void soignerEtat(Combattant e);
		
}