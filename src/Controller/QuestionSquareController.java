package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

    private ToggleGroup rbans;

    private SysData sysData;

    @FXML
    void initialize() {
    	rbans = new ToggleGroup();
    	rbans1.setToggleGroup(rbans);
    	rbans2.setToggleGroup(rbans);
    	rbans3.setToggleGroup(rbans);
    	rbans4.setToggleGroup(rbans);

        sysData = new SysData("src/WolfQuestionsDB.json");
        loadQuestions();
    }

    private void loadQuestions() {
        try {
            Question question = sysData.getRandomQuestion(sysData.loadDataFromJSON(sysData.getFilePath()));
            if (question != null) {
                questionText.setText(question.getText());
            	rbans1.setText(question.getAnswers().get(0));
            	rbans2.setText(question.getAnswers().get(1));
            	rbans3.setText(question.getAnswers().get(2));
            	rbans4.setText(question.getAnswers().get(3));

                rbans.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
                    if (rbans.getSelectedToggle() != null) {
                        int selectedIndex = rbans.getToggles().indexOf(rbans.getSelectedToggle());
                        if (selectedIndex == Integer.parseInt(question.getCorrectAnswer()) - 1) {
                            showAlert("Correct Answer!", "You have chosen the correct answer.");
                        } else {
                            showAlert("Incorrect Answer!", "You have chosen the incorrect answer.");
                        }
                        // Close the window after showing the message
                        Stage stage = (Stage) rbans1.getScene().getWindow();
                    }
                });
            }else {
                showAlert("Error", "No question found.");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to load questions.");
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
