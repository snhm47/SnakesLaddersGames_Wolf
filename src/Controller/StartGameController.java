package Controller;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Utils.DiffLevel;
import Utils.SquareType;
import application.Main;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import model.Dice;
import model.DiceFactory;
import model.Games;
import model.History;
import model.Ladders;
import model.Player;
import model.PlayerMoveSubject;
import model.Question;
import model.RunningGame;
import model.Snakes;

public class StartGameController implements Initializable {

	@FXML
	private Button pause;
	@FXML
	private Text timer;
	@FXML
	private Rectangle diceRec;
	@FXML
	private Rectangle playerRec;
	@FXML
	private Button rollB;
	@FXML
	private StackPane spDice;
	@FXML
	private StackPane spPlayer;

	private Label lPlayer;

	// images vars
	Image img;
	ImageView iv;

	// timer vars
	private Timeline timeline;
	private int minutes = 0;
	private int seconds = 0;

	// game vars
	public static int turn = -1;
	public static Player p;
	public static DiffLevel dl;
	DiceFactory df = new DiceFactory();
	Games game = RunningGame.getInstance().getCurrentGame();
	public static int diceRes;
	public static Boolean quesSquare = false;

	// singleton
	public static StartGameController _ins;
	
	// help in Pause method 
		private String currentSituation;

	public static synchronized StartGameController getInstance() {
		if (_ins == null) {
			return new StartGameController();
		}
		return _ins;
	}

	// getters and setters
	public static int getTern() {
		return turn;
	}

	public static void setTern(int tern) {
		StartGameController.turn = tern;
	}

	public static Player getP() {
		return p;
	}

	public static void setP(Player p) {
		StartGameController.p = p;
	}

	// the timer function that update the timer all the time
	private void updateTimer() {
		timer.setText(String.format("%02d:%02d", minutes, seconds));
	}

