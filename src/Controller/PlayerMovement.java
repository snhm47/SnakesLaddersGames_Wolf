package Controller;

import Utils.DiffLevel;
import model.Dice;
import model.DiceFactory;
import model.EasyGameDice;
import model.MediumGameDice;
import model.Player;
import model.RunningGame;

public class PlayerMovement {
	public void Move(RunningGame rg , int from , int to ,  Player player) {
//		System.out.println(rg);
//		System.out.println(DiceRoll(rg));
//		System.out.println(DiceRoll(rg).roll());
		
	}
	
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
