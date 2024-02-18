package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddQuestionController implements Initializable{
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private RadioButton rbuttoneasy, rbuttonMedium, rbuttonHard;
	
	@FXML
	private TextField t1,t2,t3,t4,qTf;
	
	@FXML
	private ChoiceBox<String> answersList;
	
   // edwar
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    String[] answers = {t1.getText(), t2.getText(), t3.getText(), t4.getText()};
	    answersList.getItems().addAll(answers);
	    answersList.setOnAction(this::getRightAnswer);
	}

	
	//edwar
	public void getRightAnswer(ActionEvent event) {
		String rightAnswer = answersList.getValue();
		//return rightAnswer;	
	}
	
	@FXML
	public void returnToMainPage(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../View/StartMenu.fxml"));
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
}