	// on click on the roll button
	@FXML
	void onroll(ActionEvent event) {
		// start new roll (new turn)
		turn++;
		dl = RunningGame.getInstance().getCurrentGame().getDifficultyLevel();
		Dice dice = df.getDice(dl);
		diceRes = dice.roll();
		PlayerMoveSubject pms = new PlayerMoveSubject();
		spDice.getChildren().removeAll(iv);
		spPlayer.getChildren().removeAll(lPlayer);
		spPlayer.getChildren().removeAll();

		System.out.println("new roll");

		// handle the dice images in the board window
		if (diceRes == 0) {
			img = new Image(getClass().getResourceAsStream("/Image/Dice-0.png"));
		} else if (diceRes == 1) {
			img = new Image(getClass().getResourceAsStream("/Image/Dice-1.png"));

		} else if (diceRes == 2) {
			img = new Image(getClass().getResourceAsStream("/Image/Dice-2.png"));

		} else if (diceRes == 3) {
			img = new Image(getClass().getResourceAsStream("/Image/Dice-3.png"));

		} else if (diceRes == 4) {
			img = new Image(getClass().getResourceAsStream("/Image/Dice-4.png"));

		} else if (diceRes == 5) {
			img = new Image(getClass().getResourceAsStream("/Image/Dice-5.png"));

		} else if (diceRes == 6) {
			img = new Image(getClass().getResourceAsStream("/Image/Dice-6.png"));

		} else if (diceRes == 7) {
			img = new Image(getClass().getResourceAsStream("/Image/Dice-7.png"));

		}

		// this block return the current player turn
		int mod = RunningGame.getInstance().getPlayers().size();
		if (turn % mod == 0) {
			p = RunningGame.getInstance().getPp().get(1);
			setP(p);
			System.out.println(p);
		} else if (turn % mod == 1) {
			p = RunningGame.getInstance().getPp().get(2);
			setP(p);
			System.out.println(p);
		} else if (turn % mod == 2) {
			p = RunningGame.getInstance().getPp().get(3);
			setP(p);
			System.out.println(p);
		} else if (turn % mod == 3) {
			p = RunningGame.getInstance().getPp().get(3);
			setP(p);
			System.out.println(p);
		}

		// the label of the current player
		ImageView playerIcon = RunningGame.getInstance().getPci().get(p);
		if (playerIcon != null) {
			// Clone the ImageView if you want to keep the original in the grid or reuse it
			// if you're removing it
			ImageView iconForLabel = new ImageView();
			iconForLabel.setImage(playerIcon.getImage());
			iconForLabel.setFitWidth(playerIcon.getFitWidth());
			iconForLabel.setFitHeight(playerIcon.getFitHeight());

			// Create the label with the player's nickname and icon
			lPlayer = new Label(p.getNickName(), iconForLabel);
		} else {
			// Fallback if no icon found, just use the nickname
			lPlayer = new Label(p.getNickName());
		}

		lPlayer.setTextFill(Color.WHITE);
		lPlayer.setPrefSize(50, 50);
		lPlayer.setTextAlignment(TextAlignment.CENTER);

		// get the player cureent place
		int from = RunningGame.getInstance().getPlayerPlacement().get(p);
		int to = 0;

		// if the diffLevel is easy ...
		if (dl.equals(DiffLevel.easy)) {
			spDice.getChildren().removeAll(iv);
			if (diceRes == 5) {
				// question easy
				img = new Image(getClass().getResourceAsStream("/Image/Dice-5.png"));
				try {
					// Load FXML scene (improve error handling with more specific exceptions)
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/View/Questionpopup.fxml"));
					Scene gameboardScene = new Scene(fxmlLoader.load(), 600, 400);

//        		        QuestionSquareController
					// Create new stage and configure (use descriptive variable names)
					Stage gameStage = new Stage();
					gameStage.setTitle("Easy question");
					gameStage.setScene(gameboardScene);
					gameStage.setMaximized(false);
					gameStage.setResizable(false);

					Question ques = QuestionSquareController.getQuestion();
					System.out.println("easyQuestion " + ques);

					// Show new stage and hide previous (consider more graceful transitions if
					// needed)
					gameStage.show();

				} catch (IOException e) {
					// Handle IOException more robustly with specific messages
					e.printStackTrace();
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setContentText(
							"An error occurred while loading the FXML file. Please check the file path and content.");
					alert.showAndWait();
				}
			} else if (diceRes == 6) {
				// question medium
				img = new Image(getClass().getResourceAsStream("/Image/Dice-6.png"));
				try {
					// Load FXML scene (improve error handling with more specific exceptions)
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/View/Questionpopup.fxml"));
					Scene gameboardScene = new Scene(fxmlLoader.load(), 600, 400);

					// Create new stage and configure (use descriptive variable names)
					Stage gameStage = new Stage();
					gameStage.setTitle("Medium question");
					gameStage.setScene(gameboardScene);
					gameStage.setMaximized(false);
					gameStage.setResizable(false);

					Question ques = QuestionSquareController.getQuestion();
					System.out.println("mediumQuestion" + ques);

					// Show new stage and hide previous (consider more graceful transitions if
					// needed)
					gameStage.show();

				} catch (IOException e) {
					// Handle IOException more robustly with specific messages
					e.printStackTrace();
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setContentText(
							"An error occurred while loading the FXML file. Please check the file path and content.");
					alert.showAndWait();
				}
			} else if (diceRes == 7) {
				// question hard
				img = new Image(getClass().getResourceAsStream("/Image/Dice-7.png"));
				try {
					// Load FXML scene (improve error handling with more specific exceptions)
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/View/Questionpopup.fxml"));
					Scene gameboardScene = new Scene(fxmlLoader.load(), 600, 400);

//        		        QuestionSquareController
					// Create new stage and configure (use descriptive variable names)
					Stage gameStage = new Stage();
					gameStage.setTitle("Hard question");
					gameStage.setScene(gameboardScene);
					gameStage.setMaximized(false);
					gameStage.setResizable(false);

					Question ques = QuestionSquareController.getQuestion();

					System.out.println("HardQuestion" + ques);
					// Show new stage and hide previous (consider more graceful transitions if
					// needed)
					gameStage.show();

				} catch (IOException e) {
					// Handle IOException more robustly with specific messages
					e.printStackTrace();
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setContentText(
							"An error occurred while loading the FXML file. Please check the file path and content.");
					alert.showAndWait();
				}
			} else if (diceRes <= 4) {
				// if the dice result in the range 0-4 move the current player
				to = from + diceRes;
				if (to > 49) {
					to = 49;
				}
				pms.onPlayerMovement(p, from, to);
				if (from != to) {
					move(p, from, to, turn);
				}
			}

			// if the diffLevel is Medium...
		} else if (dl.equals(DiffLevel.medium)) {
			spDice.getChildren().removeAll(iv);
			if (diceRes == 7 || diceRes == 8) {
				// question easy
				img = new Image(getClass().getResourceAsStream("/Image/Dice-7.png"));
				try {
					// Load FXML scene (improve error handling with more specific exceptions)
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/View/Questionpopup.fxml"));
					Scene gameboardScene = new Scene(fxmlLoader.load(), 600, 400);

//        		        QuestionSquareController
					// Create new stage and configure (use descriptive variable names)
					Stage gameStage = new Stage();
					gameStage.setTitle("Easy question");
					gameStage.setScene(gameboardScene);
					gameStage.setMaximized(false);
					gameStage.setResizable(false);

					Question ques = QuestionSquareController.getQuestion();
					System.out.println("EasyQuestion" + ques);
					// Show new stage and hide previous (consider more graceful transitions if
					// needed)
					gameStage.show();

				} catch (IOException e) {
					// Handle IOException more robustly with specific messages
					e.printStackTrace();
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setContentText(
							"An error occurred while loading the FXML file. Please check the file path and content.");
					alert.showAndWait();
				}
			} else if (diceRes == 9 || diceRes == 10) {
				// question meduim
				img = new Image(getClass().getResourceAsStream("/Image/Dice-8.png"));
				try {
					// Load FXML scene (improve error handling with more specific exceptions)
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/View/Questionpopup.fxml"));
					Scene gameboardScene = new Scene(fxmlLoader.load(), 600, 400);

//        		        QuestionSquareController
					// Create new stage and configure (use descriptive variable names)
					Stage gameStage = new Stage();
					gameStage.setTitle("Medium question");
					gameStage.setScene(gameboardScene);
					gameStage.setMaximized(false);
					gameStage.setResizable(false);

					Question ques = QuestionSquareController.getQuestion();
					System.out.println("MediumQuestion" + ques);

					// Show new stage and hide previous (consider more graceful transitions if
					// needed)
					gameStage.show();

				} catch (IOException e) {
					// Handle IOException more robustly with specific messages
					e.printStackTrace();
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setContentText(
							"An error occurred while loading the FXML file. Please check the file path and content.");
					alert.showAndWait();
				}
			} else if (diceRes == 11 || diceRes == 12) {
				// question hard
				img = new Image(getClass().getResourceAsStream("/Image/Dice-9.png"));
				try {
					// Load FXML scene (improve error handling with more specific exceptions)
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/View/Questionpopup.fxml"));
					Scene gameboardScene = new Scene(fxmlLoader.load(), 600, 400);

//        		        QuestionSquareController
					// Create new stage and configure (use descriptive variable names)
					Stage gameStage = new Stage();
					gameStage.setTitle("Hard question");
					gameStage.setScene(gameboardScene);
					gameStage.setMaximized(false);
					gameStage.setResizable(false);

					Question ques = QuestionSquareController.getQuestion();
					System.out.println("HardQuestion" + ques);

					// Show new stage and hide previous (consider more graceful transitions if
					// needed)
					gameStage.show();

				} catch (IOException e) {
					// Handle IOException more robustly with specific messages
					e.printStackTrace();
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setContentText(
							"An error occurred while loading the FXML file. Please check the file path and content.");
					alert.showAndWait();
				}
			} else {
				to = from + diceRes;
				if (to > 100) {
					to = 100;
				}
				pms.onPlayerMovement(p, from, to);
				if (from != to) {
					move(p, from, to, turn);
				}
			}
		} else if (dl.equals(DiffLevel.hard)) {
			spDice.getChildren().removeAll(iv);
			if (diceRes == 7 || diceRes == 8) {
				// question easy
				img = new Image(getClass().getResourceAsStream("/Image/Dice-7.png"));
				try {
					// Load FXML scene (improve error handling with more specific exceptions)
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/View/Questionpopup.fxml"));
					Scene gameboardScene = new Scene(fxmlLoader.load(), 600, 400);

//        		        QuestionSquareController
					// Create new stage and configure (use descriptive variable names)
					Stage gameStage = new Stage();
					gameStage.setTitle("Easy question");
					gameStage.setScene(gameboardScene);
					gameStage.setMaximized(false);
					gameStage.setResizable(false);

					Question ques = QuestionSquareController.getQuestion();
					System.out.println("EasyQuestion" + ques);
					// Show new stage and hide previous (consider more graceful transitions if
					// needed)
					gameStage.show();

				} catch (IOException e) {
					// Handle IOException more robustly with specific messages
					e.printStackTrace();
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setContentText(
							"An error occurred while loading the FXML file. Please check the file path and content.");
					alert.showAndWait();
				}
			} else if (diceRes == 9 || diceRes == 10) {
				// question meduim
				img = new Image(getClass().getResourceAsStream("/Image/Dice-8.png"));
				try {
					// Load FXML scene (improve error handling with more specific exceptions)
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/View/Questionpopup.fxml"));
					Scene gameboardScene = new Scene(fxmlLoader.load(), 600, 400);

//        		        QuestionSquareController
					// Create new stage and configure (use descriptive variable names)
					Stage gameStage = new Stage();
					gameStage.setTitle("Medium question");
					gameStage.setScene(gameboardScene);
					gameStage.setMaximized(false);
					gameStage.setResizable(false);

					Question ques = QuestionSquareController.getQuestion();
					System.out.println("MediumQuestion" + ques);
					// Show new stage and hide previous (consider more graceful transitions if
					// needed)
					gameStage.show();

				} catch (IOException e) {
					// Handle IOException more robustly with specific messages
					e.printStackTrace();
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setContentText(
							"An error occurred while loading the FXML file. Please check the file path and content.");
					alert.showAndWait();
				}
			} else if (diceRes == 11 || diceRes == 12 || diceRes == 13 || diceRes == 14 || diceRes == 15) {
				img = new Image(getClass().getResourceAsStream("/Image/Dice-9.png"));
				try {
					// Load FXML scene (improve error handling with more specific exceptions)
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/View/Questionpopup.fxml"));
					Scene gameboardScene = new Scene(fxmlLoader.load(), 600, 400);

//        		        QuestionSquareController
					// Create new stage and configure (use descriptive variable names)
					Stage gameStage = new Stage();
					gameStage.setTitle("Hard question");
					gameStage.setScene(gameboardScene);
					gameStage.setMaximized(false);
					gameStage.setResizable(false);

					Question ques = QuestionSquareController.getQuestion();
					System.out.println("HardQuestion" + ques);
					// Show new stage and hide previous (consider more graceful transitions if
					// needed)
					gameStage.show();

				} catch (IOException e) {
					// Handle IOException more robustly with specific messages
					e.printStackTrace();
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setContentText(
							"An error occurred while loading the FXML file. Please check the file path and content.");
					alert.showAndWait();
				}
				// question hard
			} else {
				to = from + diceRes;
				if (to > 169) {
					to = 169;
				}
				pms.onPlayerMovement(p, from, to);
				if (from != to) {
					move(p, from, to, turn);
				}
			}
		}

		iv = new ImageView(img);
		iv.setFitWidth(150);
		iv.setFitHeight(150);
		spDice.getChildren().add(iv);
		spPlayer.getChildren().add(lPlayer);
		CheckEnd();

		// handle the question square
		for (Player player : RunningGame.getInstance().getPlayerPlacement().keySet()) {
			int num = RunningGame.getInstance().getPlayerPlacement().get(player);
			if (RunningGame.getInstance().getBoard().getSq().get(num).getSquareType().equals(SquareType.QUESTION)) {
				try {
					// Load FXML scene (improve error handling with more specific exceptions)
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/View/Questionpopup.fxml"));
					Scene gameboardScene = new Scene(fxmlLoader.load(), 600, 400);

					// Create new stage and configure (use descriptive variable names)
					Stage gameStage = new Stage();
					gameStage.setTitle("Question Square");
					gameStage.setScene(gameboardScene);
					gameStage.setMaximized(false);
					gameStage.setResizable(false);

					Question ques = QuestionSquareController.getQuestion();
					System.out.println("SquareQuestion" + ques);
					// Show new stage and hide previous (consider more graceful transitions if
					// needed)
					gameStage.show();

				} catch (IOException e) {
					// Handle IOException more robustly with specific messages
					e.printStackTrace();
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setContentText(
							"An error occurred while loading the FXML file. Please check the file path and content.");
					alert.showAndWait();
				}
				// handle surprise square
			} else if (RunningGame.getInstance().getBoard().getSq().get(num).getSquareType().equals(SquareType.SURPRISE)
					&& num == to) {
//        			move(p, from, to, to)
				System.out.println("surpriseeeeeeeeeeeeeeeeeeeee");
				to = from + 10;
				if (dl.equals(DiffLevel.easy)) {

					if (to > 49) {
						to = 49;
					}
					pms.onPlayerMovement(p, from, to);
					if (from != to) {
						move(p, from, to, turn);
					}
				} else if (dl.equals(DiffLevel.medium)) {
					if (to > 100) {
						to = 100;
					}
					pms.onPlayerMovement(p, from, to);
					if (from != to) {
						move(p, from, to, turn);
					}
				} else if (dl.equals(DiffLevel.hard)) {
					if (to > 169) {
						to = 169;
					}
					pms.onPlayerMovement(p, from, to);
					if (from != to) {
						move(p, from, to, turn);
					}
				}
			}
		}

		// check the end game
		if (RunningGame.getInstance().getEndGame()) {
			System.out.println("winning");
			showEndGamePopup(p.getNickName());
			History history = new History(p.getNickName(), null, mod, mod,
					String.valueOf(RunningGame.getInstance().getCurrentGame().getDifficultyLevel()));
			HistoryController.saveHistoryToJson(history);
		}

	}

