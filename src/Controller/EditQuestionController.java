package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Question;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EditQuestionController {

    @FXML
    private Label selectedQuestionLabel;

    @FXML
    private TextArea modifiedQuestionTextArea;

    private Question questionToEdit;

    public void initialize() {
    }

    public void setQuestionToEdit(Question question) {
        this.questionToEdit = question;
        selectedQuestionLabel.setText(question.getText());
    }

    @FXML
    private void saveQuestion(ActionEvent event) {
        String editedQuestion = modifiedQuestionTextArea.getText().trim();

        if (questionToEdit == null) {
            System.out.println("questionToEdit is null");
            return;
        }

        if (!editedQuestion.isEmpty()) {
            questionToEdit.setText(editedQuestion);
            updateQuestionInJSON();
            closeStage(event); 
        }
    }

    private void updateQuestionInJSON() {
        try {
            if (questionToEdit == null) {
                System.out.println("questionToEdit is null");
                return;
            }

            JSONParser parser = new JSONParser();
            try (FileReader reader = new FileReader("WolfQuestionsDB.json")) {
                Object obj = parser.parse(reader);
                JSONObject jsonObject = (JSONObject) obj;
                JSONArray jsonArray = (JSONArray) jsonObject.get("questions");

                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonQuestion = (JSONObject) jsonArray.get(i);
                        jsonQuestion.put("question", questionToEdit.getText());
                        break; 
                    
                }

                try (FileWriter fileWriter = new FileWriter("WolfQuestionsDB.json")) {
                    fileWriter.write(jsonObject.toJSONString());
                    fileWriter.flush();
                }
            }
       
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void closeStage(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelEdit(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/QuestionsPage.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage(); 
            stage.setScene(scene);
            stage.show();

            closeStage(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
