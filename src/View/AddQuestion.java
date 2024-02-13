package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddQuestion extends JInternalFrame {

    private JTextField questionField;
    private JTextField answer1Field;
    private JTextField answer2Field;
    private JTextField answer3Field;
    private JTextField answer4Field;
    private JTextField difficultyField;
    private JTextField correctAnswerField;

    /**
     * Create the frame.
     */
    public AddQuestion() {
        setTitle("Add Question");
        setClosable(true);
        setBounds(100, 100, 920, 675);
        getContentPane().setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(7, 2, 5, 5));

        JLabel lblQuestion = new JLabel("Question:");
        lblQuestion.setFont(new Font("Tahoma", Font.BOLD, 30));
        panel.add(lblQuestion);

        questionField = new JTextField();
        panel.add(questionField);
        questionField.setColumns(10);

        JLabel lblAnswer1 = new JLabel("Answer 1:");
        lblAnswer1.setFont(new Font("Tahoma", Font.BOLD, 30));
        panel.add(lblAnswer1);

        answer1Field = new JTextField();
        panel.add(answer1Field);
        answer1Field.setColumns(10);

        JLabel lblAnswer2 = new JLabel("Answer 2:");
        lblAnswer2.setFont(new Font("Tahoma", Font.BOLD, 30));
        panel.add(lblAnswer2);

        answer2Field = new JTextField();
        panel.add(answer2Field);
        answer2Field.setColumns(10);

        JLabel lblAnswer3 = new JLabel("Answer 3:");
        lblAnswer3.setFont(new Font("Tahoma", Font.BOLD, 30));
        panel.add(lblAnswer3);

        answer3Field = new JTextField();
        panel.add(answer3Field);
        answer3Field.setColumns(10);

        JLabel lblAnswer4 = new JLabel("Answer 4:");
        lblAnswer4.setFont(new Font("Tahoma", Font.BOLD, 30));
        panel.add(lblAnswer4);

        answer4Field = new JTextField();
        panel.add(answer4Field);
        answer4Field.setColumns(10);

        JLabel lblDifficulty = new JLabel("Difficulty:");
        lblDifficulty.setFont(new Font("Tahoma", Font.BOLD, 30));
        panel.add(lblDifficulty);

        difficultyField = new JTextField();
        panel.add(difficultyField);
        difficultyField.setColumns(10);

        JLabel lblCorrectAnswer = new JLabel("Correct Answer:");
        lblCorrectAnswer.setFont(new Font("Tahoma", Font.BOLD, 30));
        panel.add(lblCorrectAnswer);

        correctAnswerField = new JTextField();
        panel.add(correctAnswerField);
        correctAnswerField.setColumns(10);

        JPanel buttonPanel = new JPanel();
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        JButton addButton = new JButton("Add");
        addButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        buttonPanel.add(addButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        buttonPanel.add(cancelButton);

        // Add action listener for the Add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AddQuestion.this, "Question added successfully!");
                dispose();
				setVisible(false); 
                Question allQuestions = new Question();
                allQuestions.setVisible(true);
            }
        });

        // Add action listener for the Cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AddQuestion.this, "Question Canceled successfully!");
                dispose();
				setVisible(false); 
                Question allQuestions = new Question();
                allQuestions.setVisible(true);

            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddQuestion frame = new AddQuestion();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
