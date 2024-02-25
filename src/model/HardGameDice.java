package model;

import java.util.Random;

public class HardGameDice extends Dice {
	public HardGameDice() {
		super();
	}

	public void showTheResult() {
		int diceRoll = this.roll();
		System.out.println("Dice roll: " + diceRoll);

		if (diceRoll <= 6)
			System.out.println("Move forward " + diceRoll + " square");
		else {
			if (diceRoll % 4 == 0)
				System.out.println("Answer a hard question.");
			else if (diceRoll % 2 == 0)
				System.out.println("Answer an easy question.");
			else
				System.out.println("Answer a medium question.");
		}
	}

	@Override
	public int roll() {
		// TODO Auto-generated method stub
		int number;
		Random random = new Random();
		number = random.nextInt(11);
		return number;
	}
}
