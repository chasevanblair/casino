import java.util.Collections;
import java.util.Stack;

public class BlackJack {
	//maybe card class similar to nodes and have them in data struct where i can store face and value
	//or deck class which has a definition for card in the same file and handles making the deck inside there
	//ie deck.deal if number of hands dealt is > 5 shuffle the deck as that is what casinos do
	//queue of cards with a shuffle method once deck is finished
	private double balance;
	private Stack<Card> deck = new Stack<Card>();
	private Stack<Card> playerHand = new Stack<Card>();
	private Stack<Card> dealerHand = new Stack<Card>();


	
	public BlackJack(double balance) {
		this.balance = balance;
		shuffle();
	}
	
	public void buildDeck() {
		deck.clear();
		String cards[] = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};
		for(String i : cards){

			deck.push(new Card(i, "spades"));
			deck.push(new Card(i, "hearts"));
			deck.push(new Card(i, "clubs"));
			deck.push(new Card(i, "diamonds"));
		}
	}
	
	
	public Stack<Card> getDeck() {
		return deck;
	}

	public void shuffle() {
		//re-adds all cards to deck to be shuffled
		buildDeck();
		Collections.shuffle(deck);
	}
	
	private void deal() {
		playerHand.push(deck.pop());
		dealerHand.push(deck.pop());
		playerHand.push(deck.pop());
		dealerHand.push(deck.pop());
	}
	
	public void play() {
		
	}
	
	
	public static void main(String[] args) {
		BlackJack b = new BlackJack(10);
		for(Card c : b.getDeck()) {
			System.out.println(c.toString());
		}
		
	}
}
