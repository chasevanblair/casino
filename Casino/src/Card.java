
public class Card {
	private String value;
	private String face;
	
	public Card(String value, String face) {
		this.value = value;
		this.face = face;
	}
	
	@Override
	public String toString() {
		return value + " of " + face;
	}
	
	
}
