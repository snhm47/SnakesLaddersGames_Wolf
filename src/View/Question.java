package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Question extends JFrame {
    private JTable table;
    private JButton btnNewButton;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Question frame = new Question();
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
	  public Question() {
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 920, 675);
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	        setContentPane(contentPane);
	        contentPane.setLayout(null);

	        JLabel lblHistory = new JLabel("QUESTION");
	        lblHistory.setFont(new Font("Tahoma", Font.BOLD, 50));
	        lblHistory.setBounds(278, 42, 352, 91);
	        contentPane.add(lblHistory);

	        // Create the table with columns
	        DefaultTableModel model = new DefaultTableModel();
	        model.addColumn("QUESTION");
	        model.addColumn("ANSWER 1");
	        model.addColumn("ANSWER 2");
	        model.addColumn("ANSWER 3");
	        model.addColumn("ANSWER 4");
	        model.addColumn("CORRECT ANSWER");


	        table = new JTable(model);
	        JScrollPane scrollPane = new JScrollPane(table);
	        scrollPane.setBounds(50, 150, 800, 400);
	        contentPane.add(scrollPane);
	        
	        btnNewButton = new JButton("HOME");
	        btnNewButton.setBounds(50, 42, 89, 23);
	        contentPane.add(btnNewButton);
	        ActionListener actionListener = new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					setVisible(false); 
			        LevelPlayer levelScreen = new LevelPlayer();
			        levelScreen.setVisible(true);

				}
			};

	        // Load game history data from JSON file
	      //  loadGameHistoryFromJson();
	    }
	

}
