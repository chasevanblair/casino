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
	String choice = "";
	private int playerHandSum = 0;
	private int dealerHandSum = 0;
	HomeGui g = new HomeGui();
	String move;
	double bet;
	boolean bust = false;
	boolean dealerAce = false;



	public BlackJack(double balance, HomeGui g) {
		this.g = g;
		this.balanceBJ = balance;
		shuffle();
	}
	public double endHand() {
		g.btnHit.setEnabled(false);
		g.btnStand.setEnabled(false);
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

	public boolean compareHands() {
		//return true if player wins or push
		
		boolean dealerDraw = true;
		boolean dealerBust = false;
		if(dealerHandSum > 16) {
			dealerDraw = false;
		}
		
		while(dealerDraw) {
			Card d = deck.pop();
			dealerHand.push(d);
			
			dealerHandSum += Integer.parseInt(d.getValue());
			if(dealerHandSum > 21 && dealerAce) {
				dealerHandSum -= 10;
			}
			else if(dealerHandSum > 21) {
				dealerBust = true;
				return true;
			}
			if(dealerHandSum > 16) {
				dealerDraw = false;
			}
		}
		if(dealerBust) {
			
			g.lblResult.setText("Dealer busts. Player won $" + bet);
			balanceBJ += bet * 2;
		}
		else if(playerHandSum < dealerHandSum) {
			String dealerFinal = "";
			for(Card d : dealerHand) {
				dealerFinal += d.toString() + "\n";
			}
			g.txtDealer.setText(dealerFinal);
			g.lblResult.setText("Dealer wins. Player lost $" + bet);
			balanceBJ -= bet;
			return false;
		}else if(playerHandSum > dealerHandSum) {
			g.lblResult.setText("Player wins. Won $" + bet);
			balanceBJ += bet * 2;
			return true;
		}else if(playerHandSum == dealerHandSum) {
			String dealerFinal = "";
			for(Card d : dealerHand) {
				dealerFinal += d.toString() + "\n";
			}
			g.txtDealer.setText(dealerFinal);
			g.lblResult.setText("Push. Bets returned.");
			return true;
		}
		return false;
		/*
		if(playerHandSum != dealerHandSum) {
			if(!bust) {
				if(playerHandSum == 21) {
					System.out.println("you got blackjack!");
					winner = "player wins";
				}
				//dealer stands on 17
				if(dealerHandSum > 21) {
					System.out.println("dealer busts");
					winner = "player wins";
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

				if(playerHandSum > dealerHandSum)
					winner = "player wins";
			}
		}else if(bust){
		}
		else {
			winner = "push";
		}
		for(Card c : dealerHand) {
			System.out.println("Dealer's hand: ");
			System.out.print(c.toString() + ", ");
		}
		System.out.println(winner);
*/


	}

	public boolean deal() {
		//return false if more cards aren't needed because either player has blackjack
		playerHand.clear();
		dealerHand.clear();
		playerHandSum = 0;
		dealerHandSum = 0;
		playerHand.push(deck.pop());
		dealerHand.push(deck.pop());
		playerHand.push(deck.pop());
		dealerHand.push(deck.pop());
		boolean playerBlackJack = false, dealerBlackJack = false;


		//moved code from hit to deal because values for dealer need to be checked for blackjack before game start

		playerHandSum += Integer.parseInt(playerHand.get(0).getValue()) + Integer.parseInt(playerHand.get(1).getValue());
		dealerHandSum += Integer.parseInt(dealerHand.get(0).getValue()) + Integer.parseInt(dealerHand.get(1).getValue());
		if(dealerHand.get(0).getName().equals("ace") || dealerHand.get(1).getName().equals("ace")) {
			dealerAce = true;
		}
		if(dealerHandSum == 21) {
			dealerBlackJack = true;
		}	
		if(playerHandSum == 21) {
			playerBlackJack = true;
		}
		g.txtDealer.setText(dealerHand.get(0).toString() + "\nHidden");
		g.txtPlayer.setText(playerHand.get(0).toString() + "\n" + playerHand.get(1).toString());

		if(dealerBlackJack && playerBlackJack) {
			g.txtDealer.setText(dealerHand.get(0).toString() + "\n" + dealerHand.get(1).toString());
			g.lblResult.setText("Dealer has blackjack. Lost $" + bet);
			g.lblResult.setText("Dealer and Player have blackjack. Money returned.");
			return true;
		}else if(dealerBlackJack) {
			g.txtDealer.setText(dealerHand.get(0).toString() + "\n" + dealerHand.get(1).toString());
			g.lblResult.setText("Dealer has blackjack. Lost $" + bet);
			balanceBJ -= bet;
			return false;

		}else if(playerBlackJack) {
			g.lblResult.setText("You have blackjack. Won $" + bet);
			balanceBJ += bet * 3;
			return false;
		}
		
		return true;
	}

	public boolean hit() {
		//return false if user doesn't bust

		String out = "";
		boolean ace = false;
		boolean bust = false;
		/*if(playerHand.contains(new Card("ace", "spades")) || playerHand.contains(new Card("ace", "clubs")) || playerHand.contains(new Card("ace", "diamonds")) || playerHand.contains(new Card("ace", "hearts"))){
			ace = true;
		}*/


		Card c = deck.pop();
		playerHand.push(c);

		for(Card cx : playerHand) {
			if(c.getValue().equals("11")) {
				ace = true;
			}
			out += cx.toString() + "\n";
		}
		g.txtPlayer.setText(out);
		//playerHandSum += Integer.parseInt(c.getValue());
		//out += c.toString() + ", ";
		if (playerHandSum > 21 && ace) {
			playerHandSum -= 10;
			//change ace value from 11 to 1
			bust = false;
		}
		playerHandSum += Integer.parseInt(c.getValue());
		g.lblPlayerHand.setText(g.lblPlayerHand.getText() + playerHandSum);
		if(playerHandSum > 21) {
			g.lblResult.setText("Bust. Player lost $" + bet);
			String dealerFinal = "";
			for(Card d : dealerHand) {
				dealerFinal += d.toString() + "\n";
			}
			g.txtDealer.setText(dealerFinal);
			bust = true;
		}
		return bust;
	}

	public void play(double bet) {

		boolean bust = deal();
		System.out.println("Choose move: Hit (1), Stand (2)1");

		while(!bust) {

			System.out.println("Choose move: Hit (1), Stand (2)2");
			if(move.equals("hit")) {
				bust = hit();
				System.out.println(playerHand.toString());
				System.out.println("Choose move: Hit (1), Stand (2)3");

			}else if(move.equals("stand:")) {

			}


		}
		compareHands();



	}
/*
	public double run() {
		//Scanner myObj = new Scanner(System.in);
		g.lblBalanceBJ.setText(("Your balance: " + balanceBJ));

		double bet = Double.parseDouble(g.txtBetBJ.getText());
		this.bet = bet;
		//deal false on dealer blackjack
		if (deal()) {
			
		}
		if (deal() || !bust) {
			g.btnHit.setVisible(true);
			g.btnStand.setVisible(true);
			g.btnHit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					bust = !hit();
				}

			});
			g.btnStand.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!bust) {
						
						boolean playerWins = compareHands();
					}

				}

			});
		}
		else{
			g.btnHit.setVisible(false);
			g.btnStand.setVisible(false);
		};
			//play(bet);

			/*System.out.println("Press 1 to start playing: ");
		if(myObj.nextLine().equals("1")) {
			System.out.println("Enter amount to bet(no \'$\'): ");
			double bet = Double.parseDouble(myObj.nextLine());

			int playAmt = 0;
			play(bet, playAmt);
			System.out.println("Play again? yes (1), no (2): aa ee oo ");
			String again = myObj.nextLine();
			if(again.equals("1")) {
				playAmt++;
				System.out.println(playAmt + "aa ee oo");
				play(bet, playAmt);
			}
			else {
				System.out.println(again);
				System.out.println("shutting down");
			}
		}	
		myObj.close();
			return balanceBJ;
*/
	}
	/*
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
	}*/

