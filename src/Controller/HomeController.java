package Controller;
import model.*;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;

import Utils.DiffLevel;
import Utils.LadderType;
import Utils.SquareType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class HomeController {
	@FXML
	private Rectangle diceRec;
	@FXML
	private GridPane gridPane;
	@FXML
	private Rectangle cellRectangle;
	
	static Scene scene;
	@FXML
	private Pane cellPane21;
	
	@FXML
	private StackPane spBox;
	
	@FXML
	private ChoiceBox<String> difficultycheckbox;
	@FXML
	private VBox vBoxBoard;
	@FXML
	private Button startgameBTN;

	FXMLLoader fxmlLoader = new FXMLLoader();
	
	@FXML
	private Label timerlbl;
	@FXML
	private Label player1lbl;
	@FXML
	private Label player2lbl;
	@FXML
	private Label player3lbl;
	@FXML
	private Label player4lbl;
	@FXML
	private TextField playernametxtbx;
	@FXML
	private Button newgamebtn;
	Alert a = new Alert(AlertType.NONE);
	private int playersCount=0;
	Stage gameStage;
	Stage History;
	
	int difficulty;
	ArrayList<Player> players = new ArrayList<Player>();
	public void onClick() {

	    try {
	        // Load FXML scene (improve error handling with more specific exceptions)
	    	if(!checkinputvalidity()) return;
	    	
	        FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setLocation(getClass().getResource("../application/gameboard.fxml"));
	        Scene gameboardScene = new Scene(fxmlLoader.load(), 600, 400);

	        // Create new stage and configure (use descriptive variable names)
	        gameStage = new Stage();
	        gameStage.setTitle("Game Board");
	        gameStage.setScene(gameboardScene);
	        gameStage.setMaximized(true);
            gameStage.setResizable(false);

            init();
	        // Show new stage and hide previous (consider more graceful transitions if needed)
	        gameStage.show();
	        Stage prevStage = (Stage) newgamebtn.getScene().getWindow();
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
//	public void histOnClick() {
//		
//        try {
//        	FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.setLocation(getClass().getResource("../application/HistoryScreen.fxml"));
//			Scene HistoryScreen = new Scene(fxmlLoader.load(), 600, 400);
//			
//			History.setScene(HistoryScreen);
//			History.show();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

	public void AddPlayer() {
		Player p;
		if(playernametxtbx.getText()!=null) {
			if(playersCount==0) {
				player1lbl.setText(playernametxtbx.getText());
				player1lbl.setTextFill(Color.BLUE);
				p=new Player(player1lbl.getText(),Utils.Color.BLUE,0);
				players.add(p);
				
			}
			if(playersCount==1) {
				player2lbl.setText(playernametxtbx.getText());
				player2lbl.setTextFill(Color.GREEN);
				p=new Player(player1lbl.getText(),Utils.Color.GREEN,0);
				players.add(p);
			}
			if(playersCount==2) {
				player3lbl.setText(playernametxtbx.getText());
				player3lbl.setTextFill(Color.YELLOW);
				p=new Player(player1lbl.getText(),Utils.Color.YELLOW,0);
				players.add(p);
			}
			if(playersCount==3) {
				player4lbl.setText(playernametxtbx.getText());
				player4lbl.setTextFill(Color.RED);
				p=new Player(player1lbl.getText(),Utils.Color.RED,0);
				players.add(p);
			}
			if(playersCount>3) {
				a.setAlertType(AlertType.CONFIRMATION);
				a.setContentText("you have reached the maximum number of players\nYou should remove a player in case to be able to add another player.");
				a.show();
			}
			else
			playersCount++;
		}
	}
	public void init() {
		
//		Utils.timer stopwatch = new Utils.timer();
//		stopwatch.start();
		

        difficulty=difficultycheckbox.getSelectionModel().getSelectedIndex();
        Board b;
        Square[][] ss = null;

        int diffnumber=0;
        if(difficulty==0) {

        	b= new Board(DiffLevel.easy);
        	diffnumber=7;
        }
        else if(difficulty==1) {
        	b= new Board(DiffLevel.medium);
        	diffnumber=10;
        }
        else{
        	b= new Board(DiffLevel.hard);
        	diffnumber=13;
        }

        GridPane grid = new GridPane();
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
		HashMap<Integer, VBox>boxes = new HashMap<Integer, VBox>();
        for (int row = 0; row < diffnumber; row++) {
            for (int column = 0; column < diffnumber; column++) {
            	
                VBox box = new VBox();
                box.setAlignment(Pos.CENTER);
                //box.setEditable(false);
                
                if((row + column) % 2 == 1)
                	box.setStyle("-fx-background-color: red;");
                	
                else
                	box.setStyle("-fx-background-color: white");
                box.setPrefSize(100,100);
//                Label num = new Label(""+b.getSquares()[row][column].getNumber());
                if(b.getSquares()[row][column].getSquareType().equals(SquareType.REGULAR)) {
                	Label num = new Label(""+b.getSquares()[row][column].getNumber());
                	box.getChildren().add(num);
                }else if(b.getSquares()[row][column].getSquareType().equals(SquareType.QUESTION)) {
                	Label num = new Label(""+b.getSquares()[row][column].getNumber()+'?');
                	box.getChildren().add(num);
                }else {
                	Label num = new Label(""+b.getSquares()[row][column].getNumber()+"*");
                	box.getChildren().add(num);

                }
//                box.getChildren().add(num);
                if(b.getSquares()[row][column].getNumber()==1) {
                	for(int i=0;i<players.size();i++) {
                	Circle player = new Circle();
                	Utils.Color c = players.get(i).getColorPlayer() ;
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
                	}
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
        	Image img = new Image(getClass().getResourceAsStream("../Image/ladder.png"));
//        	Image img1 = new Image(getClass().getResourceAsStream("../Image/Snake.png"));

//        	ImageView imgv = new ImageView(img);
//        	ImageView imgv1 = new ImageView(img1);

//        	for( Ladders l : b.getladders()) {
//        		ImageView imgv = new ImageView(img);
//        		int srow = GridPane.getRowIndex(boxes.get(l.getStartLadder()));
//        		int scul = GridPane.getColumnIndex(boxes.get(l.getStartLadder()));
//        		int erow = GridPane.getRowIndex(boxes.get(l.getEndLadder()));
//        		int ecul = GridPane.getColumnIndex(boxes.get(l.getEndLadder()));
//        		
//        		if(diffnumber == 7 ) {
//            		imgv.setScaleX(0.5);
//            		imgv.setTranslateX(imgv.getTranslateX()+50);
//            		if(l.getLadderType().equals(LadderType.TYPE_1)) {
//            			if((scul - ecul) == 6) {
//            				
//            			}
//            			imgv.setScaleY(0.5);
//            			imgv.setTranslateY(imgv.getTranslateY()-100);
//            		}else if(l.getLadderType().equals(LadderType.TYPE_2)) {
//            			imgv.setScaleY(1);
//            		}else if(l.getLadderType().equals(LadderType.TYPE_3)) {
//            			imgv.setScaleY(1.5);
//            		}else {
//            			imgv.setScaleY(2);
//            		}
//        			
//        		}
//        		grid.add(imgv, scul, srow);
//        		
//        	}
        	ImageView iv = new ImageView(img);
        	iv.setScaleX(0.5);
        	grid.add(iv, 3, 1);
        	iv.setRotate(-55);
//        	iv.setScaleY(0.5);
        	iv.setTranslateY(iv.getTranslateY()-100);
        	iv.setTranslateX(iv.getTranslateX()-70);
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




//        	spBox.getChildren().add(imgv2);
//        	//imgv.setFitWidth(diffnumber);
        	
        	
        }
//        ((Label)gameStage.getScene().lookup("#timerlbl")).setText(String.valueOf(stopwatch.getElapsedTimeSecs()));
        
	}
	private boolean checkinputvalidity() {
		if(players.size()<2) return false;
		if(difficultycheckbox.getSelectionModel().getSelectedItem()==null || difficultycheckbox.getSelectionModel().getSelectedItem().trim()=="") return false;
		return true;
	}
}