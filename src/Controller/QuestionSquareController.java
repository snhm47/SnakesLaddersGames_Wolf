package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Question;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Utils.DiffLevel;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuestionSquareController {
	@FXML
	private AnchorPane questionPane;

	@FXML
	private Label questionLabel;

	@FXML
	private RadioButton option1;

	@FXML
	private RadioButton option2;

	@FXML
	private RadioButton option3;

	@FXML
	private RadioButton option4;

	@FXML
	private ToggleGroup optionsGroup;

	@FXML
	private Button submitButton;

	private List<Question> questions;
	private Random random;
	private int currentPlayerPosition;

	@FXML
	public void initialize() {
		random = new Random();
		currentPlayerPosition = 0;
		loadQuestionsFromJSON("WolfQuestionDB.json");
		displayRandomQuestion();
	}

	private void loadQuestionsFromJSON(String filePath) {
		questions = new ArrayList<>();
		JSONParser parser = new JSONParser();
		try (FileReader reader = new FileReader(filePath)) {
			Object obj = parser.parse(reader);
			JSONArray questionArray = (JSONArray) obj;
			for (Object questionObj : questionArray) {
				JSONObject questionJson = (JSONObject) questionObj;
				String questionText = (String) questionJson.get("question");
				List<String> answers = (List<String>) questionJson.get("answers");
				String correctAnswer = (String) questionJson.get("correct_ans");
				DiffLevel difficulty = (DiffLevel) questionJson.get("difficulty");
				questions.add(new Question(questionText, answers, correctAnswer, difficulty));
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	private void displayRandomQuestion() {
		if (questions != null && !questions.isEmpty()) {
			int randomIndex = random.nextInt(questions.size());
			Question randomQuestion = questions.get(randomIndex);
			questionLabel.setText(randomQuestion.getText());
			List<String> answers = new ArrayList<>(randomQuestion.getAnswers());
			Collections.shuffle(answers);
			option1.setText(answers.get(0));
			option2.setText(answers.get(1));
			option3.setText(answers.get(2));
			option4.setText(answers.get(3));
		}
	}

	@FXML
	private void handleSubmit() {
		RadioButton selectedOption = (RadioButton) optionsGroup.getSelectedToggle();
		if (selectedOption != null) {
			String selectedAnswer = selectedOption.getText();
			if (checkAnswer(selectedAnswer)) {
				handleCorrectAnswer();
			} else {
				handleWrongAnswer();
			}
		}
	}

	private boolean checkAnswer(String selectedAnswer) {
		Question currentQuestion = getCurrentQuestion();
		return selectedAnswer.equals(currentQuestion.getCorrectAnswer());
	}

	private Question getCurrentQuestion() {
		String currentQuestionText = questionLabel.getText();
		return questions.stream().filter(question -> question.getText().equals(currentQuestionText)).findFirst()
				.orElse(null);
	}

	private void handleCorrectAnswer() {
		Question currentQuestion = getCurrentQuestion();
		DiffLevel difficulty = currentQuestion.getDifficulty();
		String congratsMessage;
		switch (difficulty) {
		case easy:
			congratsMessage = "Congratulations!";
			break;
		case medium:
			congratsMessage = "Congratulations!";
			break;
		case hard:
			congratsMessage = "Congratulations! You can move 1 square forward.";
			currentPlayerPosition += 1;
			break;
		default:
			congratsMessage = "Congratulations!";
			break;
		}
		showCongratsPopup(congratsMessage);

		displayRandomQuestion();
	}

	

	private void handleWrongAnswer() {
		Question currentQuestion = getCurrentQuestion();
		DiffLevel difficulty = currentQuestion.getDifficulty();

		// Display appropriate message and adjust player position
		String wrongMessage;
		int squaresToMoveBack;
		switch (difficulty) {
		case easy:
			wrongMessage = "Oops! You answered wrong.";
			squaresToMoveBack = 1;
			break;
		case medium:
			wrongMessage = "Oops! You answered wrong.";
			squaresToMoveBack = 2;
			break;
		case hard:
			wrongMessage = "Oops! You answered wrong.";
			squaresToMoveBack = 3;
			break;
		default:
			wrongMessage = "Oops! You answered wrong.";
			squaresToMoveBack = 1; // Default to moving back 1 square
			break;
		}
		showWrongPopup(wrongMessage);

		currentPlayerPosition -= squaresToMoveBack;
		displayRandomQuestion();
	}

	// here according to the level we call the pop up window 
	// so user can know how steps he move forward or back
	private void showCongratsPopup(String message) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/popup.fxml"));
			Parent root = loader.load();

			// Get the controller
			CongratsPopupController controller = loader.getController();
			controller.setMessage(message);

			// Create the stage
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void showWrongPopup(String message) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/popup.fxml"));
			Parent root = loader.load();

			// Get the controller
			WrongPopupController controller = loader.getController();
			controller.setMessage(message);

			// Create the stage
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
