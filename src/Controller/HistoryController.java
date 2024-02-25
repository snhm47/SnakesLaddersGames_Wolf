package Controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.History;

//import org.json.JSONArray;
//import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class HistoryController {

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private TableView<History> tableView;

	@FXML
	private TableColumn<History, String> winnerColumn;

	@FXML
	private TableColumn<History, String> dateColumn;

	@FXML
	private TableColumn<History, Integer> playersColumn;

	@FXML
	private TableColumn<History, String> difficultyColumn;

	// edwar
	// return to Main Page
	@FXML
	public void returnToMainPage(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/View/StartMenu.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
//
//	public static List<History> readHistory(String filePath) {
//		try {
//			String content = new String(Files.readAllBytes(Paths.get(filePath)));
//			JSONArray jsonArray = new JSONArray(content);
//			List<History> historyList = new ArrayList<>();
//			for (int i = 0; i < jsonArray.length(); i++) {
//				JSONObject jsonObject = jsonArray.getJSONObject(i);
//				String winnerName = jsonObject.getString("winnerName");
//				String gameDate = jsonObject.getString("gameDate");
//				// int gameTime = jsonObject.getInt("gameTime");
//				int numberOfPlayers = jsonObject.getInt("numberOfPlayers");
//				String difficulty = jsonObject.getString("difficulty");
//				History history = new History(winnerName, gameDate, numberOfPlayers, difficulty);
//				historyList.add(history);
//			}
//			return historyList;
//		} catch (IOException | JSONException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	public void initialize() {
//		winnerColumn.setCellValueFactory(new PropertyValueFactory<>("winnerName"));
//		dateColumn.setCellValueFactory(new PropertyValueFactory<>("gameDate"));
//		playersColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfPlayers"));
//		difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
//
//		// Assuming you have a method readHistory(String filePath) in HistoryReader
//		// class
//		List<History> historyList = HistoryController.readHistory("../HistoryJson.json");
//		tableView.getItems().addAll(historyList);
//	}

}
