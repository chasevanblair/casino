
public class Card {
	private String value;
	private String face;
	
	public Card(String value, String face) {
		if(value.equals("jack") || value.equals("king") || value.equals("queen")) {
			this.value = "10";
		}else if(value.equals("ace")) {
			this.value = "11";
			//default to 11 and if value goes over 21 set it to 1
		}
		else {
			this.value = value;

		}
		this.face = face;
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return value + " of " + face;
	}
	
	
}
