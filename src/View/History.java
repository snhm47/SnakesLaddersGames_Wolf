package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

public class History extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JButton btnNewButton;

    /**
     * Create the frame.
     */
    public History() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 920, 675);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblHistory = new JLabel("History");
        lblHistory.setFont(new Font("Tahoma", Font.BOLD, 50));
        lblHistory.setBounds(330, 48, 239, 91);
        contentPane.add(lblHistory);

        // Create the table with columns
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Winner Name");
        model.addColumn("Game Date");
        model.addColumn("Difficulty");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 150, 800, 400);
        contentPane.add(scrollPane);
        
        btnNewButton = new JButton("HOME");
        btnNewButton.setBounds(50, 42, 89, 23);
        contentPane.add(btnNewButton);

        // Load game history data from JSON file
      //  loadGameHistoryFromJson();
    }

//    private void loadGameHistoryFromJson() {
//        JSONParser parser = new JSONParser();
//        try (FileReader reader = new FileReader("HistoryJson.json")) {
//            // Parse JSON file
//            Object obj = parser.parse(reader);
//            JSONArray jsonArray = (JSONArray) obj;
//
//            // Populate the table with game history data
//            DefaultTableModel model = (DefaultTableModel) table.getModel();
//            for (Object o : jsonArray) {
//                JSONObject jsonObject = (JSONObject) o;
//                String winnerName = (String) jsonObject.get("winnerName");
//                Date gameDate = Date.valueOf((String) jsonObject.get("gameDate"));
//                int gameTime = ((Long) jsonObject.get("gameTime")).intValue();
//                int numberOfPlayers = ((Long) jsonObject.get("numberOfPlayers")).intValue();
//                String difficulty = (String) jsonObject.get("difficulty");
//
//                model.addRow(new Object[]{winnerName, gameDate, gameTime, numberOfPlayers, difficulty});
//            }
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        EventQueue.invokeLater(() -> {
//            try {
//                HistoryView frame = new HistoryView();
//                frame.setVisible(true);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }
}
