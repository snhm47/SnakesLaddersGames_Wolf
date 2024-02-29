package Controller;

import Utils.DiffLevel;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import model.Dice;
import model.DiceFactory;
import model.EasyGameDice;
import model.MediumGameDice;
import model.Player;
import model.RunningGame;

public class PlayerMovement {

	
	public Dice DiceRoll(RunningGame rg) {
		DiffLevel dl = rg.getCurrentGame().getDifficultyLevel();
		DiceFactory diceFactory = new DiceFactory();

		if(diceFactory.getDice(dl) instanceof EasyGameDice) {
			return diceFactory.getDice(dl);
		}else if(diceFactory.getDice(dl) instanceof MediumGameDice) {
			return diceFactory.getDice(dl);
		}else {
			return diceFactory.getDice(dl);
		}
		
		
	}
	
	
}
