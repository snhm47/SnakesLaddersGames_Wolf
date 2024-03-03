package Controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Admin;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class StartMenuController {

	private Stage stage;
	private Scene scene;
	private Parent root;

	Admin admin = new Admin("admin", "admin");

	@FXML
	private TextField aTf;

	@FXML
	private PasswordField passTf;

	@FXML
	private ImageView hisIcon, QuesIcon;

	// FXMLLoader fxmlLoader = new FXMLLoader();
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
		// Load the admin login FXML file
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/QuestionsPage.fxml"));
		root = loader.load();
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void switchToHistoryPage(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/View/HistoryAfterAdmin.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void switchToGameSetup(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/View/Home2.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void showGameRules(MouseEvent event) {
	    // Check if the TextArea is already added
	    TextArea textArea = findGameRulesTextArea(event);
	    
	    if (textArea == null) {
	    	
	        String gameRules = "Origin: Snakes and Ladders, originally known as Moksha Patam, is an ancient Indian board game. The game's concept of progression and setbacks symbolizes the journey of life with virtues represented by ladders and vices by snakes.\n\n"
	                + "- How to Play:\n\n" + "- Objective: Reach the final square of the board before your opponents.\n"
	                + "- Movement: Roll the dice to determine the number of steps you can take.\n"
	                + "- 0-6: Move forward accordingly.\n"
	                + "- Easy/Medium/Difficult Question: Answer a question correctly to proceed. Incorrect answers may hinder your progress.\n"
	                + "- Snakes: Various types of snakes will set you back:\n" + "- Yellow: Moves you back one row.\n"
	                + "- Green: Sets you back two rows.\n" + "- Blue: Takes you back three rows.\n"
	                + "- Red: Sends you back to the starting point.\n" + "- Ladders: Ladders aid your progress:\n"
	                + "- Easy Game: Ladders of length 1-4.\n" + "- Medium Game: Ladders of length 1-6.\n"
	                + "- Hard Game: Ladders of length 1-8.\n" + "- Difficulty Levels:\n"
	                + "- Easy:(7x7) board size, Four snakes, one of each type, and ladders of varying lengths. Allows easy, medium, and difficult questions.\n"
	                + "- Medium:(10x10) board size Six snakes, double chance for questions, and ladders up to length six.\n"
	                + "- Hard:(13x13) board sieze Eight snakes, increased difficulty question chances, and ladders up to length eight.\n"
	                + "- Dive into the world of Snakes and Ladders, where luck, strategy, and knowledge intertwine!";

	        textArea = new TextArea(gameRules);
	        textArea.setEditable(false);
	        textArea.setWrapText(true);

	        // Find the parent AnchorPane and add the TextArea to it
	        AnchorPane parentPane = (AnchorPane) ((Node) event.getSource()).getParent();
	        parentPane.getChildren().add(textArea);

	        // Set the layout coordinates for the TextArea
	        AnchorPane.setTopAnchor(textArea, 170.0); // Adjust these coordinates according to your layout
	        AnchorPane.setLeftAnchor(textArea, 1200.0); // Adjust these coordinates according to your layout
	    } else {
	        // TextArea is already added, remove it
	        AnchorPane parentPane = (AnchorPane) textArea.getParent();
	        parentPane.getChildren().remove(textArea);
	    }
	}

	private TextArea findGameRulesTextArea(MouseEvent event) {
	    // Find the parent AnchorPane
	    Node parent = ((Node) event.getSource()).getParent();
	    if (parent instanceof AnchorPane) {
	        AnchorPane anchorPane = (AnchorPane) parent;

	        // Iterate through the children to find the TextArea
	        for (Node node : anchorPane.getChildren()) {
	            if (node instanceof TextArea) {
	                // Return the TextArea if found
	                return (TextArea) node;
	            }
	        }
	    }
	    // Return null if TextArea not found
	    return null;
	}


}