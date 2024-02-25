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
		else 
			System.out.println("Answer an easy question ");
		
	}
	@Override
	public int roll() {
		// TODO Auto-generated method stub
		int number;
		Random random = new Random();
		number = random.nextInt(7) + 1;
		return number;
	}

}
