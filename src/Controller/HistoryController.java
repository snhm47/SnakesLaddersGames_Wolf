package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import model.History;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import application.Main;

import java.sql.Date;

public class HistoryController {

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private ImageView imgBackg;

	@FXML
	private ImageView titleImg;

	@FXML
	private TableView<History> tableView;

	@FXML
	private void initialize() {
		titleImg.setFitWidth(200);
		titleImg.setFitHeight(150);
		if (StaticController.getInstance().getPageColor().equals("Oceanic Dreams")) {
			Image newImage3 = new Image("Image/ocean.jpg");
			imgBackg.setImage(newImage3);
			Image newImage2 = new Image("Image/DarkHistory.png");
			titleImg.setImage(newImage2);
		}
		if (StaticController.getInstance().getPageColor().equals("Dark Aurora")) {
			Image newImage3 = new Image("Image/DarkBackg.jpg");
			imgBackg.setImage(newImage3);
			Image newImage2 = new Image("Image/HistoryDarkTitle.png");
			titleImg.setImage(newImage2);
		}
		if (StaticController.getInstance().getPageColor().equals("Enchanted Forest")) {
			Image newImage3 = new Image("Image/edjungletheme.jpg");
			imgBackg.setImage(newImage3);
			Image newImage2 = new Image("Image/hestoryForest.png");
			titleImg.setImage(newImage2);
		}
		// tableView.setStyle("-fx-background-color: transparent;");

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
		tableView.getColumns().addAll(winnerColumn, playersColumn, difficultyColumn);

		// Populate table view with data
		loadHistoryFromJson();
	}

	private void loadHistoryFromJson() {
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
//                Date gameDate = Date.valueOf((String) gameObj.get("gameDate"));
				long numberOfPlayers = (long) gameObj.get("numberOfPlayers");
				String difficulty = (String) gameObj.get("difficulty");

				data.add(new History(winnerName, null, 0, (int) numberOfPlayers, difficulty)); // Assuming you're not
																								// storing gameTime in
																								// JSON
			}

			// Set data to table view
			tableView.setItems(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveHistoryToJson(History gameHistory) {
		try {
			// Parse existing JSON file
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader("HistoryJson.json"));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray gamesHistory = (JSONArray) jsonObject.get("gamesHistory");

			// Convert game history object to JSON
			JSONObject gameObj = new JSONObject();
			gameObj.put("winnerName", gameHistory.getWinnerName());
//            gameObj.put("gameDate", gameHistory.getGameDate().toString());
			gameObj.put("numberOfPlayers", gameHistory.getNumberOfPlayers());
			gameObj.put("difficulty", gameHistory.getDifficulty());

			// Add new game history to existing history
			gamesHistory.add(gameObj);

			// Write back to JSON file
			try (FileWriter file = new FileWriter("HistoryJson.json")) {
				file.write(jsonObject.toJSONString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleMuteButtonAction(ActionEvent event) {
		Main.toggleMusic();
	}

	@FXML
	public void returnToMainPage(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/View/StartMenu.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}