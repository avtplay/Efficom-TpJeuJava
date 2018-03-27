/**
 * 
 */
package Mapping;

/**
 * @author alanmenit
 *
 */
public enum MapZone {
	FORET(0, "FOR"),
	MONTAGNE(1, "MON"),
	MER(2, "MER"),
	PLAINE(3, "PLA");
	
	private int id;
	private String tag;
	
	MapZone(int id, String tag) {
		this.id = id;
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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