import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

public class HomeGui extends JPanel {

	/**
	 * Create the panel.
	 */
	public JButton btnPlay; 
	public double money = 0;
	public JLabel result1 = new JLabel("-"), result2 = new JLabel("-"), result3 = new JLabel("-");
	private JTextField txtBetAmt;
	
	public HomeGui(ArrayList<String> leaderboard) {
		setLayout(new BorderLayout(0, 0));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(tabbedPane);
		
		JPanel home = new JPanel();
		tabbedPane.addTab("Home", null, home, null);
		home.setLayout(null);
		

		JTextArea leaderList = new JTextArea();
		leaderList.setLineWrap(true);
		String ret = "";
		for(String e : leaderboard) {
			ret += e + "\n";
		}
		leaderList.setText(ret);
		leaderList.setBounds(25, 11, 219, 235);
		home.add(leaderList);
		
		JTextArea userData = new JTextArea();
		userData.setBounds(266, 11, 169, 235);
		home.add(userData);
		
		JPanel slots = new JPanel();
		tabbedPane.addTab("Slots", null, slots, null);
		slots.setLayout(null);
		
		btnPlay = new JButton("play");
		btnPlay.setBounds(192, 238, 53, 23);
		slots.add(btnPlay);
		result1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
//		result1.setText(text);;
		result1.setBounds(56, 48, 85, 33);
		slots.add(result1);
		result2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		//result2 = new JLabel("New label");
		result2.setBounds(171, 48, 74, 33);
		slots.add(result2);
		result3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		//result3 = new JLabel("New label");
		result3.setBounds(289, 48, 74, 33);
		slots.add(result3);
		
		JLabel lblBet = new JLabel("Bet amount: ");
		lblBet.setBounds(100, 195, 73, 14);
		slots.add(lblBet);
		
		txtBetAmt = new JTextField();
		txtBetAmt.setBounds(202, 192, 125, 20);
		slots.add(txtBetAmt);
		txtBetAmt.setColumns(10);
		
		JLabel lblPay = new JLabel("New label");
		lblPay.setHorizontalAlignment(SwingConstants.CENTER);
		lblPay.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblPay.setBounds(66, 92, 261, 53);
		slots.add(lblPay);
		
		JPanel Blackjack = new JPanel();
		tabbedPane.addTab("Blackjack", null, Blackjack, null);
		
		
	}
}
