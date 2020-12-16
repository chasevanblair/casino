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
	public JButton btnPlay, btnRefresh; 
	public double money = 0;
	public JLabel result1 = new JLabel("-"), result2 = new JLabel("-"), result3 = new JLabel("-"), lblNoMoney, lblPay, lblBalance;
	public JTextField txtBetAmt;
	public JTextArea userData, leaderList;
	public JLabel lblUser, lblNoMoneyBJ, lblDealer, lblPlayerHand, lblBalanceBJ;
	public JTextArea txtDealer;
	public JTextArea txtPlayer;
	private JLabel lblBetBJ;
	public JTextField txtBetBJ;
	public JButton btnDeal;
	public JButton btnStand;
	public JButton btnHit;
	public JLabel lblResult;
	
	public HomeGui() {
		setLayout(new BorderLayout(0, 0));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(tabbedPane);
		
		//---------------------HOME-------------------------
		JPanel home = new JPanel();
		tabbedPane.addTab("Home", null, home, null);
		home.setLayout(null);
		

		leaderList = new JTextArea();
		leaderList.setLineWrap(true);
		
		leaderList.setBounds(25, 11, 219, 220);
		home.add(leaderList);
		
		userData = new JTextArea();
		userData.setBounds(266, 47, 169, 184);
		home.add(userData);
		
		lblUser = new JLabel("Your Info:");
		lblUser.setBounds(266, 11, 169, 25);
		home.add(lblUser);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(189, 238, 89, 23);
		home.add(btnRefresh);
		
		JPanel slots = new JPanel();
		tabbedPane.addTab("Slots", null, slots, null);
		slots.setLayout(null);
		
		btnPlay = new JButton("Play");
		btnPlay.setBounds(151, 223, 132, 38);
		slots.add(btnPlay);
		result1.setHorizontalAlignment(SwingConstants.CENTER);
		result1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		//--------------SLOT MACHINE------------------
		result1.setBounds(32, 28, 111, 76);
		slots.add(result1);
		result2.setHorizontalAlignment(SwingConstants.CENTER);
		result2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		result2.setBounds(153, 28, 111, 76);
		slots.add(result2);
		result3.setHorizontalAlignment(SwingConstants.CENTER);
		result3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		result3.setBounds(291, 28, 111, 76);
		slots.add(result3);
		
		JLabel lblBet = new JLabel("Bet amount: ");
		lblBet.setBounds(100, 170, 73, 14);
		slots.add(lblBet);
		
		txtBetAmt = new JTextField();
		txtBetAmt.setBounds(183, 167, 125, 20);
		slots.add(txtBetAmt);
		txtBetAmt.setColumns(10);
		
		lblPay = new JLabel("");
		lblPay.setHorizontalAlignment(SwingConstants.CENTER);
		lblPay.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblPay.setBounds(82, 89, 261, 53);
		slots.add(lblPay);
		
		lblNoMoney = new JLabel("Not enough money to bet this amount");
		lblNoMoney.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoMoney.setBounds(100, 198, 227, 14);
		slots.add(lblNoMoney);
		
		lblBalance = new JLabel("");
		lblBalance.setBounds(100, 145, 227, 14);
		slots.add(lblBalance);
		lblNoMoney.setVisible(false);
		
		//----------------------BLACKJACK--------------------
		JPanel blackJack = new JPanel();
		tabbedPane.addTab("Blackjack", null, blackJack, null);
		blackJack.setLayout(null);
		
		lblDealer = new JLabel("Dealer Hand:");
		lblDealer.setBounds(59, 23, 105, 14);
		blackJack.add(lblDealer);
		
		lblPlayerHand = new JLabel("Your Hand:");
		lblPlayerHand.setBounds(258, 23, 95, 14);
		blackJack.add(lblPlayerHand);
		
		txtDealer = new JTextArea();
		txtDealer.setBounds(39, 48, 125, 92);
		blackJack.add(txtDealer);
		
		txtPlayer = new JTextArea();
		txtPlayer.setBounds(228, 48, 125, 92);
		blackJack.add(txtPlayer);
		
		lblBalanceBJ = new JLabel("");
		lblBalanceBJ.setHorizontalAlignment(SwingConstants.CENTER);
		lblBalanceBJ.setBounds(39, 213, 296, 14);
		blackJack.add(lblBalanceBJ);
		
		lblBetBJ = new JLabel("Bet Amount:");
		lblBetBJ.setBounds(21, 191, 92, 14);
		blackJack.add(lblBetBJ);
		
		txtBetBJ = new JTextField();
		txtBetBJ.setBounds(123, 188, 125, 20);
		blackJack.add(txtBetBJ);
		txtBetBJ.setColumns(10);
		
		btnDeal = new JButton("Deal");
		btnDeal.setBounds(258, 187, 89, 23);
		blackJack.add(btnDeal);
		
		btnStand = new JButton("Stand");
		btnStand.setBounds(39, 238, 89, 23);
		btnStand.setVisible(false);
		blackJack.add(btnStand);
		
		btnHit = new JButton("Hit");
		btnHit.setBounds(246, 238, 89, 23);
		btnHit.setVisible(false);
		blackJack.add(btnHit);
		
		lblResult = new JLabel("");
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setBounds(39, 151, 314, 29);
		blackJack.add(lblResult);
		
		
		
		
		
	}
}
