package model;

import java.util.Random;

public class Dice {
	private static int auto = 0;
	private int ID;
	private int Number;
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		this.Number = number;
	}

	public Dice() {
		super();
		this.ID = ++auto;
	}

	// Random number
	public int roll() {
		Random random = new Random();
		this.Number = random.nextInt(10) + 1;
		return Number;
	}

	// Display an appropriate message
	public void showTheResult() {
		System.out.println(DICE_MESSAGES[Number-1]);
	}


	public static final String[] DICE_MESSAGES = { "Do not move forward", "Move forward one square",
			"Move forward two squares", "Move forward three squares", "Move forward four squares",
			"Move forward five squares", "Move forward six squares",
			"Answer an easy question. A correct answer does not advance, and a wrong answer goes back one step.",
			"Answer a medium question. A correct answer does not advance, and an incorrect answer goes back two steps.",
			"Answer a difficult question. A correct answer does not advance, and a wrong answer goes back three steps." };
}
