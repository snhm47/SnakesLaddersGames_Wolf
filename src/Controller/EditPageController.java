package Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Question;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class EditPageController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;

    private SysData sysData;

    @FXML
    private ChoiceBox<String> questionChoiceBox;

    @FXML
    private TextField editedQuestionTextField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Text feedbackText;
    
	
    private void setFullscreen() {
        stage.setResizable(false);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
    }

    public EditPageController() {
        this.sysData = new SysData("src/WolfQuestionsDB.json");
    }

    public void initialize() {
        // Populate ChoiceBox with questions from JSON file
        ObservableList<String> questions = sysData.loadQuestionsFromJSON("src/WolfQuestionsDB.json");
        questionChoiceBox.setItems(questions);
    }

    @FXML
    private void saveEditedQuestion() {
        // Get the edited question
        String editedQuestion = editedQuestionTextField.getText();

        // Get the selected original question to be replaced
        String originalQuestion = questionChoiceBox.getValue();

        // Load all questions and their details from JSON
        List<Question> allQuestions = sysData.loadDataFromJSON("src/WolfQuestionsDB.json");

        // Find the index of the original question
        int index = -1;
        for (int i = 0; i < allQuestions.size(); i++) {
            if (allQuestions.get(i).getText().equals(originalQuestion)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            // Replace the original question with the edited one
        	allQuestions.get(index).setText(editedQuestion);

            // Write the updated questions back to the JSON file
            writeQuestionsToJSON(allQuestions, "src/WolfQuestionsDB.json");
        }

        // Clear text field
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null); 
        alert.setContentText("Question Edited successfully");
        alert.setGraphic(null); 
        alert.showAndWait();
        editedQuestionTextField.clear();
    }

    private void writeQuestionsToJSON(List<Question> questions, String filePath) {
        try (FileWriter file = new FileWriter(filePath)) {
            JSONObject json = new JSONObject();
            JSONArray questionsArray = new JSONArray();

            // Convert questions to JSON format
            for (Question question : questions) {
                JSONObject questionObj = new JSONObject();
                questionObj.put("question", question.getText());
                questionObj.put("difficulty", question.getDifficulty());
                questionObj.put("answers", question.getAnswers());
                questionObj.put("correctAnswer", question.getCorrectAnswer());
                questionsArray.add(questionObj);
            }

            json.put("questions", questionsArray);

            // Write JSON to file
            file.write(json.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void cancelButtonClicked(ActionEvent event) throws IOException {
    	
		root = FXMLLoader.load(getClass().getResource("/View/QuestionsPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		setFullscreen();
		stage.show();
    	
    	
    }
}
