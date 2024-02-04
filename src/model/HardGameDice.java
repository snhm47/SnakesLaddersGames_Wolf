package model;

public class HardGameDice extends Dice {
	public HardGameDice() {
		super();
	}

	public void showTheResult() {
		int diceRoll = roll();
		System.out.println("Dice roll: " + diceRoll);

		if (diceRoll <= 6)
			System.out.println("Move forward " + diceRoll + " square");
		else {
			if (diceRoll % 4 == 0)
				System.out.println("Answer a difficult question.");
			else if (diceRoll % 2 == 0)
				System.out.println("Answer an easy question.");
			else
				System.out.println("Answer a medium question.");
		}
	}
}
