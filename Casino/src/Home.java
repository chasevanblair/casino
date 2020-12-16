import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Home {
	public ArrayList<Person> gamblers = new ArrayList();
	public Person gambler;
	public double balance;
	Scanner in = new Scanner(System.in);
	private JFrame frame;
	private JPanel panel;
	private HomeGui g;
	
	public Home(String name, double baseBalance) {
		
		
		gambler = new Person(name, baseBalance);
		gamblers.add(gambler);
		gamblers.add(new Person("jeff", 32223.21));
		gamblers.add(new Person("earl", 30.29));
		gamblers.add(new Person("regina", 92));
		gamblers.add(new Person("george", 4342));
		gamblers.add(new Person("sebastian", 0));
		
		
	}
	
	//main runs of each game return balance made/lost for each session
	public void playBlackjack() {
		BlackJack b = new BlackJack(gambler.getBalance());
		balance = b.run();
		
	}
	
	public void playSlots() {
		SlotMachine s = new SlotMachine(gambler.getBalance(), g);
		gambler.setBalance(s.run());
		
	}
	
	public void sortLeader() {
		/*leaderSorted.add(gamblers.get(0));
		gamblers.remove(0);
		for(int i = 0; i<gamblers.size(); i++) {
			
		}*/
		
		int i, j;

	    for (i = 1; i < gamblers.size(); i++) {
	        Person key = new Person("", 0);
	        key.setBalance(gamblers.get(i).getBalance());
	        key.setName(gamblers.get(i).getName());
	        j = i;
	        while((j > 0) && (gamblers.get(j - 1).getBalance() < key.getBalance())) {
	            gamblers.set(j,gamblers.get(j - 1));
	            j--;
	        }
	        gamblers.set(j,key);
	    }
	}
	
	public void drawHome() {
		frame = new JFrame();
		//panel = new JPanel();
		//panel.setBorder(BorderFactory.createEmptyBorder(50,50,10,10));
		/*panel.setLayout(new GridLayout(0,1));
		JButton button = new JButton("testssssssssssssssss");
		//button
		JLabel leader = new JLabel("Money Leaderboard");
		panel.add(leader);
		System.out.println("Leaderboard: ");*/
		ArrayList<String> leaderboard = new ArrayList<String>();
		sortLeader();

		for(int i = 1; i <= gamblers.size(); i++) {
			leaderboard.add(i + ": " + gamblers.get(i-1).toString());
			
		}

		this.g = new HomeGui(leaderboard);
		g.money = balance;
		g.btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSlots();
            }

        });

		
		frame.add(g,BorderLayout.CENTER);
		frame.setSize(500,500);
		frame.setVisible(true);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Casino");
		frame.setVisible(true);
		
		
		
		
		
	}
	
	public void start() {
		drawHome();
		System.out.println("Enter (1) for blackjack, (2) for slot machine: ");
		boolean again = true;
		while (again) {
		if(in.nextLine().equals("1")) {
			playBlackjack();
		}
		else {
			playSlots();
		}
		System.out.println("return to home (1) or quit(2): ");
		if(in.nextLine().equalsIgnoreCase("1")) {
			start();
		}
		//else {
			//System.out.println("Returning to home.");
			//start();
		//}
		}
		
	}
	
	public static void main(String[] args) {
		/*Scanner in = new Scanner(System.in);

		System.out.println("Enter name: ");
		String name = in.nextLine();
		System.out.println("Enter balance: ");
		double balance = Double.parseDouble(in.nextLine());*/
		String name = "";
		double balance = 0;
		
		 JTextField xField = new JTextField(10);
	      JTextField yField = new JTextField(10);

	      JPanel myPanel = new JPanel();
	      myPanel.add(new JLabel("Name:"));
	      myPanel.add(xField);
	      myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	      myPanel.add(new JLabel("Wallet balance:"));
	      myPanel.add(yField);

	      int result = JOptionPane.showConfirmDialog(null, myPanel, "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
	      if (result == JOptionPane.OK_OPTION) {
	    	  name = xField.getText();
	    	  balance = Double.parseDouble(yField.getText());

	      }else if(result == JOptionPane.CANCEL_OPTION) {
	    	  System.exit(0);
	      }
		Home h = new Home(name, balance);
		h.start();
		//in.close();		
		h.in.close();
		
	}
}


