import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SlotMachine {
	private int spinNum = 0;
	//moneyLeft will need to be persistent in main class so player can swap between games
	private double moneyLeft;
	//maybe map values key: which 3 aka 7,jack[pot,2x value: the multiplier their bet returns
	private Random rand = new Random(); 

	private ArrayList<String> values = new ArrayList<String>();
	private int size;

	public SlotMachine(double moneyLeft) {
		this.moneyLeft = moneyLeft;

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

			double chance = rand.nextDouble();
			if(chance <= .3) {
				//non 777 number match
				mult = 1.5;
			}
			else if(chance <= .6) {
				//777 match
				mult = 2;
				System.out.println("Wow! 777! 2x return.");

			}
			else if(chance <= .9) {
				//jackpot match
				mult = 4;
				System.out.println("Wow! jackpot! 4x return.");
			}
			else {
				//2x jackpot match
				mult = 8;
				System.out.println("Wow! 2x Jackpot!! 8x return.");

			}
		return mult;
		
	}
	
	public double calcMult(String slotNum) {
		double mult = 1;
		//return mult value
		if(isInteger(slotNum)) {
			mult = 1.5;
		}
		switch(slotNum) {
		case "7": mult = 2;
		System.out.println("Wow! 777! 2x return.");
		break;
			
		case "jackpot": mult = 4;
		System.out.println("Wow! jackpot! 4x return.");
		break;
		
		case "2x jackpot": mult = 8;
		System.out.println("Wow! 2x Jackpot!! 8x return.");
		break;
		}
		
		return mult;
	}
	
	public void spin(double bet) {
		if(!(moneyLeft - bet < 0)) {
			String first = values.get(rand.nextInt(size));
			String second = values.get(rand.nextInt(size));
			String third = values.get(rand.nextInt(size));

			ArrayList<String> results = new ArrayList<String>();
			results.add(values.get(rand.nextInt(size)));
			results.add(values.get(rand.nextInt(size)));
			results.add(values.get(rand.nextInt(size)));
			spinNum++;
			
			double mult = 1;
			boolean win = false;
			//call winPayout first, if !none win = true and mult = the switch case thing
			
			if(results.get(0).equals(results.get(1)) && results.get(0).equals(results.get(2))){
				//natural win
				System.out.println("Results: " + results.get(0) + ", " + results.get(1) + ", " + results.get(2));
				spinNum = 0;
				win = true;
				mult = calcMult(results.get(0));
			}else if(spinNum > 5){
				mult = payout();
				spinNum = 0;
				win = true;
			}else {
				System.out.println("Results: " + results.get(0) + ", " + results.get(1) + ", " + results.get(2));
			}
			
			if(win) {
				double amt = bet * mult;
				moneyLeft += amt;
				System.out.println("Congratulations! You won $" + amt + ", current balance of $" + moneyLeft);
			}else {
				moneyLeft -= bet;
				System.out.println("Sorry, spin did not win. You now have a balance of $" + moneyLeft);
			}
			
		}else {
			System.out.println("You do not have enough money to bet this amount. You have a balance of $" + moneyLeft);
		}
		
	}




	public static void main(String[] args){
		//get user input to pass their bet amount to the slot. need a welcome message prompting amount
		//need to create rarity values for 777 jackpot and 2x
		//need return method which takes user back to home and sends the moneyLeft value
		SlotMachine s = new SlotMachine("999999");
		s.spin(1);
		boolean again = true;
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object

		while(again) {
			System.out.print("Spin again? (Y/N): ");
			if(myObj.next().equals("N")) {
				again = false;
				System.out.println("Thanks for playing!");
			}
			else {
				System.out.print("Enter bet amount: ");
				s.spin(Double.parseDouble(myObj.next()));
			}
			
		}
	}

}
