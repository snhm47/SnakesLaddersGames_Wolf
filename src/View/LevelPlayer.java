package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JTextField;

public class LevelPlayer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNickname;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LevelPlayer frame = new LevelPlayer();
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
	public LevelPlayer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 920, 675);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LEVEL");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNewLabel.setBounds(364, 11, 239, 91);
		contentPane.add(lblNewLabel);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("EASY");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		rdbtnNewRadioButton.setBounds(58, 131, 111, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("HARD");
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		rdbtnNewRadioButton_1.setBounds(58, 267, 111, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("MEDUIM");
		rdbtnNewRadioButton_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		rdbtnNewRadioButton_2.setBounds(58, 198, 203, 23);
		contentPane.add(rdbtnNewRadioButton_2);
		
		JButton btnNext = new JButton("NEXT");
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnNext.setBounds(259, 527, 332, 70);
		contentPane.add(btnNext);
		
		JMenu mnNewMenu = new JMenu("New menu");
		mnNewMenu.setBounds(58, 340, 113, 27);
		contentPane.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("2");
		mnNewMenu.add(mntmNewMenuItem);
		
		JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem("3");
		mnNewMenu.add(chckbxmntmNewCheckItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("4");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		txtNickname = new JTextField();
		txtNickname.setToolTipText("");
		txtNickname.setBounds(111, 411, 96, 20);
		contentPane.add(txtNickname);
		txtNickname.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(319, 411, 96, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(527, 411, 96, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(726, 411, 96, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("History");
		btnNewButton.setBounds(10, 30, 89, 23);
		contentPane.add(btnNewButton);
		ActionListener actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false); 
				History history = new History();
				history.setVisible(true);
			}
		}; 
		
		JButton btnNewButton_1 = new JButton("Question");
		btnNewButton_1.setBounds(132, 30, 89, 23);
		contentPane.add(btnNewButton_1);
		ActionListener actionListener1 = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false); 
		        Question question = new Question();
		        question.setVisible(true);

			}
		};
	}
}
