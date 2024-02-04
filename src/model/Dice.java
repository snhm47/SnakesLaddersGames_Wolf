package model;

import java.util.Random;

import Utils.DiffLevel;

public class Dice {
	private static int auto = 0;
	private int Id;
	private int number;
	
	public int getID() {
		return Id;
	}

	public void setID(int iD) {
		Id = iD;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Dice() {
		super();
		this.Id = ++auto;
	}

	// return random number
	public int roll() {
		Random random = new Random();
		this.number = random.nextInt(10) + 1;
		return number;
	}
	/*
	public int roll(DiffLevel level) {	
		Random random = new Random();
		if(level.equals(DiffLevel.hard)||level.equals(DiffLevel.medium))
		{
			this.number = random.nextInt(10) + 1;
			if (number <= 7)
				return number;
			else {
				if (number % 4 == 0)
					return 10;
				else if (number % 2 == 0)
					return 9;
				else
					return 8;
		}
		}
		if(level.equals(DiffLevel.easy))
		{
			this.number = random.nextInt(8) + 1;
				return number;
		}
		return 0;			
	}
	*/
	
	
	// Display an appropriate message
	public void showTheResult() {
		System.out.println(DICE_MESSAGES[number-1]);
	}


	public static final String[] DICE_MESSAGES = { "Do not move forward", "Move forward one square",
			"Move forward two squares", "Move forward three squares", "Move forward four squares",
			"Move forward five squares", "Move forward six squares",
			"Answer an easy question. A correct answer does not advance, and a wrong answer goes back one step.",
			"Answer a medium question. A correct answer does not advance, and an incorrect answer goes back two steps.",
			"Answer a difficult question. A correct answer does not advance, and a wrong answer goes back three steps." };
}
