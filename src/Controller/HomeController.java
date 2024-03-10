package Controller;

import javafx.scene.control.ButtonBar;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import model.Admin;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HomeController implements Initializable {

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private Text text1;

	@FXML
	private Text text2;

	    
	@FXML
    private Button muteButton;
	
	@FXML
	private Button questionBtn;

	@FXML
	private Button historyBtn;

	@FXML
	private Button playBtn;

	@FXML
	private Button logOutBtn;
	
	@FXML
	private ChoiceBox<String> musicChoiceBox;

	@FXML
	private ImageView titleImg;

	@FXML
	private ImageView hisIcon, QuesIcon;

	@FXML
	private ImageView imgbackground;

	@FXML
	private AnchorPane pane;

	// FXMLLoader fxmlLoader = new FXMLLoader();
	@FXML
	private RadioButton rbuttoneasy, rbuttonMedium, rbuttonHard;
	
	@FXML
	private ImageView imgmove1, imgmove2,imgLadd, imgBrunch;
	
	 private TranslateTransition t1, t2;
	    private FadeTransition fadeOut1, fadeOut2, fadeIn1, fadeIn2;


	@FXML
	private TextField infoTf = new TextField();
	Admin admin = new Admin("admin", "1234");

	@FXML
	private ComboBox<String> picMode = new ComboBox<String>();
	private static Boolean themePic = false;

	private boolean isAdminLoggedIn = false;
	private static String themeName = "Enchanted Forest";

	@FXML
	public void switchToQuestionPage(ActionEvent event) {
		if (!isAdminLoggedIn) {
			// If admin is not already logged in, prompt for login
			showLoginDialog(event);
		} else {
			// If admin is already logged in, proceed directly to QuestionsPage
			loadQuestionsPage(event);
		}
	}

	@FXML
	public void logOut(ActionEvent event) {
		stage = (Stage) pane.getScene().getWindow();
		stage.close();
	}

	private void showLoginDialog(ActionEvent event) {
		// Create and display the admin access warning dialog
		Alert adminAccessAlert = new Alert(Alert.AlertType.CONFIRMATION);
		adminAccessAlert.setTitle("Admin Access Required");
		adminAccessAlert.setHeaderText("This page is only for admins.");
		adminAccessAlert.setContentText("Do you want to login?");

		ButtonType buttonTypeLogin = new ButtonType("Login");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
		adminAccessAlert.getButtonTypes().setAll(buttonTypeLogin, buttonTypeCancel);

		Optional<ButtonType> adminAccessResult = adminAccessAlert.showAndWait();
		if (adminAccessResult.isPresent() && adminAccessResult.get() == buttonTypeLogin) {
			// If the user chooses to login, show the login dialog
			Dialog<Pair<String, String>> loginDialog = new Dialog<>();
			loginDialog.setTitle("Admin Login");
			loginDialog.setHeaderText("Please enter your username and password:");

			// Set the button types (Login and Cancel)
			loginDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

			// Create the username and password labels and fields
			GridPane grid = new GridPane();
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

			TextField usernameField = new TextField();
			usernameField.setPromptText("Username");
			PasswordField passwordField = new PasswordField();
			passwordField.setPromptText("Password");

			grid.add(new Label("Username:"), 0, 0);
			grid.add(usernameField, 1, 0);
			grid.add(new Label("Password:"), 0, 1);
			grid.add(passwordField, 1, 1);

			loginDialog.getDialogPane().setContent(grid);

			// Convert the result to a username-password-pair when the login button is
			// clicked
			loginDialog.setResultConverter(dialogButton -> {
				if (dialogButton == ButtonType.OK) {
					return new Pair<>(usernameField.getText(), passwordField.getText());
				}
				return null;
			});

			Optional<Pair<String, String>> loginResult = loginDialog.showAndWait();
			if (loginResult.isPresent()) {
				String username = loginResult.get().getKey();
				String password = loginResult.get().getValue();
				// Implement your authentication logic here
				boolean isAuthenticated = authenticate(username, password);
				if (isAuthenticated) {
					isAdminLoggedIn = true; // Set admin login status to true
					loadQuestionsPage(event); // Proceed to load QuestionsPage
				} else {
					// Show an error message for failed authentication
					Alert errorAlert = new Alert(Alert.AlertType.ERROR);
					errorAlert.setTitle("Authentication Failed");
					errorAlert.setHeaderText(null);
					errorAlert.setContentText("Invalid username or password.");
					errorAlert.showAndWait();
				}
			}
		}
	}

	// Method to load the QuestionsPage
	private void loadQuestionsPage(ActionEvent event) {
		try {
			javafx.scene.Parent root = FXMLLoader.load(getClass().getResource("/View/QuestionsPage.fxml"));
			if (root == null) {
				throw new IOException("FXML file is null");
			}
			javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene()
					.getWindow();
			javafx.scene.Scene scene = new javafx.scene.Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace(); // Handle IOException properly
		} catch (NullPointerException e) {
			e.printStackTrace(); // Handle NullPointerException
		}
	}

	private boolean authenticate(String username, String password) {
		Admin admin = new Admin("admin", "1234");
		return admin.getUserName().equals(username) && admin.getPass().equals(password);
	}

	@FXML
	public void switchToHistoryPage(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/View/HistoryScreen.fxml"));
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

		TextArea textArea = new TextArea(gameRules);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Game Rules");
		alert.setHeaderText(null);

		// Set the TextArea as the content of the alert dialog
		alert.getDialogPane().setContent(textArea);
		alert.setX(19);
		alert.setY(130);
		alert.show();
	}

	@FXML
	void changeDesign(ActionEvent event) {

	}

	@FXML
	void changeColor(ActionEvent event) {
		if (picMode.getValue().equals("Dark Aurora")) {
            imgmove1.setVisible(false);
            imgmove2.setVisible(false);
            imgLadd.setVisible(false);
            imgBrunch.setVisible(false);
			stopAnimations();
			darkAuroraMode();
			StaticController.getInstance().setPageColor("Dark Aurora");

		}
		/*
		 * if(picMode.getValue().equals("Twilight Serenity")) { twilightSerenityMode();
		 * StaticController.getInstance().setPageColor("Twilight Serenity");
		 * 
		 * }
		 */
		if (picMode.getValue().equals("Enchanted Forest")) {
            imgmove1.setVisible(true);
            imgmove2.setVisible(true);
            imgLadd.setVisible(true);
            imgBrunch.setVisible(true);
            startAnimations();
			enchantedForestMode();
			StaticController.getInstance().setPageColor("Enchanted Forest");

		}
		if (picMode.getValue().equals("Oceanic Dreams")) {
            imgmove1.setVisible(false);
            imgmove2.setVisible(false);
            imgLadd.setVisible(false);
			imgBrunch.setVisible(false);
			stopAnimations();
			oceanicDreamsMode();
			StaticController.getInstance().setPageColor("Oceanic Dreams");

		}

	}

	/*
	 * private void twilightSerenityMode() { //Image newImage3 = new
	 * Image("Image/edjungletheme1.jpg"); //imgbackground.setImage(newImage3);
	 * System.out.println("Twilight Serenity"); themePic=true;
	 * themeName="Twilight Serenity"; }
	 */

	private void enchantedForestMode() {
		Image newImage3 = new Image("Image/edjungletheme.jpg");
		imgbackground.setImage(newImage3);
		// Image tit = new Image("Image/titForest.png‬");
		// titleImg.setImage(tit);
		Image newImage2 = new Image("Image/titForest.png");
		titleImg.setImage(newImage2);
		playBtn.setTextFill(Color.WHITE);
		playBtn.setStyle("-fx-background-color: green;");
		logOutBtn.setTextFill(Color.WHITE);
		logOutBtn.setStyle("-fx-background-color: green;");
		historyBtn.setTextFill(Color.WHITE);
		historyBtn.setStyle("-fx-background-color: green;");
		questionBtn.setTextFill(Color.WHITE);
		questionBtn.setStyle("-fx-background-color: green;");
		muteButton.setTextFill(Color.WHITE);
		muteButton.setStyle("-fx-background-color: green;");
		System.out.println("Enchanted Forest");
		themePic = true;
		themeName = "Enchanted Forest";
		text1.setFill(Color.BLACK);
		text2.setFill(Color.BLACK);
	}

	private void oceanicDreamsMode() {
		Image newImage3 = new Image("Image/ocean.jpg");
		imgbackground.setImage(newImage3);
		Image newImage2 = new Image("Image/DarksnakeAndLadders.png");
		titleImg.setImage(newImage2);
		playBtn.setTextFill(Color.DARKSLATEGREY);
		playBtn.setStyle("-fx-background-color: gray;");
		logOutBtn.setTextFill(Color.DARKSLATEGREY);
		logOutBtn.setStyle("-fx-background-color: gray;");
		historyBtn.setTextFill(Color.DARKSLATEGREY);
		historyBtn.setStyle("-fx-background-color: gray;");
		questionBtn.setTextFill(Color.DARKSLATEGREY);
		questionBtn.setStyle("-fx-background-color: gray;");
		muteButton.setTextFill(Color.DARKSLATEGREY);
		muteButton.setStyle("-fx-background-color: gray;");
		System.out.println("Oceanic Dreams");
		themePic = true;
		themeName = "Oceanic Dreams";
		text1.setFill(Color.WHITE);
		text2.setFill(Color.WHITE);
	}

	private void darkAuroraMode() {

		Image newImage = new Image("Image/DarkBackg.jpg");
		imgbackground.setImage(newImage);

		Image newImage2 = new Image("Image/DarkTitle.png");
		titleImg.setImage(newImage2);
		playBtn.setTextFill(Color.BLACK);
		playBtn.setStyle("-fx-background-color: #2F4F4F;");
		logOutBtn.setTextFill(Color.BLACK);
		logOutBtn.setStyle("-fx-background-color: #2F4F4F;");
		historyBtn.setTextFill(Color.BLACK);
		historyBtn.setStyle("-fx-background-color: #2F4F4F;");
		questionBtn.setTextFill(Color.BLACK);
		questionBtn.setStyle("-fx-background-color: #2F4F4F;");
		muteButton.setTextFill(Color.BLACK);
		muteButton.setStyle("-fx-background-color: #2F4F4F;");
		System.out.println("Dark Aurora");
		themePic = true;
		themeName = "Dark Aurora";
		text1.setFill(Color.WHITE);
		text2.setFill(Color.WHITE);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		 musicChoiceBox.getItems().addAll("Angry Birds", "Avatar OST", "OG Metro"); // Add your music track names here
		 musicChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			    switchMusicTrack((String) newVal); // Cast newVal to String
			});
		// TODO Auto-generated method stub
		picMode.getItems().add("Dark Aurora");
		// picMode.getItems().add("Twilight Serenity");
		picMode.getItems().add("Enchanted Forest");
		picMode.getItems().add("Oceanic Dreams");
		if (themePic.equals(false)) {
			enchantedForestMode();
			StaticController.getInstance().setPageColor("Enchanted Forest");
		} else {
			if (themeName.equals("Dark Aurora")) {
				darkAuroraMode();
				StaticController.getInstance().setPageColor("Dark Aurora");
			}
			/*
			 * if(themeName.equals("Twilight Serenity")) { twilightSerenityMode();
			 * StaticController.getInstance().setPageColor("Twilight Serenity");
			 * 
			 * }
			 */
			if (themeName.equals("Enchanted Forest")) {
				enchantedForestMode();
				StaticController.getInstance().setPageColor("Enchanted Forest");

			}
			if (themeName.equals("Oceanic Dreams")) {
				oceanicDreamsMode();
				StaticController.getInstance().setPageColor("Oceanic Dreams");

			}
		}

		imgmove2.setVisible(false); // Ensure imgmove2 is initially invisible

		// Translate imgmove1 upward
		 t1 = createTranslateTransition(imgmove1, 90, -500, 2500, false);
		// Fade out imgmove1 after translation
		 fadeOut1 = createFadeTransition(imgmove1, 1, 0, 500);
		// Fade in imgmove1 for cycle restart, not immediately used
		 fadeIn1 = createFadeTransition(imgmove1, 0, 1, 500);

		// Translate imgmove2 upward
		 t2 = createTranslateTransition(imgmove2, 900, 0, 2500, false);
		// Fade out imgmove2 after translation
		 fadeOut2 = createFadeTransition(imgmove2, 1, 0, 500);
		// Fade in imgmove2, not immediately used
		 fadeIn2 = createFadeTransition(imgmove2, 0, 1, 500);

		// Sequence for imgmove1
		t1.setOnFinished(event -> fadeOut1.play());
		fadeOut1.setOnFinished(event -> {
			imgmove1.setVisible(false); // Make sure imgmove1 is invisible after fade
			imgmove2.setVisible(true); // Make imgmove2 visible right before its animation
			t2.play();
		});

		// Sequence for imgmove2
		t2.setOnFinished(event -> fadeOut2.play());
		fadeOut2.setOnFinished(event -> {
			imgmove2.setVisible(false); // Ensure imgmove2 stays invisible after its fade, preventing re-appearance
			resetImageView(imgmove1); // Reset imgmove1 for next cycle
			resetImageView(imgmove2); // Reset imgmove2 but keep it invisible
			imgmove1.setVisible(true); // Only make imgmove1 visible before its animation
			t1.play(); // Restart the cycle with imgmove1's animation
		});

		// Start the animation cycle with imgmove1
		t1.play();

	}

	private TranslateTransition createTranslateTransition(ImageView imageView, double byX, double toY, int duration,
			boolean autoReverse) {
		TranslateTransition transition = new TranslateTransition(Duration.millis(duration), imageView);
		transition.setByX(byX);
		transition.setToY(toY);
		transition.setAutoReverse(autoReverse);
		return transition;
	}

	private FadeTransition createFadeTransition(ImageView imageView, double fromValue, double toValue, int duration) {
		FadeTransition transition = new FadeTransition(Duration.millis(duration), imageView);
		transition.setFromValue(fromValue);
		transition.setToValue(toValue);
		return transition;
	}

	private void resetImageView(ImageView imageView) {
		imageView.setTranslateX(0);
		imageView.setTranslateY(0);
		imageView.setOpacity(1); // Reset opacity to make sure the imageView is visible when it reappears
	}

	@FXML
	private void handleMuteButtonAction(ActionEvent event) {
		Main.toggleMusic();
	}
	
	private void startAnimations() {
        // Reset positions if necessary and start or restart animations
        resetImageView(imgmove1);
        resetImageView(imgmove2);
        t1.play();
        t2.play();
    }
	
	private void switchMusicTrack(String trackName) {
	    String musicFile = ""; // Initialize empty string for music file URL
	    switch (trackName) {
	        case "Angry Birds":
	            musicFile = getClass().getResource("/Music/AngryBirdsTheme.mp3").toExternalForm();
	            break;
	        case "Avatar OST":
	            musicFile = getClass().getResource("/Music/avatarOst.mp3").toExternalForm();
	            break;
	        case "OG Metro":
	            musicFile = getClass().getResource("/Music/ogmetro1.mp3").toExternalForm();
	            break;
	        // Add more cases as necessary for additional tracks
	    }

	    if (!musicFile.isEmpty()) {
	        if (Main.mediaPlayer != null) {
	            Main.mediaPlayer.stop(); // Stop currently playing music if any
	        }
	        Media media = new Media(musicFile);
	        Main.mediaPlayer = new MediaPlayer(media);
	        Main.mediaPlayer.setAutoPlay(true);
	        Main.mediaPlayer.setVolume(0.4); // Set volume (adjust as necessary)
	        Main.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	    }
	}

    private void stopAnimations() {
        // Stop all animations
        t1.stop();
        fadeOut1.stop();
        fadeIn1.stop();
        
        t2.stop();
        fadeOut2.stop();
        fadeIn2.stop();
        // If using fadeIn1, fadeIn2 in cycles, stop those too
        // fadeIn1.stop();
        // fadeIn2.stop();
    }

}