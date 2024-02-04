package model;

public class MediumGameDice extends Dice {

	public MediumGameDice() {
		super();
	}

	public void showTheResult() {
		int diceRoll = roll();
		System.out.println("Dice roll: " + diceRoll);
		if (diceRoll <= 6)
			System.out.println("Move forward " + diceRoll + " square");
		else {
			if (diceRoll % 2 == 0)
				System.out.println("Answer a medium question");
			else
				System.out.println("Move forward without answering a question.");
		}
	}
}
