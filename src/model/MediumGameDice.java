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
			if (diceRoll ==7 || diceRoll ==8)
				System.out.println("Answer a easy question");
			else if (diceRoll ==9 || diceRoll ==10)
				System.out.println("Answer a medium question.");
			else if(diceRoll ==11 || diceRoll ==12)
				System.out.println("Answer a Hard question.");
		}
	}

	@Override
	public int roll() {
		// TODO Auto-generated method stub
		int number;
		Random random = new Random();
		number = random.nextInt(13);	
		if(number==10) {
		return 7;	
		}
		if(number==11) {
			return 8;	
		}
		if(number==12) {
			return 9;	
		}
		return number;			
	}
}
