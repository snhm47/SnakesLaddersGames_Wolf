package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Checkbox;
import javax.swing.JButton;

public class CreateGame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateGame frame = new CreateGame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreateGame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 379);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Choose level:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(68, 86, 131, 36);
		contentPane.add(lblNewLabel);
		
		Checkbox checkbox = new Checkbox("Easy");
		checkbox.setBounds(211, 89, 89, 25);
		contentPane.add(checkbox);
		
		Checkbox checkbox_1 = new Checkbox("Medium");
		checkbox_1.setBounds(211, 128, 89, 18);
		contentPane.add(checkbox_1);
		
		Checkbox checkbox_2 = new Checkbox("Hard");
		checkbox_2.setBounds(211, 159, 89, 18);
		contentPane.add(checkbox_2);
		
		JButton btnNewButton = new JButton("New Game");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(211, 234, 112, 21);
		contentPane.add(btnNewButton);
	}
}
