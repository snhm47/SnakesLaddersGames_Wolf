package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Question;

public class QuestionSquareController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private RadioButton rbans1;

	@FXML
	private RadioButton rbans2;

	@FXML
	private RadioButton rbans3;

	@FXML
	private RadioButton rbans4;

	@FXML
	private TextField questionText;

	@FXML
	private Button answerButton;

	private ToggleGroup rbans;

	private SysData sysData;
	
    private Question question; // Add this field


	@FXML
	void initialize() {
		rbans = new ToggleGroup();
		rbans1.setToggleGroup(rbans);
		rbans2.setToggleGroup(rbans);
		rbans3.setToggleGroup(rbans);
		rbans4.setToggleGroup(rbans);

		sysData = new SysData("src/WolfQuestionsDB.json");
		loadQuestions();
		answerButton.setOnAction(event -> checkAnswer());
	}
	private void loadQuestions() {
        try {
            question = sysData.getRandomQuestion(sysData.loadDataFromJSON(sysData.getFilePath()));
            if (question != null) {
                questionText.setText(question.getText());
                rbans1.setText(question.getAnswers().get(0));
                rbans2.setText(question.getAnswers().get(1));
                rbans3.setText(question.getAnswers().get(2));
                rbans4.setText(question.getAnswers().get(3));
                
            } else {
                showAlert("Error", "No question found.");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to load questions.");
            e.printStackTrace();
        }
    }

    private void checkAnswer() {
        if (question != null) {
            int selectedIndex = rbans.getToggles().indexOf(rbans.getSelectedToggle());
            int correctIndex = Integer.parseInt(question.getCorrectAnswer()) - 1;

            String selectedAnswer = question.getAnswers().get(selectedIndex);
            String correctAnswer = question.getAnswers().get(correctIndex);

            if (selectedIndex == correctIndex) {
                showAlert("Correct Answer!", "You have chosen the correct answer: " + selectedAnswer);
            } else {
                showAlert("Incorrect Answer!", "The correct answer is: " + correctAnswer + "\nYou chose: " + selectedAnswer);
            }

            // Close the window after showing the message
            Stage stage = (Stage) answerButton.getScene().getWindow();
            stage.close();
        } else {
            showAlert("Error", "No question found.");
        }
    }

	private void showAlert(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();
	}
}