	// method that display the winning
	public void showEndGamePopup(String winner) {
		// Create a custom dialog
		Dialog<String> dialog = new Dialog<>();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.setTitle("Game Over");

		// Set the content for the dialog
		DialogPane dialogPane = dialog.getDialogPane();
		dialogPane.getButtonTypes().addAll(ButtonType.CLOSE);
		Stage stage = (Stage) dialogPane.getScene().getWindow();

		// Custom layout for dialog content
		HBox content = new HBox();
		content.setSpacing(10);
		Label label = new Label(winner + " won the game!");
		content.getChildren().add(label);

		// Create the button for returning to the main page
		Button btnReturnMain = new Button("Return to Main Page");
		btnReturnMain.setOnAction(e -> {
			// Stop the theme song
			GameSetupController.stopThemeSong();
			// Start the main menu music, if applicable
			Main.startMusic();
			// Close the current stage
			stage.close();
			// Load and display the StartMenu.fxml
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/View/StartMenu.fxml"));
				Scene scene = new Scene(root);
				Stage mainStage = new Stage();
				mainStage.setScene(scene);
				mainStage.show();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		});

		// Set content to the dialog
		content.getChildren().add(btnReturnMain);
		dialogPane.setContent(content);

		// Show the dialog and wait for it to be closed
		dialog.showAndWait();
	}

//    void runTheGame() {
//    	    	
//    	while(!RunningGame.getInstance().getEndGame()) {
//    		CheckEnd();
//    	}
//    }

