package model;

import java.util.Random;

public class Dice {
	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Dice() {
		super();
	}

	public int roll() {
		Random random = new Random();
		this.number = random.nextInt(10) + 1;
		return number;
	}

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
