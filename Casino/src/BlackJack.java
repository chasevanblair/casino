import java.util.Collections;
import java.util.Scanner;
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
	String choice = "";
	private int playerHandSum = 0;
	private int dealerHandSum = 0;


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

	private void compareHands(boolean bust) {
		
		String dealerStr = "Dealer hand: ";
		for(Card c : dealerHand) {
			dealerStr += (c.toString() + ", ");
		} 
		if(bust) {
			if(playerHandSum == 21) {
				System.out.println("you got blackjack!");
			}
			//dealer stands on 17
			if(dealerHandSum > 21) {
				System.out.println("dealer busts");
				choice = "2";
			}
			else if(dealerHandSum <= 17) {
				Card drawn = deck.pop();
				dealerHand.push(drawn);
				
				dealerHandSum += Integer.parseInt(drawn.getValue());
				System.out.println(dealerStr + drawn.toString());
				compareHands(bust);
			}else {
				
				System.out.println(dealerStr + "\ndealer stands.");
			}
		}



	}

	private boolean deal() {
		//return true if dealer doesn't have blackjack
		playerHand.push(deck.pop());
		dealerHand.push(deck.pop());
		playerHand.push(deck.pop());
		dealerHand.push(deck.pop());
		String out = "";
		boolean ace = false;

		//moved code from hit to deal because values for dealer need to be checked for blackjack before game start
		System.out.println("Your Hand: " + playerHand.get(0).toString() + ", " + playerHand.get(1).toString());
		if(playerHand.contains(new Card("ace", "spades")) || playerHand.contains(new Card("ace", "clubs")) || playerHand.contains(new Card("ace", "diamonds")) || playerHand.contains(new Card("ace", "hearts"))){
			ace = true;
		}
		//can get 1 every time because this is always called on initial hand
		playerHandSum += Integer.parseInt(playerHand.get(0).getValue()) + Integer.parseInt(playerHand.get(1).getValue());

		dealerHandSum += (Integer.parseInt(dealerHand.get(0).getValue()) + Integer.parseInt(dealerHand.get(1).getValue()));
		if(dealerHandSum == 21) {
			System.out.println("Dealer has blackjack.");
			if(playerHandSum == 21) {
				System.out.println("push");
			}
			else {
				System.out.println("bust");
			}
			choice = "2";
			return false;
		}else {
			System.out.println("Dealer Hand: " + dealerHand.get(0) + ", hidden");
			return true;
		}
	}

	private boolean hit(Stack<Card> hand) {
		//return true if user doesn't bust
		String out = "Your hand: ";
		boolean ace = false;
		if(hand.contains(new Card("ace", "spades")) || hand.contains(new Card("ace", "clubs")) || hand.contains(new Card("ace", "diamonds")) || hand.contains(new Card("ace", "hearts"))){
			ace = true;
		}

		for(Card c : hand) {
			playerHandSum += Integer.parseInt(c.getValue());
			out += c.toString() + ", ";
		}
		Card c = deck.pop();
		hand.push(c);
		playerHandSum += Integer.parseInt(c.getValue());
		out += c.toString() + ", ";
		if (playerHandSum > 21 && ace) {
			playerHandSum -= 10;
			//change ace value from 11 to 1
		}else if(playerHandSum >= 21) {
			System.out.println(out);
			System.out.println("bust!");
			choice = "2";
			return false;
		}
		System.out.println(out);
		return true;

	}

	public void play(double bet, int playAmt) {
		deal();
		System.out.println("Choose move: Hit (1), Stand (2)");
		Scanner myObj = new Scanner(System.in);
		choice = myObj.nextLine();
		boolean bust = false;

		while(choice.equals("1")) {
			bust = hit(playerHand);
			if(playAmt > 1) {
				System.out.println("Choose move: Hit (1), Stand (2)");
				choice = myObj.nextLine();
			}
		}
		compareHands(bust);



	}


	public static void main(String[] args) {
		BlackJack b = new BlackJack(10);
		Scanner myObj = new Scanner(System.in);
		System.out.println("Your balance: " + b.balance);
		System.out.println("Press 1 to start playing: ");
		if(myObj.nextLine().equals("1")) {
			System.out.println("Enter amount to bet(no \'$\'): ");
			double bet = Double.parseDouble(myObj.nextLine());
			
			int playAmt = 0;
			b.play(bet, playAmt);
			System.out.println("Play again? yes (1), no (2): ");
			String again = myObj.nextLine();
			if(again.equals("1")) {
				playAmt++;
				b.play(bet, playAmt);
			}
			else {
				System.out.println(again);
				System.out.println("shutting down");
			}
		}	
		myObj.close();
	}
}
