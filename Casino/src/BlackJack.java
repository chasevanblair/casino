import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class BlackJack {
	//maybe card class similar to nodes and have them in data struct where i can store face and value
	//or deck class which has a definition for card in the same file and handles making the deck inside there
	//ie deck.deal if number of hands dealt is > 5 shuffle the deck as that is what casinos do
	//queue of cards with a shuffle method once deck is finished
	public double balanceBJ;
	private Stack<Card> deck = new Stack<Card>();
	private Stack<Card> playerHand = new Stack<Card>();
	private Stack<Card> dealerHand = new Stack<Card>();
	private int playerHandSum = 0;
	private int dealerHandSum = 0;
	HomeGui g = new HomeGui();
	double bet;
	boolean bust = false;
	boolean dealerAce = false;
	boolean playerAce = false;
	int moveAmt = 0;
	boolean playerBlackJack = false, dealerBlackJack = false;


	public BlackJack(double balance, HomeGui g) {
		this.g = g;
		this.balanceBJ = balance;

		shuffle();
	}
	public double endHand() {
		//ends hand by clearing hand textareas and disabling hit/stand
		//updates balance
		g.lblBalanceBJ.setText("Balance: $" + balanceBJ);
		g.btnHit.setEnabled(false);
		g.btnStand.setEnabled(false);
		g.btnDeal.setEnabled(true);
		playerHand.clear();
		dealerHand.clear();
		playerHandSum = 0;
		dealerHandSum = 0;
		moveAmt++;
		if(moveAmt == 5) {
			shuffle();
		}
		return balanceBJ;
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

	public void compareHands() {
		//to be called on stand or player reaching 21
		boolean dealerDraw = true;
		boolean dealerBust = false;
		if(dealerHandSum > 16) {
			dealerDraw = false;
		}

		while(dealerDraw) {
			//dealer draws until 16 or higher then stands
			Card d = deck.pop();
			dealerHand.push(d);

			dealerHandSum += Integer.parseInt(d.getValue());
			if(dealerHandSum > 21 && dealerAce) {
				dealerHandSum -= 10;
			}
			else if(dealerHandSum > 21) {
				dealerBust = true;
			}
			if(dealerHandSum >= 16) {
				dealerDraw = false;
			}
		}
		if(dealerHandSum > 21) {
			dealerBlackJack = true;
		}
		String dealerFinal = "";
		for(Card d : dealerHand) {
			dealerFinal += d.toString() + "\n";
		}
		g.txtDealer.setText(dealerFinal);
		g.lblResult.setText("Dealer wins. Player lost $" + bet);

		if(dealerBust) {

			g.lblResult.setText("Dealer busts. Player won $" + bet);
			balanceBJ += bet * 2;
		}
		else if(playerHandSum < dealerHandSum) {

			balanceBJ -= bet;
		}else if(playerHandSum > dealerHandSum) {
			g.lblResult.setText("Player wins. Won $" + bet);
			balanceBJ += bet * 2;
		}else if(playerHandSum == dealerHandSum) {

			g.lblResult.setText("Push. Bets returned.");
		}

	}

	public double deal() {
		//return false if more cards aren't needed because either player has blackjack
		moveAmt++;
		if(moveAmt == 5) {
			shuffle();
		}
		g.lblResult.setText("");
		double bet = Double.parseDouble(g.txtBetBJ.getText());
		this.bet = bet;
		balanceBJ -= bet;
		g.lblBalanceBJ.setText("Balance: $" + balanceBJ);

		playerHand.clear();
		dealerHand.clear();
		playerHandSum = 0;
		dealerHandSum = 0;
		playerHand.push(deck.pop());
		dealerHand.push(deck.pop());
		playerHand.push(deck.pop());
		dealerHand.push(deck.pop());
		playerBlackJack = false; 
		dealerBlackJack = false;


		//moved code from hit to deal because values for dealer need to be checked for blackjack before game start

		playerHandSum += Integer.parseInt(playerHand.get(0).getValue()) + Integer.parseInt(playerHand.get(1).getValue());
		dealerHandSum += Integer.parseInt(dealerHand.get(0).getValue()) + Integer.parseInt(dealerHand.get(1).getValue());
		if(dealerHand.get(0).getName().equals("ace") || dealerHand.get(1).getName().equals("ace")) {
			dealerAce = true;
		}
		//checks for starting blackjacks so the rest of the code doesn't execute
		//double aces will default to 12 (11+1)
		if(dealerHandSum == 21) {
			dealerBlackJack = true;
		}else if(dealerHandSum == 22) {
			dealerAce = true;
			dealerHandSum = 12;
		}
		if(playerHandSum == 21) {
			playerBlackJack = true;
		}else if(playerHandSum == 22) {
			playerAce = true;
			playerHandSum = 12;
		}
		//initial hand text
		g.txtDealer.setText(dealerHand.get(0).toString() + "\nHidden");
		g.txtPlayer.setText(playerHand.get(0).toString() + "\n" + playerHand.get(1).toString());
		g.lblPlayerHand.setText("Player Hand:" + playerHandSum);

		//what to do on initial blackjack
		if(dealerBlackJack && playerBlackJack) {
			g.txtDealer.setText(dealerHand.get(0).toString() + "\n" + dealerHand.get(1).toString());
			g.lblResult.setText("Dealer has blackjack. Lost $" + bet);
			g.lblResult.setText("Dealer and Player have blackjack. Money returned.");
			return endHand();
		}else if(dealerBlackJack) {
			g.txtDealer.setText(dealerHand.get(0).toString() + "\n" + dealerHand.get(1).toString());
			g.lblResult.setText("Dealer has blackjack. Lost $" + bet);
			balanceBJ -= bet;
			return endHand();

		}else if(playerBlackJack) {
			g.lblResult.setText("You have blackjack. Won $" + bet);
			balanceBJ += bet * 3;
			return endHand();
		}
		return balanceBJ;
	}

	public boolean hit() {
		//return false if hand ends ie. blackjack or bust
		String out = "";
		playerAce = false;
		bust = false;


		//draw card add to hand
		Card c = deck.pop();
		playerHand.push(c);

		for(Card cx : playerHand) {
			if(c.getValue().equals("11")) {
				playerAce = true;
			}
			out += cx.toString() + "\n";
		}
		g.txtPlayer.setText(out);
		//update player hand output with new card
		if (playerHandSum > 21 && playerAce) {
			playerHandSum -= 10;
			//change ace value from 11 to 1
			bust = false;
		}
		playerHandSum += Integer.parseInt(c.getValue());
		g.lblPlayerHand.setText("Player Hand:" + playerHandSum);
		//bust calc
		if(playerHandSum > 21) {
			g.lblResult.setText("Bust. Player lost $" + bet);
			String dealerFinal = "";
			for(Card d : dealerHand) {
				dealerFinal += d.toString() + "\n";
			}

			g.txtDealer.setText(dealerFinal);
			bust = true;
		}
		if(playerHandSum == 21) {
			playerBlackJack = true;
			compareHands();
			bust = true;
		}
		return bust;
	}


}

