package model;

import java.util.Random;

public class MediumGameDice extends Dice {

	public MediumGameDice() {
		super();
	}

	public void showTheResult() {
		int diceRoll = this.roll();
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

	@Override
	public int roll() {
		// TODO Auto-generated method stub
		int number;
		Random random = new Random();
		number = random.nextInt(9) + 1;
		return number;
	}
}
