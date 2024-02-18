package Controller;

import java.util.ArrayList;

import Utils.DiffLevel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Question;

public class AddQuestionsController {
	 @FXML
	    private Label AddQuestionLabel;

	    @FXML
	    private Pane AddQuestionPane;

	    @FXML
	    private Label Answer1Label;

	    @FXML
	    private Label Answer2Label;

	    @FXML
	    private Label Answer3Label;

	    @FXML
	    private Label Answer4Label;

	    @FXML
	    private Label DifficultlyLabel;

	    @FXML
	    private ImageView HomeImage;

	    @FXML
	    private Label QuestionTextArea;

	    @FXML
	    private Label TheCorrectAnswerLabel;

	    @FXML
	    private Button addButton;

	    @FXML
	    private TextArea answer1TextArea;

	    @FXML
	    private TextArea answer2TextArea;

	    @FXML
	    private TextArea answer3TextArea;

	    @FXML
	    private TextArea answer4TextArea;

	    @FXML
	    private Button cancelButton;

	    @FXML
	    private ComboBox<String> correctComboBox;

	    @FXML
	    private ComboBox<String> difficultlyComboBox;

	    @FXML
	    private TextArea questionTextArea;
	    
	    @FXML
	    void initialize() {
	    	correctComboBox.setItems(FXCollections.observableArrayList("1","2","3"));	
	    	difficultlyComboBox.setItems(FXCollections.observableArrayList("easy","medium","hard"));	

	    }

	    @FXML
	    void AddButton(ActionEvent event) {
	    	if(checkEmpty()) {
	    	ArrayList<String> answers = new ArrayList<String>();
	    	answers.add(answer1TextArea.getText());
	    	answers.add(answer2TextArea.getText());
	    	answers.add(answer3TextArea.getText());
	    	answers.add(answer4TextArea.getText());
	    	DiffLevel df =DiffLevel.easy;
	    	if(difficultlyComboBox.getValue().equals("medium")) {
				df = DiffLevel.medium ;
			}
			if(difficultlyComboBox.getValue().equals("hard")) {
				df = DiffLevel.hard ;
			}
	    	Question newQuestion =new Question(questionTextArea.getText(), answers, correctComboBox.getValue() , df);
	    	SysData sys = new SysData();
//	    	sys.addQuestionToJsonFile(newQuestion);
	    	}else {
	    		Alert alert = new Alert(AlertType.NONE);
	 	        alert.setTitle("Error");
	 	        alert.setContentText("There is one or more blank TextArea");
	 	        alert.showAndWait();
	    	}

	    }
	    // help method that return true if all the TextArea and ComboBox not empty
	    boolean checkEmpty() {
	    	if(answer1TextArea.getText().isEmpty())
	    		return false;
	    	if(answer2TextArea.getText().isEmpty())
	    		return false;
	    	if(answer3TextArea.getText().isEmpty())
	    		return false;
	    	if(answer4TextArea.getText().isEmpty())
	    		return false;
	    	if(questionTextArea.getText().isEmpty())
	    		return false;
	    	if(correctComboBox.getValue().equals(null))
	    		return false;
	    	if(difficultlyComboBox.getValue().equals(null))
	    		return false;
	    	
	    	return true;
	    }

	    @FXML
	    void CancelButton(ActionEvent event) {
	    	
	    }
}
