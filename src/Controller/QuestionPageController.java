package Controller;
import model.Question;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Utils.DiffLevel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class QuestionPageController {

	@FXML 
    private ResourceBundle resources;

    @FXML 
    private URL location;

    @FXML 
    private Button addButton; 
    
    @FXML 
    private Button deleteButton; 
    
    @FXML
    private TableColumn<Question, ArrayList<String>> AnswersColumn;

    @FXML 
    private TableColumn<Question, String> CorrectAnswerColumn;

    @FXML 
    private TableColumn<Question, DiffLevel> DifficultyColumn; 

    @FXML 
    private ImageView homeImage; 

    @FXML 
    private TableColumn<Question, String> QuestionsColumn; 

    @FXML 
    private Label QuestionsPageLabel;

    @FXML 
    private Pane QuestionsPagePane; 
    @FXML 
    private TableView<Question> QuestionsTable; 
    
    Stage gameStage;
    
    @FXML
    void HomeImage(MouseEvent event) {
    	try {
   		 
 	        FXMLLoader fxmlLoader = new FXMLLoader();
 	        fxmlLoader.setLocation(getClass().getResource("../application/Home.fxml"));
 	        Scene HomeScene = new Scene(fxmlLoader.load());

 	        Stage homeStage = new Stage();
 	        homeStage.setTitle("Add Question");
 	        homeStage.setScene(HomeScene);
            homeStage.setResizable(false);
     //       initialize();
 	        homeStage.show();
 	        Stage prevStage = (Stage) homeImage.getScene().getWindow();
 	        prevStage.hide();
 	        

 	    } catch (IOException e) {
 	        // Handle IOException more robustly with specific messages
 	        e.printStackTrace();
 	        Alert alert = new Alert(AlertType.ERROR);
 	        alert.setTitle("Error");
 	        alert.setContentText("An error occurred while loading the FXML file. Please check the file path and content.");
 	        alert.showAndWait();
 	    }
    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	 QuestionsColumn.setCellValueFactory(new PropertyValueFactory<>("questionText"));
    	 AnswersColumn.setCellValueFactory(new PropertyValueFactory<>("answers"));
         CorrectAnswerColumn.setCellValueFactory(new PropertyValueFactory<>("correctAnswer"));
    	 DifficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
    	 SysData sys = new SysData();
    	 QuestionsTable.setItems(FXCollections.observableArrayList(sys.getQuestions()));
    }
    
    public void AddButton(ActionEvent event) throws IOException {
    	 try {
    		 
 	        FXMLLoader fxmlLoader = new FXMLLoader();
 	        fxmlLoader.setLocation(getClass().getResource("../application/AddQuestion.fxml"));
 	        Scene addQuestionScene = new Scene(fxmlLoader.load());

 	        gameStage = new Stage();
 	        gameStage.setTitle("Add Question");
 	        gameStage.setScene(addQuestionScene);
            gameStage.setResizable(false);
 	        gameStage.show();
 	        Stage prevStage = (Stage) addButton.getScene().getWindow();
 	        prevStage.hide();
 	        

 	    } catch (IOException e) {
 	        // Handle IOException more robustly with specific messages
 	        e.printStackTrace();
 	        Alert alert = new Alert(AlertType.ERROR);
 	        alert.setTitle("Error");
 	        alert.setContentText("An error occurred while loading the FXML file. Please check the file path and content.");
 	        alert.showAndWait();
 	    }
    }
    
    public void DeleteButton(ActionEvent event) {
    	
    	
    }
    

}

