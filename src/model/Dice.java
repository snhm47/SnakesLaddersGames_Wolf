package model;

public class Dice {
	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Dice(int number) {
		super();
		this.number = number;
	}

	public void roll() {
	}

	public void showTheResult() {
	}

	public static final String[] DICE_MESSAGES = { "Do not move forward", "Move forward one square",
			"Move forward two squares", "Move forward three squares", "Move forward four squares",
			"Move forward five squares", "Move forward six squares",
			"Answer an easy question. A correct answer does not advance, and a wrong answer goes back one step.",
			"Answer a medium question. A correct answer does not advance, and an incorrect answer goes back two steps.",
			"Answer a difficult question. A correct answer does not advance, and a wrong answer goes back three steps." };
}
