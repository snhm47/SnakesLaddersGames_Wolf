package Controller;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

import Utils.DiffLevel;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import model.Dice;
import model.DiceFactory;
import model.Games;
import model.Ladders;
import model.Player;
import model.PlayerMoveSubject;
import model.RunningGame;
import model.Snakes;

public class StartGameController {
	@FXML
    private Button start;

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
    
    Image img ;
    ImageView iv;
    
    private Timeline timeline;
    private int minutes = 0;
    private int seconds = 0;
    
    private int tern =0;
    
	DiceFactory df = new DiceFactory();
	
	Games game = RunningGame.getInstance().getCurrentGame();
	
	
	int diceRes;
    
    
    // start the timer
    @FXML
    void onClickStart(ActionEvent event) {
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
    private void updateTimer() {
        timer.setText(String.format("%02d:%02d", minutes, seconds));
    }
    
    
    @FXML
    void onroll(ActionEvent event) {
    	DiffLevel dl = RunningGame.getInstance().getCurrentGame().getDifficultyLevel();
    	Dice dice = df.getDice(dl);
    	diceRes = dice.roll();
    	PlayerMoveSubject pms = new PlayerMoveSubject();
		spDice.getChildren().removeAll(iv);
		spPlayer.getChildren().removeAll(lPlayer);
		spPlayer.getChildren().removeAll();
    	if(diceRes == 0 ) {
    		img = new Image(getClass().getResourceAsStream("/Image/Dice-0.png"));
    	}else if(diceRes == 1 ) {
    		img = new Image(getClass().getResourceAsStream("/Image/Dice-1.png"));
    		
    	}else if(diceRes == 2) {
    		img = new Image(getClass().getResourceAsStream("/Image/Dice-2.png"));
    		
    	}else if(diceRes == 3) {
    		img = new Image(getClass().getResourceAsStream("/Image/Dice-3.png"));
    		
    	}else if(diceRes == 4) {
    		img = new Image(getClass().getResourceAsStream("/Image/Dice-4.png"));
    		
    	}else if(diceRes == 5) {
    		img = new Image(getClass().getResourceAsStream("/Image/Dice-5.png"));
    		
    	}else if(diceRes == 6) {
    		img = new Image(getClass().getResourceAsStream("/Image/Dice-6.png"));
    		
    	}else if(diceRes == 7) {
    		img = new Image(getClass().getResourceAsStream("/Image/Dice-7.png"));
    		
    	}
    	int mod = RunningGame.getInstance().getPlayers().size();
    	Player p = null;
    	if(tern%mod == 0) {
    		p = RunningGame.getInstance().getPp().get(1);
    		System.out.println(p);
    	}else if(tern%mod == 1) {
    		p = RunningGame.getInstance().getPp().get(2);
    		System.out.println(p);

    	}else if(tern%mod == 2) {
    		p = RunningGame.getInstance().getPp().get(3);
    		System.out.println(p);

    	}else if(tern%mod == 3) {
    		p = RunningGame.getInstance().getPp().get(3);
    		System.out.println(p);
    	}
    	lPlayer = new Label(p.getNickName());
    	lPlayer.setTextFill(Color.WHITE);
    	lPlayer.setPrefSize(50, 50);
    	lPlayer.setTextAlignment(TextAlignment.CENTER);
    		int from = RunningGame.getInstance().getPlayerPlacement().get(p);
    		if(dl.equals(DiffLevel.easy)) {
    			spDice.getChildren().removeAll(iv);
    			if(diceRes == 5) {
    				//question easy
    	    		img = new Image(getClass().getResourceAsStream("/Image/Dice-5.png"));
    			}else if(diceRes == 6) {
    				//question meduim
    				img = new Image(getClass().getResourceAsStream("/Image/Dice-6.png"));
    			}else if(diceRes == 7) {
    				//question hard
    				img = new Image(getClass().getResourceAsStream("/Image/Dice-7.png"));
    			}else if(diceRes <=4 ){
    				int to = from + diceRes;
    				if(to > 49) {
    					to =49;
    				}		
    				pms.onPlayerMovement(p, from, to);
    				if(from != to) {
        				move(p, from, to , tern);
    				}
    			}
    			System.out.println("DiceRes : "+diceRes);
    		}else if(dl.equals(DiffLevel.medium)) {
    			spDice.getChildren().removeAll(iv);
    			if(diceRes == 7 || diceRes == 8) {
    				//question easy
    	    		img = new Image(getClass().getResourceAsStream("/Image/Dice-7.png"));
    			}else if(diceRes == 9 || diceRes == 10) {
    				//question meduim
    	    		img = new Image(getClass().getResourceAsStream("/Image/Dice-8.png"));
    			}else if(diceRes == 11 || diceRes == 12) {
    				//question hard
    	    		img = new Image(getClass().getResourceAsStream("/Image/Dice-9.png"));
    			}else {
    				int to = from + diceRes;
    				if(to > 100) {
    					to =100;
    				}
    				pms.onPlayerMovement(p, from, to);
    				if(from != to) {
        				move(p, from, to , tern);
    				}
    			}
    		}else if(dl.equals(DiffLevel.hard)) {
    			spDice.getChildren().removeAll(iv);
    			if(diceRes == 7 || diceRes == 8) {
    				//question easy
    	    		img = new Image(getClass().getResourceAsStream("/Image/Dice-7.png"));
    			}else if(diceRes == 9 || diceRes == 10) {
    				//question meduim
    	    		img = new Image(getClass().getResourceAsStream("/Image/Dice-8.png"));
    			}else if(diceRes == 11 || diceRes == 12 || diceRes == 13 || diceRes == 14 || diceRes == 15) {
    				img = new Image(getClass().getResourceAsStream("/Image/Dice-9.png"));
    				//question hard
    			}else {
    				int to = from + diceRes;
    				if(to > 169) {
    					to =169;
    				}
    				pms.onPlayerMovement(p, from, to);
    				if(from != to) {
        				move(p, from, to , tern);
    				}
    			}
    		}	
    		
    		iv = new ImageView(img);
        	iv.setFitWidth(150);
        	iv.setFitHeight(150);
        	spDice.getChildren().add(iv);
        	spPlayer.getChildren().add(lPlayer);
        	CheckEnd();
        	tern++;
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
    		if(dl.equals(DiffLevel.easy)) {
    			if(i==49) {
    				RunningGame.getInstance().setEndGame(true);
    			}
    		}else if(dl.equals(DiffLevel.medium)) {
    			if(i==100) {
    				RunningGame.getInstance().setEndGame(true);
    			}
    		}else {
    			if(i==169) {
    				RunningGame.getInstance().setEndGame(true);
    			}
    		}
    	}
    }
    
    
    void move(Player player , int from , int to , int tern) {
//    	int Tcul = GridPane.getColumnIndex(GameSetupController.boxes.get(to)) ;
//    	int Trow = GridPane.getRowIndex(GameSetupController.boxes.get(to));
//    	int Fcul = GridPane.getColumnIndex(GameSetupController.boxes.get(from));
//    	int Frow = GridPane.getRowIndex(GameSetupController.boxes.get(from));
    	Player p = null;
    	Circle c = null;
    	int mod = RunningGame.getInstance().getPlayers().size();
    	if(tern%mod == 0) {
    		p = RunningGame.getInstance().getPp().get(1);
    		c = RunningGame.getInstance().getPc().get(1);
    	}else if(tern%mod == 1) {
    		p = RunningGame.getInstance().getPp().get(2);
    		c = RunningGame.getInstance().getPc().get(2);
    	}else if(tern%mod == 2) {
    		p = RunningGame.getInstance().getPp().get(3);
    		c = RunningGame.getInstance().getPc().get(3);
    	}else if(tern%mod == 3) {
    		p = RunningGame.getInstance().getPp().get(4);
    		c = RunningGame.getInstance().getPc().get(4);
    	}
    	//check Snakes and ladders
    	Integer s = CheckSnake(to);
    	Integer l = CheckLadder(to);
    	if(s != null) {
//    		Tcul = GridPane.getColumnIndex(GameSetupController.boxes.get(s));
//        	Trow = GridPane.getRowIndex(GameSetupController.boxes.get(s));
        	to = s;
    	}
    	if(l != null) {
//    		Tcul = GridPane.getColumnIndex(GameSetupController.boxes.get(l));
//        	Trow = GridPane.getRowIndex(GameSetupController.boxes.get(l));
        	to = l;
    	}
    	
    	GameSetupController.grid.getChildren().removeIf(node -> node instanceof Circle );    		
//		System.out.println(c + " " + Tcul + " " + Trow);
		RunningGame.getInstance().getPlayerPlacement().put(p, to);
		p.setPlace(to);
		for(Player pl :RunningGame.getInstance().getPci().keySet()) {
			Circle cir = RunningGame.getInstance().getPci().get(pl);
			int row = GridPane.getRowIndex(GameSetupController.boxes.get(RunningGame.getInstance().getPlayerPlacement().get(pl)));
			int cul = GridPane.getColumnIndex(GameSetupController.boxes.get(RunningGame.getInstance().getPlayerPlacement().get(pl)));

			GameSetupController.grid.add(cir, cul, row);
		}
    }
    
    public Integer CheckSnake(int to ) {
    	ArrayList<Snakes> snakes = RunningGame.getInstance().getBoard().getsnakes();
    	for(Snakes s : snakes) {
    		if(to == s.getStartSnake()) {
    			int too = s.getEndSnake();
    			System.out.println("Snakeeeee");
    			return too;
    		}
    	}
    	return null;
    }
    public Integer CheckLadder(int to) {
    	ArrayList<Ladders> ladders = RunningGame.getInstance().getBoard().getladders();
    	for(Ladders l : ladders) {
    		if(to == l.getStartLadder()) {
    			int too = l.getEndLadder();
    			System.out.println("ladddder");
    			return too;
    		}
    	}
    	return null;
    }
    
    
}