	void CheckEnd() {
		DiffLevel dl = RunningGame.getInstance().getCurrentGame().getDifficultyLevel();
		for (Integer i : RunningGame.getInstance().getPlayerPlacement().values()) {
			if (dl.equals(DiffLevel.easy)) {
				if (i == 49) {
					RunningGame.getInstance().setEndGame(true);
				}
			} else if (dl.equals(DiffLevel.medium)) {
				if (i == 100) {
					RunningGame.getInstance().setEndGame(true);
				}
			} else {
				if (i == 169) {
					RunningGame.getInstance().setEndGame(true);
				}
			}
		}
	}

	// method that moving the player on the board
	void move(Player player, int from, int to, int tern) {
		int mod = RunningGame.getInstance().getPlayers().size();
		if (tern % mod == 0) {
			p = RunningGame.getInstance().getPp().get(1);
		} else if (tern % mod == 1) {
			p = RunningGame.getInstance().getPp().get(2);
		} else if (tern % mod == 2) {
			p = RunningGame.getInstance().getPp().get(3);
		} else if (tern % mod == 3) {
			p = RunningGame.getInstance().getPp().get(4);
		}

		// check Snakes and ladders
		if (to < 1) {
			to = 1;
		}
		Integer s = CheckSnake(to);
		Integer l = CheckLadder(to);
		if (s != null) {
			to = s;
			p.setPlace(to);
			RunningGame.getInstance().getPlayerPlacement().put(p, to);
		}
		if (l != null) {
			to = l;
			p.setPlace(to);
			RunningGame.getInstance().getPlayerPlacement().put(p, to);
		}
		RunningGame.getInstance().getPlayerPlacement().put(p, to);
		// remove all the player from the grid
		GameSetupController.grid.getChildren()
				.removeIf(node -> node instanceof ImageView && "playerIcon".equals(node.getUserData()));

		p.setPlace(to);
		System.out.println(p.getNickName() + " move " + from + " to " + to);
		// add all the player again
		for (Player pl : RunningGame.getInstance().getPci().keySet()) {
			ImageView cir = RunningGame.getInstance().getPci().get(pl);
			int row = GridPane
					.getRowIndex(GameSetupController.boxes.get(RunningGame.getInstance().getPlayerPlacement().get(pl)));
			int cul = GridPane.getColumnIndex(
					GameSetupController.boxes.get(RunningGame.getInstance().getPlayerPlacement().get(pl)));
			GameSetupController.grid.add(cir, cul, row);
		}
	}

