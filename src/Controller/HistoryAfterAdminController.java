package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import model.History;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.sql.Date;

public class HistoryAfterAdminController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private TableView<History> tableView;

    @SuppressWarnings("unchecked")
	@FXML
    private void initialize() {
        // Define columns
        TableColumn<History, String> winnerColumn = new TableColumn<>("Winner");
        winnerColumn.setCellValueFactory(new PropertyValueFactory<>("winnerName"));
        winnerColumn.setPrefWidth(150); // Set preferred width for the Winner column

        TableColumn<History, Date> dateColumn = new TableColumn<>("Game Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("gameDate"));
        dateColumn.setPrefWidth(150); // Set preferred width for the Game Date column

        TableColumn<History, Integer> playersColumn = new TableColumn<>("Players");
        playersColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfPlayers"));
        playersColumn.setPrefWidth(100); // Set preferred width for the Players column

        TableColumn<History, String> difficultyColumn = new TableColumn<>("Difficulty");
        difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
        difficultyColumn.setPrefWidth(100); // Set preferred width for the Difficulty column

        // Add columns to table view
        tableView.getColumns().addAll(winnerColumn, dateColumn, playersColumn, difficultyColumn);

        // Populate table view with data
        try {
            // Parse JSON file
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("HistoryJson.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray gamesHistory = (JSONArray) jsonObject.get("gamesHistory");

            // Convert JSON data to ObservableList<History>
            ObservableList<History> data = FXCollections.observableArrayList();
            for (Object game : gamesHistory) {
                JSONObject gameObj = (JSONObject) game;
                String winnerName = (String) gameObj.get("winnerName");
                Date gameDate = Date.valueOf((String) gameObj.get("gameDate"));
                long numberOfPlayers = (long) gameObj.get("numberOfPlayers");
                String difficulty = (String) gameObj.get("difficulty");

                data.add(new History(winnerName, gameDate, 0, (int) numberOfPlayers, difficulty)); // Assuming you're not storing gameTime in JSON
            }

            // Set data to table view
            tableView.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    


	@FXML
	public void returnToMainPage(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../View/StartMenuAfterAdmin.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}