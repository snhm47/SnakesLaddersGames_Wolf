package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
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
    @FXML
    private ImageView imgBackg;
    
    @FXML
    private ImageView titleImg;
     

    private Question questionToEdit;

    public void initialize() {
    	titleImg.setFitWidth(200); 
        titleImg.setFitHeight(150);
    	if(StaticController.getInstance().getPageColor().equals("Oceanic Dreams")) {
    		Image newImage3 = new Image("Image/ocean.jpg");
    		imgBackg.setImage(newImage3);
    		Image newImage2 = new Image("Image/ocean_EditQuestion.png");
            titleImg.setImage(newImage2);
            selectedQuestionLabel.setTextFill(Color.web("#d6bb98"));
            }
    	if(StaticController.getInstance().getPageColor().equals("Dark Aurora")) {
    		Image newImage3 = new Image("Image/DarkBackg.jpg");
    		imgBackg.setImage(newImage3);
    		Image newImage2 = new Image("Image/dark_editQuestion.png");
            titleImg.setImage(newImage2);
            selectedQuestionLabel.setTextFill(Color.web("#FFFFFF"));

    	}
    	if(StaticController.getInstance().getPageColor().equals("Enchanted Forest")) {
    		Image newImage3 = new Image("Image/edjungletheme.jpg");
    		imgBackg.setImage(newImage3);
    		Image newImage2 = new Image("Image/Forest_EditQuestion.png");
            titleImg.setImage(newImage2);
            selectedQuestionLabel.setTextFill(Color.web("#000000"));

    }}

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/QuestionsPage.fxml"));
            Parent root = loader.load();
            QuestionPageController controller = loader.getController();

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
