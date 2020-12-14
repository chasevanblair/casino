import java.util.ArrayList;
import java.util.Scanner;

public class Home {
	public ArrayList<Person> leaderboard = new ArrayList();
	public Person gambler = new Person("user", 0);
	
	
	public Home() {
		leaderboard.add(new Person("jeff", 32223.21));
		leaderboard.add(new Person("earl", 30.29));
		leaderboard.add(new Person("regina", 92));
		leaderboard.add(new Person("george", 4342));
		leaderboard.add(new Person("sebastian", 0));
	}
	
	public void playBlackjack() {
		BlackJack b = new BlackJack(gambler.getBalance());
		gambler.setBalance(gambler.getBalance() + b.run());
		
	}
	
	public void playSlots() {
		SlotMachine s = new SlotMachine(gambler.getBalance());
		gambler.setBalance(gambler.getBalance() + s.run());
		
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Home h = new Home();
		
		System.out.println("Enter (1) for blackjack, (2) for slot machine: ");
		if(in.nextLine().equals("1")) {
			h.playBlackjack();
		}
		else {
			h.playSlots();
		}
		
		
	}
}


