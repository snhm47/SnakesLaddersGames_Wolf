package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import Utils.DiffLevel;
import Utils.LadderType;
import Utils.SquareType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Board;
import model.EasyBoard;
import model.Games;
import model.HardBoard;
import model.Ladders;
import model.MeduimBoard;
import model.Player;
import model.RunningGame;
import model.Snakes;


public class GameSetupController implements Initializable {
	
	public static GridPane grid;
	public static HashMap<Integer, VBox>boxes;

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	ToggleGroup group = new ToggleGroup();

	
	
	@FXML
	private StackPane spBox;

	@FXML
	private ChoiceBox<String> difficultycheckbox;
	@FXML
	private RadioButton rbuttoneasy, rbuttonMedium, rbuttonHard;
	@FXML
	private TextField tF1, tF2, tF3, tF4;
	@FXML
	private Text text1, text2, text3, text4;
	@FXML
	private Slider slider;
	@FXML
	private Button addPlayerbtn, cnfrmbtn, rstbtn, newgamebtn;
	@FXML
	private ColorPicker c1, c2, c3, c4;
	@FXML
	private TextField PlayerTf;
	@FXML
	private Label p1lbl, p2lbl, p3lbl, p4lbl;
	@FXML
	private VBox vBoxBoard;
	@FXML
	private Label timerlbl;
	
	@FXML
    private ChoiceBox<String> chooseColor;
	
	private ArrayList<String> colorListCheck;// help to add color to the new player
	
	
	Stage gameStage;
	
//	HashMap<Player, Circle> pc = new HashMap<Player, Circle>();

	Alert a = new Alert(AlertType.NONE);

	ArrayList<Player> players = new ArrayList<Player>();
	int difficulty;

	private int playersCount = 0;

	private int confirmedPlayerCount = 2; // Default to 2 players

