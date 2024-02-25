package model;

public abstract class Dice {
	private static int auto = 0;
	private int Id;
	private int number;
	
	
	public Dice() {
		this.Id = ++auto;
	}
	
	
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

	// return random number
	public abstract int roll();
	
	// Display an appropriate message
	public abstract void showTheResult();


	public static final String[] DICE_MESSAGES = { "Do not move forward", "Move forward one square",
			"Move forward two squares", "Move forward three squares", "Move forward four squares",
			"Move forward five squares", "Move forward six squares",
			"Answer an easy question. A correct answer does not advance, and a wrong answer goes back one step.",
			"Answer a medium question. A correct answer does not advance, and an incorrect answer goes back two steps.",
			"Answer a difficult question. A correct answer does not advance, and a wrong answer goes back three steps." };
}
