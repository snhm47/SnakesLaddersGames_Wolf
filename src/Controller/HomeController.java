package Controller;


import java.io.IOException;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.RadioButton;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;

public class HomeController {//implements Initializable
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private ImageView hisIcon,QuesIcon;
	
	//FXMLLoader fxmlLoader = new FXMLLoader();
	@FXML
	private RadioButton rbuttoneasy, rbuttonMedium, rbuttonHard;

		
	
	
	//edwar
	//once the Launch button pressed it goes to the gamesetup page
	@FXML
	public void switchToGameSetup(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/View/Home2.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	//edwar
	//to go to add question page
	@FXML
	public void switchToAddQ(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/View/AddQuestion.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	//edwar
	//once pressed on the history icon it goes to the history page
	@FXML
	public void switchToHistoryPage(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/View/HistoryScreen.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	//edwar
	//once pressed on the Question icon it goes to the Question page
	@FXML
	public void switchToQuestionPage(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/View/QuestionsPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	//edwar
	//return to Main Page
	@FXML
	public void returnToMainPage(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/View/StartMenu.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}