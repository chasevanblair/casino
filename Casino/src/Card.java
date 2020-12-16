
public class Card {
	private String value;
	private String name;
	private String suit;
	
	public Card(String value, String suit) {
		this.name = value;
		if(name.equals("jack") || name.equals("king") || name.equals("queen")) {
			this.value = "10";
		}else if(name.equals("ace")) {
			this.value = "11";
			//default to 11 and if value goes over 21 set it to 1
		}
		else {
			this.value = value;

		}
		this.suit = suit;
	}
	public String getName() {
		return name;
	}
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return name + " of " + suit;
	}
	
	
}
