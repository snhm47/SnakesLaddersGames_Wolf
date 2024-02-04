package model;

public class EasyGameDice extends Dice {
	public EasyGameDice() {
		super();
	}
	public void showTheResult() {
		int diceRoll = roll();
		System.out.println("Dice roll: " + diceRoll);
		if (diceRoll <= 4)
			System.out.println("Move forward " + diceRoll + " square");
		else 
			System.out.println("Answer an easy question ");
		
	}

}
