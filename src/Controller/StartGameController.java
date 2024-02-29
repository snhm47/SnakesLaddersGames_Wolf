package Controller;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import Utils.DiffLevel;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import model.Dice;
import model.DiceFactory;
import model.Games;
import model.Player;
import model.RunningGame;

public class StartGameController {
	@FXML
    private Button start;

    @FXML
    private Text timer;
    
    @FXML
    private Rectangle diceRec;
    @FXML
    private Button rollB;
    @FXML
    private StackPane spDice;
    
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
    }
    private void updateTimer() {
        timer.setText(String.format("%02d:%02d", minutes, seconds));
    }
    
    
    @FXML
    void onroll(ActionEvent event) {
    	DiffLevel dl = RunningGame.getInstance().getCurrentGame().getDifficultyLevel();
    	Dice dice = df.getDice(dl);
    	diceRes = dice.roll();
    	
    	if(diceRes == 0 ) {
    		spDice.getChildren().remove(iv);
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
    		
    		int from = RunningGame.getInstance().getPlayerPlacement().get(p);
    		if(dl.equals(DiffLevel.easy)) {
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
    				System.out.println("to = "+to);
    				move(p, from, to , tern);
    			}
    		}else if(dl.equals(DiffLevel.medium)) {
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
    				move(p, from, to , tern);
    			}
    		}else if(dl.equals(DiffLevel.hard)) {
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
    				move(p, from, to , tern);
    			}
    		}	
    		iv = new ImageView(img);
        	iv.setFitWidth(150);
        	iv.setFitHeight(150);
        	System.out.println(diceRes);
        	spDice.getChildren().add(iv);
        	
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
    
    void getPlayerByTern(int tern) {
    	
    }
    
    void move(Player player , int from , int to , int tern) {
    	int Tcul = GridPane.getColumnIndex(GameSetupController.boxes.get(to)) ;
    	int Trow = GridPane.getRowIndex(GameSetupController.boxes.get(to));
    	int Fcul = GridPane.getColumnIndex(GameSetupController.boxes.get(from));
    	int Frow = GridPane.getRowIndex(GameSetupController.boxes.get(from));
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
    	RunningGame.getInstance().getPlayerPlacement().put(p, to);
    	if(!(Tcul==Fcul && Frow==Trow)) {
        	GameSetupController.grid.add(c, Tcul, Trow);
    	}
    }
    
}
