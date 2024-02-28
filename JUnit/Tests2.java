//package JUnit;
//
//import model.Board;
//import model.Player;
//
//import static org.junit.Assert.assertNotNull;
//
//import org.junit.Test;
//import Utils.Color;
//import Utils.DiffLevel;
//
//class Tests2 {
//
//	@Test
//	public void testSaveGameHistory() throws IOException, ParseException {
//		History gameHistory = new History("Player1", Date.valueOf("2024-02-25"), 0, 3, "Easy");
//
//		historyController.saveGameHistory(gameHistory);
//		assertTrue("JSON file should have been created", Files.exists(Paths.get(TEST_JSON_FILE)));
//		JSONArray gamesHistory = readJsonArrayFromFile(TEST_JSON_FILE);
//		assertEquals("Only one game history should be saved", 1, gamesHistory.size());
//		JSONObject savedGameHistory = (JSONObject) gamesHistory.get(0);
//		assertEquals("Winner name should match", "Player1", savedGameHistory.get("winnerName"));
//		assertEquals("Game date should match", "2024-02-25", savedGameHistory.get("gameDate"));
//		assertEquals("Number of players should match", 3L, savedGameHistory.get("numberOfPlayers"));
//		assertEquals("Difficulty should match", "Easy", savedGameHistory.get("difficulty"));
//	}
//
//}
package JUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import Controller.HistoryController;
import Controller.QuestionsquareController;
import Utils.DiffLevel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.History;
import model.Question;

public class Tests2 {
    private static final String TEST_JSON_FILE = "test.json"; // Provide a path for the test JSON file

    @Test
    public void testSaveGameHistory() throws IOException, ParseException {
        HistoryController historyController = new HistoryController(); // Instantiate HistoryController
        History gameHistory = new History("Player1", Date.valueOf("2024-02-25"), 0, 3, "Easy");

        historyController.saveGameHistory(gameHistory);
        assertTrue("JSON file should have been created", Files.exists(Paths.get(TEST_JSON_FILE)));
        JSONArray gamesHistory = readJsonArrayFromFile(TEST_JSON_FILE); // Implement this method
        assertEquals("Only one game history should be saved", 1, gamesHistory.size());
        JSONObject savedGameHistory = (JSONObject) gamesHistory.get(0);
        assertEquals("Winner name should match", "Player1", savedGameHistory.get("winnerName"));
        assertEquals("Game date should match", "2024-02-25", savedGameHistory.get("gameDate"));
        assertEquals("Number of players should match", 3L, savedGameHistory.get("numberOfPlayers"));
        assertEquals("Difficulty should match", "Easy", savedGameHistory.get("difficulty"));
    }

    private JSONArray readJsonArrayFromFile(String filePath) throws IOException, ParseException {
        return null; // Placeholder
    }
    
    //test2
    @Test
    public void testquestionsquare(Stage primaryStage) throws Exception {
        // Load the FXML file
    	  String questionText = "What is the capital of France?";
    	  List<String> answers = Arrays.asList("London", "Berlin", "Paris", "Madrid");
          String correctAnswer = "Paris";
          DiffLevel difficulty = DiffLevel.easy;
          Question sampleQuestion = new Question(questionText, answers, correctAnswer, difficulty);

          // Load the FXML file
          FXMLLoader loader = new FXMLLoader(getClass().getResource("Questionsquare.fxml"));
          Parent root = loader.load();

          // Get the controller
          QuestionsquareController controller = loader.getController();

          // Set the sample question in the controller
          controller.setQuestion(sampleQuestion);

          // Create a scene with the parent node
          Scene scene = new Scene(root);

          // Set the scene and show the stage
          primaryStage.setScene(scene);
          primaryStage.show();
      }
}
