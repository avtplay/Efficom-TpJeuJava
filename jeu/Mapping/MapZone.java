/**
 * 
 */
package Mapping;


/**
 * @author alanmenit
 *
 */
public enum MapZone {
	FORET(0),
	MONTAGNE(1),
	MER(2),
	PLAINE(3);
	
	private int id;
	
	MapZone(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public static MapZone getById(int id) {
		switch(id){
		case 0:
			return MapZone.FORET;
			
		case 1:
			return MapZone.MONTAGNE;
			
		case 2: 
			return MapZone.MER;
			
		default:
			return MapZone.PLAINE;
		}
	}
}