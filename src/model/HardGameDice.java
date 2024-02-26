package model;

import java.util.Random;

public class HardGameDice extends Dice {
	public HardGameDice() {
		super();
	}

	public void showTheResult() {
		int diceRoll = this.roll();
		System.out.println("Dice roll: " + diceRoll);

		if (diceRoll <= 8)
			System.out.println("Move forward " + diceRoll + " square");
		else {
			if (diceRoll == 11)
				System.out.println("Answer a hard question.");
			else if (diceRoll == 10)
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
		number = random.nextInt(16);
		if(number==11) {
		return 9;
		}
		if(number==12) {
			return 10;
		}
		if(number>12) {
			return 11;
		}
		return number;
	}
}