	// check if there is snake on the square
	public Integer CheckSnake(int to) {
		ArrayList<Snakes> snakes = RunningGame.getInstance().getBoard().getsnakes();
		for (Snakes s : snakes) {
			if (to == s.getStartSnake()) {
				int too = s.getEndSnake();
				System.out.println("Snakeeeee");
				return too;
			}
		}
		return null;
	}

	// check if there is ladder on the square
	public Integer CheckLadder(int to) {
		ArrayList<Ladders> ladders = RunningGame.getInstance().getBoard().getladders();
		for (Ladders l : ladders) {
			if (to == l.getStartLadder()) {
				int too = l.getEndLadder();
				System.out.println("ladddder");
				return too;
			}
		}
		return null;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		currentSituation="play";
		setupAndStartTimer();
		initializeFirstPlayerIcon();
		initializeFirstPlayerName();

	}

	private void initializeFirstPlayerIcon() {
		if (!RunningGame.getInstance().getPp().isEmpty()) {
			p = RunningGame.getInstance().getPp().get(0); // First player
			ImageView playerIcon = RunningGame.getInstance().getPci().get(p); // Get icon

			// Use VBox for vertical layout
			VBox vbox = new VBox();
			vbox.setAlignment(Pos.CENTER); // Center alignment

			if (playerIcon != null) {
				playerIcon.setFitWidth(20); // Adjust icon size
				playerIcon.setFitHeight(20); // Adjust icon size
				vbox.getChildren().add(playerIcon); // Add icon to VBox
			}

			// Clear any existing content and add the new VBox
			spPlayer.getChildren().clear();
			spPlayer.getChildren().add(vbox); // Add VBox to StackPane or the layout pane you are using
		}
	}

