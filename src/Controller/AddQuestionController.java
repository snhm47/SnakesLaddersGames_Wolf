package Controller;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AddQuestionController implements Initializable {

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private RadioButton rbuttoneasy, rbuttonMedium, rbuttonHard;

	@FXML
	private TextField t1, t2, t3, t4, qTf;

	@FXML
	private ChoiceBox<String> answersList;
	@FXML
	private ImageView imgBackg;

	@FXML
	private ImageView titleImg;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String[] answers = { t1.getText(), t2.getText(), t3.getText(), t4.getText() };
		answersList.getItems().addAll(answers);
		answersList.setOnAction(this::getRightAnswer);
		    	titleImg.setFitWidth(200); 
		        titleImg.setFitHeight(150);
		    	if(StaticController.getInstance().getPageColor().equals("Oceanic Dreams")) {
		    		Image newImage3 = new Image("Image/ocean.jpg");
		    		imgBackg.setImage(newImage3);
		    		Image newImage2 = new Image("Image/ocean_addQuestion.png");
		            titleImg.setImage(newImage2);
		            }
		    	if(StaticController.getInstance().getPageColor().equals("Dark Aurora")) {
		    		Image newImage3 = new Image("Image/DarkBackg.jpg");
		    		imgBackg.setImage(newImage3);
		    		Image newImage2 = new Image("Image/DarkAddQuestion.png");
		            titleImg.setImage(newImage2);
		    	}
		    	if(StaticController.getInstance().getPageColor().equals("Enchanted Forest")) {
		    		Image newImage3 = new Image("Image/edjungletheme.jpg");
		    		imgBackg.setImage(newImage3);
		    		Image newImage2 = new Image("Image/Forest_AddQuestion.png");
		            titleImg.setImage(newImage2);
		    }
	}

	public void getRightAnswer(ActionEvent event) {
		String rightAnswer = answersList.getValue();
		// return rightAnswer;
	}

	@FXML
	public void returnToMainPage(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/View/StartMenu.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void getLevel(ActionEvent event) {

		if (rbuttoneasy.isSelected())
			System.out.println("easy");
		else if (rbuttonMedium.isSelected())
			System.out.println("medium");
		else
			System.out.println("hard");
	}

	@FXML
	public void switchToQuestionPage(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../View/QuestionsPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	private void addAnswer(ActionEvent event) {
		String answer1 = t1.getText().trim();
		String answer2 = t2.getText().trim();
		String answer3 = t3.getText().trim();
		String answer4 = t4.getText().trim();

		// Clear existing items in the choice box
		answersList.getItems().clear();

		// Add answers to the choice box
		answersList.getItems().addAll(answer1, answer2, answer3, answer4);
	}

	@FXML
	private void resetAddQs(ActionEvent event) {
		// Clear all text fields
		qTf.clear();
		t1.clear();
		t2.clear();
		t3.clear();
		t4.clear();

		rbuttoneasy.setSelected(false);
		rbuttonMedium.setSelected(false);
		rbuttonHard.setSelected(false);

		// Clear choice box
		answersList.getItems().clear();

	}

	@FXML
	public void addQ(ActionEvent event) throws ParseException, org.json.simple.parser.ParseException {
		try {
			// Read existing JSON file
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader("WolfQuestionsDB.json"));
			JSONObject jsonObject = (JSONObject) obj;

			// Get the array of questions
			JSONArray questionsArray = (JSONArray) jsonObject.get("questions");

			// Create a new question object
			JSONObject newQuestion = new JSONObject();
			newQuestion.put("question", qTf.getText());

			JSONArray answersArray = new JSONArray();
			answersArray.add(t1.getText());
			answersArray.add(t2.getText());
			answersArray.add(t3.getText());
			answersArray.add(t4.getText());
			newQuestion.put("answers", answersArray);

			// Extract the selected correct answer from the choice box
			String selectedCorrectAnswer = answersList.getValue();

			// Map the selected correct answer to its index (0, 1, 2, 3)
			int correctAnswerIndex = -1; // Default value if not found
			for (int i = 0; i < answersArray.size(); i++) {
				if (answersArray.get(i).equals(selectedCorrectAnswer)) {
					correctAnswerIndex = i;
					break;
				}
			}

			// Add the correct answer index to the question object
			newQuestion.put("correct_ans", String.valueOf(correctAnswerIndex));

			// Extract the selected difficulty from the radio buttons
			String selectedDifficulty = "1"; // Default to easy
			if (rbuttoneasy.isSelected()) {
				selectedDifficulty = "1"; // Easy
			} else if (rbuttonMedium.isSelected()) {
				selectedDifficulty = "2"; // Medium
			} else if (rbuttonHard.isSelected()) {
				selectedDifficulty = "3"; // Hard
			}

			// Add the selected difficulty to the question object
			newQuestion.put("difficulty", selectedDifficulty);

			// Check if the question already exists
			String newQuestionText = qTf.getText();
			for (Object questionObj : questionsArray) {
				JSONObject question = (JSONObject) questionObj;
				String existingQuestionText = (String) question.get("question");
				if (existingQuestionText.equals(newQuestionText)) {
					// Show a pop-up message indicating the question already exists
					showAlert("Question Already Exists", "The question already exists in the database.",
							Alert.AlertType.WARNING);
					return; // Exit the method
				}
			}

			// Add the new question to the array
			questionsArray.add(newQuestion);

			// Update the JSON object with the modified questions array
			jsonObject.put("questions", questionsArray);

			// Write the updated JSON back to the file
			try (FileWriter file = new FileWriter("WolfQuestionsDB.json")) {
				// Write the JSON object with formatting
				file.write("{\n");
				file.write("    \"questions\": [\n");
				for (int i = 0; i < questionsArray.size(); i++) {
					JSONObject question = (JSONObject) questionsArray.get(i);
					file.write("        " + question.toJSONString());
					if (i < questionsArray.size() - 1) {
						file.write(",");
					}
					file.write("\n");
				}
				file.write("    ]\n");
				file.write("}\n");
				file.flush();
				System.out.println("Question stored successfully!");
				showAlert("Success", "Question added successfully!", Alert.AlertType.INFORMATION);
				// Return to the question page
				switchToQuestionPage(event);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error storing question!");
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error reading JSON file!");
		}
	}

	private void showAlert(String title, String message, Alert.AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private void switchToQuestionPage(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/QuestionsPage.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}