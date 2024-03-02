package Controller;


import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class HomeController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private ImageView hisIcon,QuesIcon;
	
	//FXMLLoader fxmlLoader = new FXMLLoader();
	@FXML
	private RadioButton rbuttoneasy, rbuttonMedium, rbuttonHard;
	
	@FXML
	private TextField infoTf = new TextField();
	
    private void setFullscreen() {
        stage.setResizable(false);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
    }

    @FXML
    public void switchToQuestionPage(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/View/QuestionsPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        setFullscreen(); // Set the stage to fullscreen mode
        stage.show();
    }

    @FXML
    public void switchToHistoryPage(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/View/HistoryScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        setFullscreen(); // Set the stage to fullscreen mode
        stage.show();
    }

    @FXML
    public void switchToGameSetup(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/View/Home2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        setFullscreen(); // Set the stage to fullscreen mode
        stage.show();
    }
	
	@FXML
	public void showGameRules(MouseEvent event){
	    String gameRules = "Origin: Snakes and Ladders, originally known as Moksha Patam, is an ancient Indian board game. The game's concept of progression and setbacks symbolizes the journey of life with virtues represented by ladders and vices by snakes.\n\n"
	            + "- How to Play:\n\n"
	            + "- Objective: Reach the final square of the board before your opponents.\n"
	            + "- Movement: Roll the dice to determine the number of steps you can take.\n"
	            + "- 0-6: Move forward accordingly.\n"
	            + "- Easy/Medium/Difficult Question: Answer a question correctly to proceed. Incorrect answers may hinder your progress.\n"
	            + "- Snakes: Various types of snakes will set you back:\n"
	            + "- Yellow: Moves you back one row.\n"
	            + "- Green: Sets you back two rows.\n"
	            + "- Blue: Takes you back three rows.\n"
	            + "- Red: Sends you back to the starting point.\n"
	            + "- Ladders: Ladders aid your progress:\n"
	            + "- Easy Game: Ladders of length 1-4.\n"
	            + "- Medium Game: Ladders of length 1-6.\n"
	            + "- Hard Game: Ladders of length 1-8.\n"
	            + "- Difficulty Levels:\n"
	            + "- Easy:(7x7) board size, Four snakes, one of each type, and ladders of varying lengths. Allows easy, medium, and difficult questions.\n"
	            + "- Medium:(10x10) board size Six snakes, double chance for questions, and ladders up to length six.\n"
	            + "- Hard:(13x13) board sieze Eight snakes, increased difficulty question chances, and ladders up to length eight.\n"
	            + "- Dive into the world of Snakes and Ladders, where luck, strategy, and knowledge intertwine!";
	    
	    
	    TextArea textArea = new TextArea(gameRules);
	    textArea.setEditable(false);
	    textArea.setWrapText(true);

	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setTitle("Game Rules");
	    alert.setHeaderText(null);

	    // Set the TextArea as the content of the alert dialog
	    alert.getDialogPane().setContent(textArea);
	    alert.showAndWait();
	}



	
	
	
}