	// to choose the level of the game
	@FXML
	public boolean getLevel() {
	    if (rbuttoneasy.isSelected()) {
	        System.out.println("easy");
	        return true;
	    } else if (rbuttonMedium.isSelected()) {
	        System.out.println("medium");
	        return true;
	    } else if (rbuttonHard.isSelected()) {
	        System.out.println("hard");
	        return true;
	    } else {
	        return false; // Return false if no level is selected
	    }
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colorListCheck = new ArrayList<String>();
		// add items to ChoiceBox
		chooseColor.getItems().add("BLUE");
		chooseColor.getItems().add("GREEN");
		chooseColor.getItems().add("YELLOW");
		chooseColor.getItems().add("RED");
		chooseColor.setDisable(true);
		
		rbuttoneasy.setToggleGroup(group);
		rbuttonMedium.setToggleGroup(group);
		rbuttonHard.setToggleGroup(group);

		addPlayerbtn.setDisable(true); // Disable adding players initially
		PlayerTf.setDisable(true);

		slider.valueProperty().addListener((observable, oldValue, newValue) -> {
			// Do nothing here, unlocking the player input controls should not depend on
			// slider value change
		});

		cnfrmbtn.setOnAction(event -> {
			confirmedPlayerCount = (int) Math.round(slider.getValue());
			addPlayerbtn.setDisable(false); // Enable adding players once confirmed
			PlayerTf.setDisable(false);
			chooseColor.setDisable(false);
			// Disable controls related to choosing number of players after confirmation
			slider.setDisable(true);
			cnfrmbtn.setDisable(true);
		});

		addPlayerbtn.setOnAction(this::addPlayer);
	}

	
	public void addPlayer(ActionEvent event) {
		Player p;
		String playerName = PlayerTf.getText().trim(); // Trim whitespace from input
		String playerColor = chooseColor.getValue();

		if(!chooseColor.getSelectionModel().isEmpty()) {
		// Check if player name is empty
		if (!playerName.isEmpty() && 
				playersCount < confirmedPlayerCount && 
				!checkColorChoosed(playerColor)) {
			// Check if player name is unique
			boolean isUniqueName = players.stream().noneMatch(player -> player.getNickName().equals(playerName));

			if (isUniqueName) {
				switch (playersCount) {
				case 0:
					p1lbl.setText("Player 1: "+playerName);
					p1lbl.setTextFill(paintColorChoosed(playerColor));
					p = new Player(playerName, returnColorChoosed(playerColor), 0);
					players.add(p);
					colorListCheck.add(playerColor);
					break;
				case 1:
					p2lbl.setText("Player 2: "+playerName);
					p2lbl.setTextFill(paintColorChoosed(playerColor));
					p = new Player(playerName, returnColorChoosed(playerColor), 0);
					players.add(p);
					colorListCheck.add(playerColor);
					break;
				case 2:
					p3lbl.setText("Player 3: "+playerName);
					p3lbl.setTextFill(paintColorChoosed(playerColor));
					p = new Player(playerName, returnColorChoosed(playerColor), 0);
					players.add(p);
					colorListCheck.add(playerColor);
					break;
				case 3:
					p4lbl.setText("Player 4: "+playerName);
					p4lbl.setTextFill(paintColorChoosed(playerColor));
					p = new Player(playerName, returnColorChoosed(playerColor), 0);
					players.add(p);
					colorListCheck.add(playerColor);
					break;
				default:
					break;
				}
				playersCount++;
				if (playersCount == confirmedPlayerCount) {
					addPlayerbtn.setDisable(true); // Disable adding more players after reaching confirmed count
					PlayerTf.setDisable(true);
					chooseColor.setDisable(true);
				}
				
	            PlayerTf.setText("");

			} else {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setContentText("Each player must have a unique name.");
				alert.show();
			}
		} else if(checkColorChoosed(playerColor)){
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("You cannot select this color, it has already been selected.");
			alert.show();	
		}else{
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("Player name cannot be empty or a duplicate.");
			alert.show();
		}
		}else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("player Color cannot be empty.");
			alert.show();
		}
	}
	
	
	// Return true if there is another player adding this color
	private boolean checkColorChoosed(String checkColor) {
		if(colorListCheck.isEmpty()||colorListCheck.equals(null))
		return false;
		for(String s : colorListCheck) {
			if(s.equals(checkColor)) {
				return true;
			}
		}
		return false;
		
	}

	
	private Paint paintColorChoosed(String checkColor) {
		if(checkColor.equals("BLUE"))
			return javafx.scene.paint.Color.BLUE;
		if(checkColor.equals("GREEN"))
			return javafx.scene.paint.Color.GREEN;
		if(checkColor.equals("YELLOW"))
			return javafx.scene.paint.Color.YELLOW;
		if(checkColor.equals("RED"))
			return javafx.scene.paint.Color.RED;
		
		return null;
		
	}
	
	private Utils.Color returnColorChoosed(String checkColor) {
		if(checkColor.equals("BLUE"))
			return Utils.Color.BLUE;
		if(checkColor.equals("GREEN"))
			return Utils.Color.GREEN;
		if(checkColor.equals("YELLOW"))
			return Utils.Color.YELLOW;
		if(checkColor.equals("RED"))
			return Utils.Color.RED;
		
		return null;
	}

	@FXML
	private void resetGameSetup() {

		// Clear player labels
		p1lbl.setText("");
		p2lbl.setText("");
		p3lbl.setText("");
		p4lbl.setText("");

		// Clear player list
		players.clear();
		playersCount = 0;

		// Reset slider and enable controls
		slider.setDisable(false);
		cnfrmbtn.setDisable(false);
		addPlayerbtn.setDisable(true);
		PlayerTf.setDisable(true);
		chooseColor.setDisable(true);
		PlayerTf.clear(); // Clear player name text field

		rbuttoneasy.setSelected(false);
		rbuttonMedium.setSelected(false);
		rbuttonHard.setSelected(false);
	}

	public void onClick(ActionEvent event) {
	    // Check if the game level is set, and the number of players reaches the confirmed amount
	    if (!getLevel() || playersCount < confirmedPlayerCount) {
	        Alert alert = new Alert(Alert.AlertType.WARNING);
	        alert.setTitle("Warning");
	        alert.setHeaderText("Game Setup Incomplete");
	        alert.setContentText("Please set the game level and ensure the number of players is sufficient.");
	        alert.showAndWait();
	        return;
	    }

	    try {
	        // Load FXML scene (improve error handling with more specific exceptions)
	        FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setLocation(getClass().getResource("/View/gameboard.fxml"));
	        Scene gameboardScene = new Scene(fxmlLoader.load(), 600, 400);

	        // Create new stage and configure (use descriptive variable names)
	        gameStage = new Stage();
	        gameStage.setTitle("Game Board");
	        gameStage.setScene(gameboardScene);
	        gameStage.setMaximized(true);
	        gameStage.setResizable(false);

	        init();

	        // Show new stage and hide previous (consider more graceful transitions if
	        // needed)
	        gameStage.show();
	        Stage prevStage = (Stage) newgamebtn.getScene().getWindow();
	        prevStage.hide();

	    } catch (IOException e) {
	        // Handle IOException more robustly with specific messages
	        e.printStackTrace();
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setContentText(
	                "An error occurred while loading the FXML file. Please check the file path and content.");
	        alert.showAndWait();
	    }
	}



	public void init() {
		
//		Utils.timer stopwatch = new Utils.timer();
//		stopwatch.start();
		if(rbuttoneasy.isSelected()) {
			difficulty = 0;
		}else if(rbuttonMedium.isSelected()) {
			difficulty = 1;
		}else {
			difficulty = 2;
		}

        Board b;
        int diffnumber=0;
        if(difficulty==0) {
        	Games games = new Games(players.size(), DiffLevel.easy);
        	
			RunningGame.setCurrentGame(games);
        	b= new EasyBoard();
        	diffnumber=7;
        }
        else if(difficulty==1) {
        	Games games = new Games(players.size(), DiffLevel.medium);
        	
			RunningGame.setCurrentGame(games);
        	b= new MeduimBoard();
        	diffnumber=10;
        }
        else{
        	Games games = new Games(players.size(), DiffLevel.hard);
        	
			RunningGame.setCurrentGame(games);
        	b= new HardBoard();
        	diffnumber=13;
        }

        grid = new GridPane();
        for(int cons=0;cons<diffnumber;cons++) {
        	ColumnConstraints column = new ColumnConstraints();
        	column.setMinWidth(10.0);
        	column.setPrefWidth(100.0);
        	column.setFillWidth(true);
        	column.setHgrow(Priority.SOMETIMES);

        	RowConstraints row = new RowConstraints();
        	row.setMinHeight(10.0);
        	row.setPrefHeight(100.0);
        	row.setFillHeight(true);
        	row.setVgrow(Priority.SOMETIMES);
        	
        	grid.getColumnConstraints().add(column);
        	grid.getRowConstraints().add(row);
        }

		b.initializeBoard();
		boxes = new HashMap<Integer, VBox>();
        for (int row = 0; row < diffnumber; row++) {
            for (int column = 0; column < diffnumber; column++) {
            	
                VBox box = new VBox();
                box.setAlignment(Pos.TOP_LEFT);
                //box.setEditable(false);
                
                if((row + column) % 2 == 1)
                	box.setStyle("-fx-background-color: orange;");
                	
                else
                	box.setStyle("-fx-background-color: white");
                box.setPrefSize(100,100);
//                Label num = new Label(""+b.getSquares()[row][column].getNumber());
                if(b.getSquares()[row][column].getSquareType().equals(SquareType.REGULAR)) {
                	Label num = new Label("  "+b.getSquares()[row][column].getNumber());
                	num.setScaleX(2);
                	num.setScaleY(2);
                	box.getChildren().add(num);
                }else if(b.getSquares()[row][column].getSquareType().equals(SquareType.QUESTION)) {
                	Label num = new Label("  "+b.getSquares()[row][column].getNumber()+'?');
                	num.setScaleX(2);
                	num.setScaleY(2);
                	box.getChildren().add(num);      	
                }else {
                	Label num = new Label("  "+b.getSquares()[row][column].getNumber()+"*");
                	num.setScaleX(2);
                	num.setScaleY(2);
                	box.getChildren().add(num);
                }
//                box.getChildren().add(num);
                if(b.getSquares()[row][column].getNumber()==1) {
//                	for(int i=0;i<players.size();i++) {
//	                	Circle player = new Circle();
//	                	Utils.Color c = players.get(i).getColorPlayer() ;
//	                	if (c.equals(Utils.Color.YELLOW)) {
//	                		player.setFill(Color.YELLOW);
//	                	}
//	                	if(c.equals(Utils.Color.RED)) {
//	                		player.setFill(Color.RED);
//	                	}
//	
//	                	if(c.equals(Utils.Color.BLUE)) {
//	                		player.setFill(Color.BLUE);
//	                	}
//	                	if(c.equals(Utils.Color.GREEN)) {
//	                		player.setFill(Color.GREEN);
//	                	}
//	                	player.setRadius(7);
//	                	box.getChildren().add(player);
//                	
//                	}
                	int tern = 1;
                	for (Player p : players) {
                		Circle player = new Circle();
                		player.setScaleX(1.5);
                		player.setScaleY(1.5);
	                	Utils.Color c = p.getColorPlayer();
	                	if (c.equals(Utils.Color.YELLOW)) {
	                		player.setFill(Color.YELLOW);
	                	}
	                	if(c.equals(Utils.Color.RED)) {
	                		player.setFill(Color.RED);
	                	}
	
	                	if(c.equals(Utils.Color.BLUE)) {
	                		player.setFill(Color.BLUE);
	                	}
	                	if(c.equals(Utils.Color.GREEN)) {
	                		player.setFill(Color.GREEN);
	                	}
	                	player.setRadius(7);
	                	box.getChildren().add(player);
	                	RunningGame.getInstance().getPc().put(tern, player);
	                	RunningGame.getInstance().getPlayers().add(p);
	                	RunningGame.getInstance().getPlayerPlacement().put(p, 1);
	                	RunningGame.getInstance().getPp().put(tern++, p);
                	}
                	tern =1;
                }
                grid.add(box, column, row, 1, 1);
                boxes.put(b.getSquares()[row][column].getNumber(), box);
            }
        }
        //difficulty=difficultycheckbox.getSelectionModel().getSelectedItem().toLowerCase();
        // Add grid to vBoxBoard or handle null case with more informative feedback
        //vBoxBoard=(VBox)gameStage.getScene().lookup("#vBoxBoard");
        spBox=(StackPane)gameStage.getScene().lookup("#spBox");
        if(spBox==null)
        {
        	Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setContentText("An error occurred while loading the FXML file. Please check the file path and content.");
	        alert.showAndWait();
        }
        else {
        	//vBoxBoard.getChildren().clear();
        	//vBoxBoard.getChildren().add(grid);
        	spBox.getChildren().add(grid);
        	Image img = new Image(getClass().getResourceAsStream("/Image/ladder.png"));


        	for(Ladders l : b.getladders()) {
            	int srow = GridPane.getRowIndex(boxes.get(l.getStartLadder()));
            	int scul = GridPane.getColumnIndex(boxes.get(l.getStartLadder()));
//            	int erow = GridPane.getRowIndex(boxes.get(l.getEndLadder()));
//            	int ecul = GridPane.getColumnIndex(boxes.get(l.getEndLadder()));
//            	int srow = GridPane.getRowIndex(boxes.get(37));
//            	int scul = GridPane.getColumnIndex(boxes.get(37));
//            	int erow = GridPane.getRowIndex(boxes.get(47));
//            	int ecul = GridPane.getColumnIndex(boxes.get(47));
//            	int leny = srow - erow;
//            	int lenx = scul - ecul ;
            	
//            	
            	System.out.println(l);
            	ImageView iv = new ImageView(img);
            	iv.setFitWidth((gameStage.getScene().getWidth()/5));// moslameh mn rab al3almen

            	
            	if(b.getLevel().equals(DiffLevel.easy)) {
                	iv.setTranslateY(iv.getTranslateY()-150);
                	if(l.getLadderType().equals(LadderType.TYPE_1)) {
                		iv.setScaleY(0.5);
                    	iv.setTranslateY(iv.getTranslateY()+50);
                    }else if(l.getLadderType().equals(LadderType.TYPE_2)) {
                		iv.setScaleY(0.8);
                    	iv.setTranslateY(iv.getTranslateY());
                    	iv.setTranslateX(iv.getTranslateX()+20);
                    }else if(l.getLadderType().equals(LadderType.TYPE_3)) {
                		iv.setScaleY(1.5);
                    	iv.setTranslateY(iv.getTranslateY()-120);
                    	iv.setTranslateX(iv.getTranslateX()+30);
                    }else if(l.getLadderType().equals(LadderType.TYPE_4)) {
                		iv.setScaleY(1.8);
                    	iv.setTranslateY(iv.getTranslateY()-200);
                    	iv.setTranslateX(iv.getTranslateX()+50);
                    }
                	
            	}else if(b.getLevel().equals(DiffLevel.medium)){
            		iv.setTranslateY(iv.getTranslateY()-50);
                	iv.setFitWidth((gameStage.getScene().getWidth()/6));// moslameh mn rab al3almen
            		 if(l.getLadderType().equals(LadderType.TYPE_1)) {
            			iv.setScaleY(0.3);
            			iv.setTranslateX(iv.getTranslateX()+20);
                     }else if(l.getLadderType().equals(LadderType.TYPE_2)) {
                    	iv.setScaleY(0.6);
                    	iv.setTranslateY(-100);
                     }else if(l.getLadderType().equals(LadderType.TYPE_3)) {
                    	iv.setScaleY(1);
                    	iv.setTranslateY(iv.getTranslateY()-100);
                    	iv.setTranslateX(iv.getTranslateX()+10);
     
                     }else if(l.getLadderType().equals(LadderType.TYPE_4)) {
                    	iv.setScaleY(1.3);
                    	iv.setTranslateY(iv.getTranslateY()-170);
                    	iv.setTranslateX(iv.getTranslateX()+40);


                     }else if(l.getLadderType().equals(LadderType.TYPE_5)) {
                    	iv.setScaleY(1.6);
                    	iv.setTranslateY(iv.getTranslateX()+20);
                    	iv.setTranslateY(iv.getTranslateY()-270);
                     }else if(l.getLadderType().equals(LadderType.TYPE_6)) {
                    	 iv.setScaleY(2);
                     	iv.setTranslateY(iv.getTranslateY()-300);
                    	iv.setTranslateX(iv.getTranslateX()+30);

                     }
            		 //ladders hard board
            	}else {
            		iv.setScaleX(0.5);
            		if(l.getLadderType().equals(LadderType.TYPE_1)) {
            			iv.setScaleY(0.3);
            			iv.setTranslateY(iv.getTranslateY()-50);
            			iv.setTranslateX(iv.getTranslateX()-20);
            		}else if(l.getLadderType().equals(LadderType.TYPE_2)) {
            			iv.setScaleY(0.5);
            			iv.setTranslateY(iv.getTranslateY()-80);
            			iv.setTranslateX(iv.getTranslateX()-10);
            		}else if(l.getLadderType().equals(LadderType.TYPE_3)) {
            			iv.setScaleY(0.8);
            			iv.setTranslateY(iv.getTranslateY()-130);
//            			iv.setTranslateX(iv.getTranslateX()-20);
            		}else if(l.getLadderType().equals(LadderType.TYPE_4)) {
            			iv.setScaleY(1);
            			iv.setTranslateY(iv.getTranslateY()-170);
            			iv.setTranslateX(iv.getTranslateX()+10);
            		}else if(l.getLadderType().equals(LadderType.TYPE_5)) {
            			iv.setScaleY(1.3);
            			iv.setTranslateY(iv.getTranslateY()-220);
            			iv.setTranslateX(iv.getTranslateX()+20);
                    }else if(l.getLadderType().equals(LadderType.TYPE_6)) {
                    	iv.setScaleY(1.5);
            			iv.setTranslateY(iv.getTranslateY()-250);
            			iv.setTranslateX(iv.getTranslateX()+25);
                    }else if(l.getLadderType().equals(LadderType.TYPE_7)) {
                    	iv.setScaleY(1.8);
            			iv.setTranslateY(iv.getTranslateY()-290);
            			iv.setTranslateX(iv.getTranslateX()+30);
                    }else if(l.getLadderType().equals(LadderType.TYPE_8)) {
                    	iv.setScaleY(2.1);
            			iv.setTranslateY(iv.getTranslateY()-350);
            			iv.setTranslateX(iv.getTranslateX()-30);
                    }
            	}
//            	double watar = (Math.sqrt(Math.pow(100*lenx,2)+Math.pow(100*leny,2)));
//            	iv.setFitHeight(watar);

//            	iv.setRotate(Math.toDegrees(Math.asin((100*leny)/watar)));
                
//                int addR = srow -(leny/2);
//                int addC =scul +(lenx/2);
//                if(Math.abs(lenx)%2 ==1) {
//                	addC++;
//                }
             
               
//                if(addC>=diffnumber) {
//                	addC=6;
//                }
//                if(addC<0) {
//                	addC=1;
//                }
//                System.out.println(l);
//                System.out.println(srow +" "+ scul);
//                System.out.println(lenx +" "+ leny);
//                System.out.println(addC +" "+ addR);
//                if(scul > ecul) {
//                	iv.setRotate(Math.toDegrees(Math.asin((100*lenx)/watar)));
//                }else if(scul < ecul) {
//                	iv.setRotate(Math.toDegrees(Math.asin((100*lenx)/watar)));
//                }

//                iv.setRotate(-10);
//                iv.setRotate(80);
//                iv.setRotate(30);//the degree from the pythagoras implementation

//                iv.setTranslateX(iv.getLayoutX()+70);//bshra7ha ba3den
//                iv.setTranslateY(iv.getLayoutY()-70);//bshra7ha ba3den
                if(l.getLadderType().equals(LadderType.TYPE_1)) {
                	iv.setFitHeight(gameStage.getScene().getHeight());
                }else if(l.getLadderType().equals(LadderType.TYPE_2)) {
                	iv.setFitHeight(gameStage.getScene().getHeight());
                }else if(l.getLadderType().equals(LadderType.TYPE_3)) {
                	iv.setFitHeight(gameStage.getScene().getHeight());
                }else if(l.getLadderType().equals(LadderType.TYPE_4)) {
                	iv.setFitHeight(gameStage.getScene().getHeight());
                }else if(l.getLadderType().equals(LadderType.TYPE_5)) {
                	iv.setFitHeight(gameStage.getScene().getHeight());
                }else if(l.getLadderType().equals(LadderType.TYPE_6)) {
                	iv.setFitHeight(gameStage.getScene().getHeight());
                }else if(l.getLadderType().equals(LadderType.TYPE_7)) {
                	iv.setFitHeight(gameStage.getScene().getHeight());
                }else if(l.getLadderType().equals(LadderType.TYPE_8)) {
                	iv.setFitHeight(gameStage.getScene().getHeight());
                }
                
                grid.add(iv, scul, srow);//the middle cell index,
        	}
        	
        	

        	//snakes in the board
        	for(Snakes s : b.getsnakes()) {
        		Image imgs = null ;
        		if(s.getSnakeType().equals(Utils.Color.RED)) {
        			imgs = new Image(getClass().getResourceAsStream("/Image/RedSnake.png"));
        		}else if(s.getSnakeType().equals(Utils.Color.YELLOW)) {
        			imgs = new Image(getClass().getResourceAsStream("/Image/YellowSnake.png"));
        		}else if(s.getSnakeType().equals(Utils.Color.BLUE)){
        			imgs = new Image(getClass().getResourceAsStream("/Image/BlueSnake.png"));
        		}else {
        			imgs = new Image(getClass().getResourceAsStream("/Image/GreenSnake.png"));
        		}
        		
        		
//        		int srow = GridPane.getRowIndex(boxes.get(s.getStartSnake()));
//            	int scul = GridPane.getColumnIndex(boxes.get(s.getEndSnake()));
            	int erow = GridPane.getRowIndex(boxes.get(s.getStartSnake()));
            	int ecul = GridPane.getColumnIndex(boxes.get(s.getEndSnake()));
//            	int srow = GridPane.getRowIndex(boxes.get(37));
//            	int scul = GridPane.getColumnIndex(boxes.get(37));
//            	int erow = GridPane.getRowIndex(boxes.get(47));
//            	int ecul = GridPane.getColumnIndex(boxes.get(47));
//            	int leny = srow - erow;
//            	int lenx = scul - ecul ;
            	
            	ImageView ivs = new ImageView(imgs);
            	ivs.setFitWidth((gameStage.getScene().getWidth()/3));// moslameh mn rab al3almen
            	if(b.getLevel().equals(DiffLevel.easy)) {
                	if(s.getSnakeType().equals(Utils.Color.YELLOW)) {
//                    	iv.setFitHeight(700);
                		ivs.setScaleY(0.5);
                    	ivs.setTranslateY(ivs.getTranslateY()+60);
                    	ivs.setTranslateX(ivs.getTranslateX()-30);
                    }else if(s.getSnakeType().equals(Utils.Color.GREEN)) {
//                    	iv.setFitHeight(1100);
                		ivs.setScaleY(0.7);
                    	ivs.setTranslateY(ivs.getTranslateY()+110);
                    	ivs.setTranslateX(ivs.getTranslateX()-20);
                    }else if(s.getSnakeType().equals(Utils.Color.BLUE)) {
//                    	iv.setFitHeight(1500);
                		ivs.setScaleY(1.1);
                    	ivs.setTranslateY(ivs.getTranslateY()+180);
                    	ivs.setTranslateX(ivs.getTranslateX()-10);
                    }else if(s.getSnakeType().equals(Utils.Color.RED)) {
//                    	iv.setFitHeight(10000);
                    	if(s.getStartSnake() == 14) {
                    		ivs.setScaleY(0.5);
                        	ivs.setTranslateY(ivs.getTranslateY()+60);
                        	ivs.setTranslateX(ivs.getTranslateX()-30);
                    	}else if(s.getStartSnake() == 15) {
                    		ivs.setScaleY(0.7);
                    		ivs.setTranslateY(ivs.getTranslateY()+110);
                        	ivs.setTranslateX(ivs.getTranslateX()-20);
                    	}else if(s.getStartSnake() == 28) {
                    		ivs.setScaleY(1.1);
                    		ivs.setTranslateY(ivs.getTranslateY()+180);
                        	ivs.setTranslateX(ivs.getTranslateX()-10);
                    	}else if(s.getStartSnake() == 29) {
                    		ivs.setScaleY(1.3);
                        	ivs.setTranslateY(ivs.getTranslateY()+260);
                    	}else if(s.getStartSnake() == 42) {
                    		ivs.setScaleY(1.6);
                        	ivs.setTranslateY(ivs.getTranslateY()+330);
                    	}else if(s.getStartSnake() == 43){
                    		ivs.setScaleY(1.9);
                        	ivs.setTranslateY(ivs.getTranslateY()+420);
                    	}
//                		ivs.setScaleY(1.3);
//                    	ivs.setTranslateY(ivs.getTranslateY()+260);
                    }
                	//snakes medium
            	}else if(b.getLevel().equals(DiffLevel.medium)){
            		if(s.getSnakeType().equals(Utils.Color.YELLOW)) {
                		ivs.setScaleY(0.3);
                    	ivs.setTranslateY(ivs.getTranslateY()+60);
                    	ivs.setTranslateX(ivs.getTranslateX()-30);
                    }else if(s.getSnakeType().equals(Utils.Color.GREEN)) {
//                    	iv.setFitHeight(1100);
                		ivs.setScaleY(0.5);
                    	ivs.setTranslateY(ivs.getTranslateY()+120);
                    	ivs.setTranslateX(ivs.getTranslateX()-40);
                    }else if(s.getSnakeType().equals(Utils.Color.BLUE)) {
//                    	iv.setFitHeight(1500);
                		ivs.setScaleY(1.2);
                    	ivs.setTranslateY(ivs.getTranslateY()+220);
                    	ivs.setTranslateX(ivs.getTranslateX()-50);
                    }else if(s.getSnakeType().equals(Utils.Color.RED)) {
//                    	iv.setFitHeight(10000);
                    	if(s.getStartSnake() == 20) {
                    		ivs.setScaleY(0.3);
                    		ivs.setTranslateY(ivs.getTranslateY()+100);
                    	}else if(s.getStartSnake() == 21) {
                    		ivs.setScaleY(0.7);
                    		ivs.setTranslateY(ivs.getTranslateY()+150);
                    	}else if(s.getStartSnake() == 40) {
                    		ivs.setScaleY(1.1);
                    		ivs.setTranslateY(ivs.getTranslateY()+170);
                    	}else if(s.getStartSnake() == 41) {
                    		ivs.setScaleY(1.3);
                    		ivs.setTranslateY(ivs.getTranslateY()+190);
                    	}else if(s.getStartSnake() == 60) {
                    		ivs.setScaleY(1.4);
                    		ivs.setTranslateY(ivs.getTranslateY()+220);
                    	}else if(s.getStartSnake() == 61) {
                    		ivs.setScaleY(1.5);
                    		ivs.setTranslateY(ivs.getTranslateY()+280);
                    	}else if(s.getStartSnake() == 80) {
                    		ivs.setScaleY(1.7);
                    		ivs.setTranslateY(ivs.getTranslateY()+330);
                    	}else if(s.getStartSnake() == 81) {
                    		ivs.setScaleY(1.8);
                    		ivs.setTranslateY(ivs.getTranslateY()+380);
                    	}
//                    	ivs.setTranslateY(ivs.getTranslateY()+280);
                    	ivs.setTranslateX(ivs.getTranslateX()-60);
                    }
            	}else {
            		if(s.getSnakeType().equals(Utils.Color.YELLOW)) {
                		ivs.setScaleY(0.2);
                		ivs.setScaleX(1.3);
                    	ivs.setTranslateY(ivs.getTranslateY()+40);
                    	ivs.setTranslateX(ivs.getTranslateX()-30);
                    }else if(s.getSnakeType().equals(Utils.Color.GREEN)) {
//                    	iv.setFitHeight(1100);
                		ivs.setScaleY(0.4);
                    	ivs.setTranslateY(ivs.getTranslateY()+70);
                    	ivs.setTranslateX(ivs.getTranslateX()-50);
                    }else if(s.getSnakeType().equals(Utils.Color.BLUE)) {
//                    	iv.setFitHeight(1500);
                		ivs.setScaleY(0.6);
                    	ivs.setTranslateY(ivs.getTranslateY()+120);
                    	ivs.setTranslateX(ivs.getTranslateX()-50);
                    }else if(s.getSnakeType().equals(Utils.Color.RED)) {
                    	
	                    if(s.getStartSnake() == 26) {
	                    	ivs.setScaleY(0.2);
	                		ivs.setScaleX(1.3);
	                    	ivs.setTranslateY(ivs.getTranslateY()+40);
	                    	ivs.setTranslateX(ivs.getTranslateX()-50);
	                	}else if(s.getStartSnake() == 27) {
	                		ivs.setScaleY(0.4);
	                    	ivs.setTranslateY(ivs.getTranslateY()+70);
	                    	ivs.setTranslateX(ivs.getTranslateX()-40);
	                	}else if(s.getStartSnake() == 52) {
	                		ivs.setScaleY(0.6);
	                    	ivs.setTranslateY(ivs.getTranslateY()+120);
	                    	ivs.setTranslateX(ivs.getTranslateX()-30);
	                	}else if(s.getStartSnake() == 53) {
	                		ivs.setScaleY(0.8);
	                		ivs.setTranslateY(ivs.getTranslateY()+170);
	                    	ivs.setTranslateX(ivs.getTranslateX()-20);
	                	}else if(s.getStartSnake() == 78) {
	                		ivs.setScaleY(1);
	                		ivs.setTranslateY(ivs.getTranslateY()+220);
	                    	ivs.setTranslateX(ivs.getTranslateX()-50);
	                	}else if(s.getStartSnake() == 79) {
	                		ivs.setScaleY(1.2);
	                		ivs.setTranslateY(ivs.getTranslateY()+270);
//	                    	ivs.setTranslateX(ivs.getTranslateX()-20);
	                	}else if(s.getStartSnake() == 104) {
	                		ivs.setScaleY(1.4);
	                		ivs.setTranslateY(ivs.getTranslateY()+300);
//	                    	ivs.setTranslateX(ivs.getTranslateX()+10);
	                    	ivs.setTranslateX(ivs.getTranslateX()-30);

	                	}else if(s.getStartSnake() == 105) {
	                		ivs.setScaleY(1.6);
	                		ivs.setTranslateY(ivs.getTranslateY()+350);
//	                    	ivs.setTranslateX(ivs.getTranslateX()+15);
	                    	ivs.setTranslateX(ivs.getTranslateX()-50);

	                	}else if(s.getStartSnake() == 130) {
	                		ivs.setScaleY(1.7);
	                		ivs.setTranslateY(ivs.getTranslateY()+380);
//	                    	ivs.setTranslateX(ivs.getTranslateX()+20);
	                    	ivs.setTranslateX(ivs.getTranslateX()-50);

	                	}else if(s.getStartSnake() == 131) {
	                		ivs.setScaleY(1.8);
	                		ivs.setTranslateY(ivs.getTranslateY()+380);
//	                    	ivs.setTranslateX(ivs.getTranslateX()+25);
	                    	ivs.setTranslateX(ivs.getTranslateX()-20);

	                	}else if(s.getStartSnake() == 156) {
	                		ivs.setScaleY(1.9);
	                		ivs.setTranslateY(ivs.getTranslateY()+400);
	                    	ivs.setTranslateX(ivs.getTranslateX()-50);

	                	}else if(s.getStartSnake() == 157) {
	                		ivs.setScaleY(2);
	                		ivs.setTranslateY(ivs.getTranslateY()+410);
//	                    	ivs.setTranslateX(ivs.getTranslateX()+10);
	                    	ivs.setTranslateX(ivs.getTranslateX()-50);


	                	}
                    }	
            	}
//            	double watar = (Math.sqrt(Math.pow(100*lenx,2)+Math.pow(100*leny,2)));
//            	ivs.setFitHeight(watar);

//            	iv.setRotate(Math.toDegrees(Math.asin((100*leny)/watar)));
                
//                int addR = srow -(leny/2);
//                int addC =scul +(lenx/2);
//                if(Math.abs(lenx)%2 ==1) {
//                	addC++;
//                }
             
               
//                if(addC>=diffnumber) {
//                	addC=6;
//                }
//                if(addC<0) {
//                	addC=1;
//                }
                System.out.println(s);
//                System.out.println(srow +" "+ scul);
//                System.out.println(lenx +" "+ leny);
//                System.out.println(addC +" "+ addR);
//                if(scul > ecul) {
//                	ivs.setRotate(Math.toDegrees(Math.asin((100*lenx)/watar)));
//                }else if(scul < ecul) {
//                	ivs.setRotate(Math.toDegrees(Math.asin((100*lenx)/watar)));
//                }
//                ivs.setRotate(50);

//                iv.setTranslateX(iv.getLayoutX()+70);//bshra7ha ba3den
//                iv.setTranslateY(iv.getLayoutY()-70);//bshra7ha ba3den
                
                grid.add(ivs, ecul, erow);//the middle cell index,
        	}
//        	grid.add(imgv,6, 1);
////        	iv.setRotate(-20);
////        	imgv.setScaleY(0.5);
////        	iv.setTranslateY(iv.getTranslateY()+50);
//        	imgv.setScaleX(0.4);
//			imgv.setScaleY(3.3);
//			imgv.setRotate(-83);
//			imgv.setTranslateY(imgv.getTranslateY()-80);
//	        imgv.setTranslateX(imgv.getTranslateX()-780);

//        	ImageView imgv2 = new ImageView(img);
//        	ImageView imgv3 = new ImageView(img);
//        	ImageView imgv4 = new ImageView(img);
//
//        	imgv.setRotate(45);
//        	imgv.setScaleX(0.5);
//        	imgv2.setTranslateX(imgv2.getTranslateX()+50);
//        	imgv2.setTranslateY(imgv2.getTranslateY()-50);
//        	imgv2.setScaleX(0.5);
//        	imgv2.setScaleY(0.5);
//        	imgv3.setScaleX(0.5);
//        	imgv3.setTranslateX(imgv3.getTranslateX()+170);
//        	imgv3.setTranslateY(imgv3.getTranslateX()-250);
//
//        	imgv3.setRotate(30);
//        	imgv3.setScaleY(1.5);
//        	
//        	
//        	imgv4.setScaleY(2.2);
//        	imgv4.setScaleX(0.5);
//        	imgv4.setRotate(-50);
//        	imgv4.setTranslateX(imgv4.getTranslateX()-230);
//        	imgv.setTranslateX(imgv.getTranslateX()-50);
//        	grid.add(imgv, 1, 5);
//        	grid.add(imgv2, 2, 2);
//        	grid.add(imgv3, 4, 3);
//        	grid.add(imgv4, 3, 3);





        	
        	
        }
//        ((Label)gameStage.getScene().lookup("#timerlbl")).setText(String.valueOf(stopwatch.getElapsedTimeSecs()));
        
	}
	
	@FXML
	public void returnToMainPage(MouseEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/View/StartMenu.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