	private void initializeFirstPlayerName() {
		if (!RunningGame.getInstance().getPp().isEmpty()) {
			p = RunningGame.getInstance().getPp().get(0); // First player

			Label playerName = new Label(p.getNickName()); // Player name label
			playerName.setTextFill(Color.WHITE);
			// Clear any existing content and add the new Label
			spPlayer.getChildren().clear();
			spPlayer.getChildren().add(playerName); // Add playerName Label to StackPane or the layout pane you are
													// using
		}
	}

	private void setupAndStartTimer() {
		rollB.setDisable(true);
		if (timeline == null) {
			timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
				seconds++;
				if (seconds == 60) {
					minutes++;
					seconds = 0;
				}
				updateTimer();
			}));
			timeline.setCycleCount(Timeline.INDEFINITE);
		}
		timeline.play();
		rollB.setDisable(false);
	}
	
	@FXML
	private void handleMuteButtonAction(ActionEvent event) {
		GameSetupController.toggleMusic();
	}
	
	// pause the game
	@FXML
    void pauseorplay(ActionEvent event) {
		if(currentSituation.equals("play")) {
			currentSituation="pause";
			pause.setText("play");
			rollB.setDisable(true);
			timeline.pause();
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    alert.setTitle("Paused");
		    alert.setHeaderText(null);
		    alert.setContentText("The game is paused. Click Play to resume.");
		    alert.showAndWait();
			System.out.println(currentSituation);
		}else {
			currentSituation="play";
			pause.setText("pause");
			timeline.play();
			System.out.println(currentSituation);
			rollB.setDisable(false);
		}
    }
	

}