package model;

import java.util.Random;

public class EasyGameDice extends Dice {
	
	public EasyGameDice() {
		super();
	}
	public void showTheResult() {
		int diceRoll = this.roll();
		System.out.println("Dice roll: " + diceRoll);
		if (diceRoll <= 4)
			System.out.println("Move forward " + diceRoll + " square");
		else {
			if (diceRoll == 7)
				System.out.println("Answer a hard question.");
			else if (diceRoll == 5)
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
		number = random.nextInt(8);
		return number;
	}

}
