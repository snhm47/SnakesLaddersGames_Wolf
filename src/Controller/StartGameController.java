package Controller;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import javafx.animation.KeyFrame;
import javafx.util.Duration;
import model.Dice;
import model.DiceFactory;
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
    
    private Timeline timeline;
    private int minutes = 0;
    private int seconds = 0;
    
    
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
    	
    }
    
    
    
}
