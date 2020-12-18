import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SlotMachine {
	private double moneyLeft;
	private Random rand = new Random(); 

	private ArrayList<String> values = new ArrayList<String>();
	private int size;
	public HomeGui g;
	public boolean winDef = false;
	public boolean win = false;


	public SlotMachine(double moneyLeft, HomeGui g) {
		this.g = g;
		this.moneyLeft = moneyLeft;

		//add possible spin results
		values.add("1");
		values.add("2");
		values.add("3");
		values.add("4");
		values.add("5");
		values.add("6");
		values.add("7");
		values.add("jackpot");
		values.add("2x jackpot");

		size = values.size();
	}

	public boolean getWin() {
		return win;
	}



	public static boolean isInteger(String s) {
		try { 
			if(Integer.parseInt(s) < 7){
				return true;
			}
		} catch(NumberFormatException e) { 
			return false; 
		} catch(NullPointerException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}

	private double payout() {
		//checks amount of spins if it is enough it will guarantee win
		double mult = 1;
		String val = "";
		double chance = rand.nextDouble();
		if(chance <= .3) {
			//non 777 number match
			val += rand.nextInt(6);
			mult = 1.5;
		}
		else if(chance <= .6) {
			//777 match
			val = "7";
			mult = 2;

		}
		else if(chance <= .9) {
			//jackpot match
			val = "jackpot";
			mult = 4;
		}
		else {
			//2x jackpot match
			val = "2x jackpot";
			mult = 8;

		}
		g.result1.setText(val);
		g.result2.setText(val);
		g.result3.setText(val);
		return mult;

	}

	public double calcMult(String slotNum) {
		//calculate winnings
		double mult = 1;
		//return mult value
		if(isInteger(slotNum)) {
			mult = 1.5;
		}
		switch(slotNum) {
		case "7": mult = 2;
		break;

		case "jackpot": mult = 4;
		break;

		case "2x jackpot": mult = 8;
		break;
		}

		return mult;
	}

	public void spin(double bet) {
		if(!(moneyLeft - bet < 0)) { 
			g.lblNoMoney.setVisible(false);
			String first = values.get(rand.nextInt(size));
			String second = values.get(rand.nextInt(size));
			String third = values.get(rand.nextInt(size));

			//each random result of spin columns
			ArrayList<String> results = new ArrayList<String>();
			results.add(first);
			results.add(second);
			results.add(third);

			g.result1.setText(results.get(0));
			g.result2.setText(results.get(1));
			g.result3.setText(results.get(2));
			double mult = 1;
			//call winPayout first, if !none win = true and mult = the switch case thing

			if(results.get(0).equals(results.get(1)) && results.get(0).equals(results.get(2))){
				//natural win
				win = true;
				mult = calcMult(results.get(0));
			}else if(winDef){
				//predefined payout rate - every 5 spins lost in a row as defined in Home.java will win 
				mult = payout();
				win = true;
				
			}

			//begin to calc winnings
			
			if(win) {
				win = false;
				double amt = bet * mult;
				moneyLeft += amt;
				g.lblBalance.setText("Balance: $" + moneyLeft);
				g.lblPay.setText("You won $" + amt);
			}
			else {
				moneyLeft -= bet;
				g.lblBalance.setText("Balance: $" + moneyLeft);
				g.lblPay.setText("You lost $" + bet);
			}
			//send spin results to jframe
			
		}else {
			//display if user doesnt have their bet amount
			g.lblNoMoney.setVisible(true);
		}


	}

	public double run(){
		//get user input to pass their bet amount to the slot. need a welcome message prompting amount

		spin(Double.parseDouble(g.txtBetAmt.getText()));
		return moneyLeft;
	}




}
