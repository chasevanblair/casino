
public class Card {
	private String value;
	private String suit;
	
	public Card(String value, String suit) {
		if(value.equals("jack") || value.equals("king") || value.equals("queen")) {
			this.value = "10";
		}else if(value.equals("ace")) {
			this.value = "11";
			//default to 11 and if value goes over 21 set it to 1
		}
		else {
			this.value = value;

		}
		this.suit = suit;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value + " of " + suit;
	}
	
	
}
