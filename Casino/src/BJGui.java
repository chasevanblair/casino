import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BJGui extends JPanel {

	/**
	 * Create the panel.
	 */
	public BJGui() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dealer's Hand");
		lblNewLabel.setBounds(58, 45, 135, 167);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Hit");
		btnNewButton.setBounds(186, 467, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Stay");
		btnNewButton_1.setBounds(363, 467, 89, 23);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Deal");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnNewButton_2.setBounds(271, 378, 89, 23);
		add(btnNewButton_2);
		
		JLabel dealerHand = new JLabel("");
		dealerHand.setForeground(Color.BLACK);
		dealerHand.setBackground(Color.WHITE);
		dealerHand.setBounds(301, 90, 215, 76);
		add(dealerHand);
		
		JLabel dealerLabel = new JLabel("Player's Hand");
		dealerLabel.setBounds(58, 209, 82, 14);
		add(dealerLabel);
		
		JLabel playerHand = new JLabel("");
		playerHand.setBounds(301, 209, 46, 14);
		add(playerHand);
		
	}
}
